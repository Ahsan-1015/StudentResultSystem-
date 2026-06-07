# Student Result System (SE-409)

This repository contains the implementation of a **Student Result System** developed for the course *Advanced Enterprise Java (SE-409)*.

The system leverages **Java Collections** (Arrays & HashMaps) for temporary data storage and management, calculates total marks and grades dynamically, and uses **JDBC (Java Database Connectivity)** to persistently store and retrieve student records from a MySQL database.

---

## Features

*   **Dynamic Grade Calculation:** Automatically sums up marks for three subjects and assigns academic grades (`A+`, `A`, `B`, `C`, `F`).
*   **Java Collections Integration:** Uses a `String[]` array for storing student names and a `HashMap<Integer, Integer[]>` to map unique Student IDs to their marks.
*   **Database Persistence (JDBC):** Automatically initializes the database and table, inserts/updates records securely using `PreparedStatement`, and retrieves data to display on the console using `ResultSet`.

---

## Project Structure

*   **`StudentResultSystem.java`**: The main Java file containing the business logic, database configurations, and console display helper methods.

---

## Prerequisites

1.  **Java Development Kit (JDK):** Version 1.8 or higher.
2.  **MySQL Server:** Managed via XAMPP Control Panel.
3.  **JDBC Driver:** MySQL Connector/J (`mysql-connector-java-8.0.33.jar` or similar).

---

## Getting Started & Database Setup

### 1. Start MySQL Server (via XAMPP)
*   Open the **XAMPP Control Panel**.
*   Click **Start** next to **MySQL** (and **Apache** if you want to view the database through `http://localhost/phpmyadmin`).

### 2. Configure Your IDE (IntelliJ IDEA) to include the MySQL Driver
If you see a `java.sql.SQLException: No suitable driver found` error, add the Maven dependency or JAR to your classpath:
1.  Go to **File** > **Project Structure** > **Libraries**.
2.  Click the **`+` (Add)** icon > **From Maven...**
3.  Search for: `mysql:mysql-connector-java:8.0.33` and click **OK**.
4.  Click **Apply** and **OK**.

---

## Compiling and Running the Code

1.  Compile the Java file:
bash
javac StudentResultSystem.java


2.  Run the application:
bash
java StudentResultSystem


---

## Sample Console Output

When you run the code, the application automatically handles database creation, inserts/updates the dataset, and retrieves the records to print on the console:
text
Data successfully saved to database.

=========================================================================

STUDENT DATABASE RECORDS

=========================================================================

ID | Name | Sub1 | Sub2 | Sub3 | Total | Grade

-------------------------------------------------------------------------

1015	Ahsan	85	90	80	255	A+
1064	Hira	95	88	92	275	A+
1083	Saju	75	70	65	210	A
=========================================================================

---

## Conceptual Review (Quick Reference)

### 1. Java Collections Used
*   **`String[]` (Array):** Used to store student names sequentially. Arrays are chosen because the order matches the iteration indexing and they are highly memory-efficient.
*   **`HashMap<Integer, Integer[]>`:** Used to store the unique Student ID (Key) and map it directly to an array of marks (Value). HashMaps are chosen because they offer high performance $O(1)$ lookup complexity, making finding a student's marks by their unique ID near-instantaneous.

### 2. JDBC Steps Implemented
1.  **Registering the Driver:** The database connection handles the implicit registration of the MySQL Driver (`com.mysql.cj.jdbc.Driver`).
2.  **Establishing Connection:** Connects to localhost MySQL using `DriverManager.getConnection()`.
3.  **Executing SQL Queries:**
    *   `Statement`: Used to run `CREATE DATABASE` and `CREATE TABLE` queries.
    *   `PreparedStatement`: Used with parameterized place-holders (`?`) to execute `INSERT ... ON DUPLICATE KEY UPDATE` queries securely, preventing SQL Injection.
4.  **Retrieving Results:** Uses `ResultSet` to loop through the query output of `SELECT * FROM student_results` and display it in a clean console table.
5.  **Closing Resources:** Implicitly uses **Java Try-with-Resources** block to safely and automatically close all open database connections and statements.
