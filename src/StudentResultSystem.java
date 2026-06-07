import java.sql.*;
import java.util.HashMap;

public class StudentResultSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        String[] studentNames = {"Ahsan", "Saju", "Hira"};
        int[] studentIds = {1015, 1083, 1064};

        HashMap<Integer, Integer[]> studentMarksMap = new HashMap<>();
        studentMarksMap.put(1015, new Integer[]{85, 90, 80});
        studentMarksMap.put(1083, new Integer[]{75, 70, 65});
        studentMarksMap.put(1064, new Integer[]{95, 88, 92});

        setupDatabase();
        insertStudentData(studentIds, studentNames, studentMarksMap);
        displayAllRecords();
    }

    private static void setupDatabase() {
        try {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS student_db");
            }

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS student_results (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(100), " +
                        "subject1 INT, " +
                        "subject2 INT, " +
                        "subject3 INT, " +
                        "total INT, " +
                        "grade VARCHAR(5))";
                stmt.executeUpdate(createTableSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudentData(int[] ids, String[] names, HashMap<Integer, Integer[]> marksMap) {
        String insertSQL = "INSERT INTO student_results (id, name, subject1, subject2, subject3, total, grade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name=VALUES(name), subject1=VALUES(subject1), " +
                "subject2=VALUES(subject2), subject3=VALUES(subject3), total=VALUES(total), grade=VALUES(grade)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            for (int i = 0; i < names.length; i++) {
                int id = ids[i];
                String name = names[i];
                Integer[] marks = marksMap.get(id);

                if (marks != null && marks.length == 3) {
                    int total = marks[0] + marks[1] + marks[2];
                    String grade = calculateGrade(total);

                    pstmt.setInt(1, id);
                    pstmt.setString(2, name);
                    pstmt.setInt(3, marks[0]);
                    pstmt.setInt(4, marks[1]);
                    pstmt.setInt(5, marks[2]);
                    pstmt.setInt(6, total);
                    pstmt.setString(7, grade);
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Data successfully saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String calculateGrade(int total) {
        double average = total / 3.0;
        if (average >= 80) return "A+";
        if (average >= 70) return "A";
        if (average >= 60) return "B";
        if (average >= 50) return "C";
        return "F";
    }

    private static void displayAllRecords() {
        String selectSQL = "SELECT * FROM student_results";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            System.out.println("\n=========================================================================");
            System.out.println("                         STUDENT DATABASE RECORDS                        ");
            System.out.println("=========================================================================");
            System.out.printf("%-6s | %-15s | %-4s | %-4s | %-4s | %-5s | %-5s\n",
                    "ID", "Name", "Sub1", "Sub2", "Sub3", "Total", "Grade");
            System.out.println("-------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-6d | %-15s | %-4d | %-4d | %-4d | %-5d | %-5s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("subject1"),
                        rs.getInt("subject2"),
                        rs.getInt("subject3"),
                        rs.getInt("total"),
                        rs.getString("grade"));
            }
            System.out.println("=========================================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}