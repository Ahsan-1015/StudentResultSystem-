# 🎓 Student Result System (SE-409)

A Java-based **Student Result Management System** developed for the **Advanced Enterprise Java (SE-409)** course.

The application demonstrates the use of **Java Collections Framework**, **Object-Oriented Programming (OOP)** concepts, and **JDBC (Java Database Connectivity)** to manage student records, calculate grades dynamically, and store data permanently in a MySQL database.

---

## 📌 Project Overview

This project allows users to:

* Store student information and marks.
* Calculate total marks automatically.
* Generate grades based on total scores.
* Save records into a MySQL database.
* Retrieve and display records from the database.
* Demonstrate practical implementation of Java Collections and JDBC.

---

## 🚀 Features

### ✅ Student Record Management

* Stores student names and marks.
* Uses arrays and collections for temporary data handling.

### ✅ Automatic Grade Calculation

The system automatically calculates:

* Total Marks
* Academic Grade

| Total Marks | Grade |
| ----------- | ----- |
| 240 - 300   | A+    |
| 210 - 239   | A     |
| 180 - 209   | B     |
| 150 - 179   | C     |
| Below 150   | F     |

### ✅ Java Collections Usage

#### Array

```java
String[] studentNames
```

Used to store student names sequentially.

#### HashMap

```java
HashMap<Integer, Integer[]>
```

Stores:

* Student ID → Key
* Subject Marks → Value

Benefits:

* Fast data retrieval
* O(1) average lookup time
* Easy record management

### ✅ JDBC Integration

The system performs:

* Database Creation
* Table Creation
* Data Insertion
* Data Update
* Data Retrieval

using:

* DriverManager
* Connection
* Statement
* PreparedStatement
* ResultSet

---

# 🏗️ Project Structure

```text
Student-Result-System/
│
├── StudentResultSystem.java
│
├── README.md
│
└── mysql-connector-java-8.0.33.jar
```

---

# ⚙️ Technologies Used

| Technology       | Purpose                 |
| ---------------- | ----------------------- |
| Java             | Application Development |
| JDBC             | Database Connectivity   |
| MySQL            | Data Storage            |
| XAMPP            | Local MySQL Server      |
| IntelliJ IDEA    | Development Environment |
| Java Collections | Data Management         |

---

# 📋 Prerequisites

Before running the project, ensure the following are installed:

### 1. Java Development Kit (JDK)

Version:

```text
JDK 8 or Higher
```

Check version:

```bash
java -version
```

---

### 2. XAMPP

Download and install:

https://www.apachefriends.org

Start:

* Apache
* MySQL

from the XAMPP Control Panel.

---

### 3. MySQL Connector/J

Required JDBC Driver:

```text
mysql-connector-java-8.0.33.jar
```

Download:

https://dev.mysql.com/downloads/connector/j/

---

# 🔧 Database Configuration

Update the following credentials inside:

```java
StudentResultSystem.java
```

```java
private static final String DB_URL =
"jdbc:mysql://localhost:3306/student_db";

private static final String USER = "root";
private static final String PASSWORD = "";
```

Modify if your MySQL setup uses different credentials.

---

# 🛠️ IntelliJ IDEA Setup

## Method 1: Add Maven Dependency

Go to:

```text
File
 └── Project Structure
      └── Libraries
```

Click:

```text
+ → From Maven
```

Search:

```text
mysql:mysql-connector-java:8.0.33
```

Click:

```text
OK → Apply → OK
```

---

## Method 2: Add JAR File

```text
Project Structure
 └── Libraries
      └── Add JAR/Folder
```

Select:

```text
mysql-connector-java-8.0.33.jar
```

Apply changes.

---

# ▶️ Compile and Run

## Compile

```bash
javac StudentResultSystem.java
```

## Run

```bash
java StudentResultSystem
```

---

# 🗄️ Database Operations Performed

### Create Database

```sql
CREATE DATABASE IF NOT EXISTS student_db;
```

### Create Table

```sql
CREATE TABLE IF NOT EXISTS student_results(
    id INT PRIMARY KEY,
    name VARCHAR(100),
    sub1 INT,
    sub2 INT,
    sub3 INT,
    total INT,
    grade VARCHAR(5)
);
```

### Insert or Update Record

```sql
INSERT INTO student_results
VALUES(?,?,?,?,?,?,?)
ON DUPLICATE KEY UPDATE
name=VALUES(name),
sub1=VALUES(sub1),
sub2=VALUES(sub2),
sub3=VALUES(sub3),
total=VALUES(total),
grade=VALUES(grade);
```

---

# 📊 Sample Console Output

```text
Data successfully saved to database.

=========================================================================

                    STUDENT DATABASE RECORDS

=========================================================================

ID     Name      Sub1   Sub2   Sub3   Total   Grade

-------------------------------------------------------------------------

1015   Ahsan      85     90     80     255     A+

1064   Hira       95     88     92     275     A+

1083   Saju       75     70     65     210     A

=========================================================================

```

---

# 📚 JDBC Workflow

### Step 1: Load Driver

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

### Step 2: Establish Connection

```java
DriverManager.getConnection(...)
```

### Step 3: Execute SQL Queries

Using:

```java
Statement
PreparedStatement
```

### Step 4: Retrieve Data

Using:

```java
ResultSet
```

### Step 5: Close Resources

Implemented using:

```java
try-with-resources
```

which automatically closes:

* Connection
* Statement
* PreparedStatement
* ResultSet

---

# 🧠 Concepts Demonstrated

## Java Concepts

* Arrays
* HashMap
* Loops
* Methods
* OOP Principles
* Exception Handling
* JDBC API

## Database Concepts

* Database Creation
* Table Creation
* CRUD Operations
* SQL Queries
* Primary Keys
* Prepared Statements

---

# 🎯 Learning Outcomes

After completing this project, students will understand:

* Java Collections Framework
* JDBC Architecture
* MySQL Integration
* Database Connectivity
* Data Persistence
* SQL Query Execution
* ResultSet Processing
* Exception Handling in Java

---

# 👨‍💻 Author

**Ahsan Habib**

Student of Computer Science & Engineering (CSE)

Course: **Advanced Enterprise Java (SE-409)**

---

# ⭐ Support

If you found this project helpful:

⭐ Star this repository

🍴 Fork this repository

📢 Share with fellow students

---

## 📄 License

This project is developed for academic and educational purposes under the **SE-409 Advanced Enterprise Java** course.
