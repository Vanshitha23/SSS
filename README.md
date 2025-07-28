# Shamir's Secret Sharing - Catalog Placements Assignment

## 📖 Problem Statement
This project implements a simplified version of **Shamir's Secret Sharing algorithm**.  
Given a set of encoded points (x, y), the task is to reconstruct the secret constant term `c` of the hidden polynomial using **Lagrange interpolation**.

- The input points are provided in **JSON files**.
- Each y-value is encoded in a specific base and needs to be decoded.
- The degree of the polynomial is determined as `m = k - 1` (where `k` is given).
- The goal is to compute **f(0) = c**, the secret.

---

## 📂 Project Structure
ProjectRoot/
├── libs/
│ └── json-20231013.jar # External JSON library
├── src/
│ └── SecretSharing.java # Main Java code
├── testcase1.json # First test case input
├── testcase2.json # Second test case input
└── README.md # Documentation

---

## 📝 Requirements
- **Language:** Java (any IDE, IntelliJ recommended)
- **JSON Library:** [org.json](https://mvnrepository.com/artifact/org.json/json)  
  (Add `json-20231013.jar` to the project classpath)
- **Java Version:** 8 or higher

---

## ⚙️ How to Run

### 1. Compile the Program
```bash
javac -cp .;libs/json-20231013.jar src/SecretSharing.java -d out
java -cp out;libs/json-20231013.jar SecretSharing

