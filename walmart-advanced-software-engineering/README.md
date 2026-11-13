# Walmart Global Tech – Advanced Software Engineering Job Simulation

## Project Overview
This repository contains the complete implementation for the **Walmart Global Tech Advanced Software Engineering Simulation** on Forage. The work encompasses custom data structures, system architecture design using the Strategy Pattern, normalized relational database modeling (ERD), and ETL data munging pipelines.

---

## Repository Structure
```text
├── src/
│   ├── PowerOfTwoMaxHeap.java    # Custom 2^x max heap implementation in Java
│   └── populate_database.py      # SQLite & Pandas ETL ingestion pipeline
├── diagrams/                     # UML Class Diagram and Relational ERD design assets
├── requirements.txt
└── README.md
```

---

## Tasks Completed

1. **Advanced Data Structures (`src/PowerOfTwoMaxHeap.java`)**
   - Implemented a generalized $2^{\text{childExponent}}$ max-heap data structure in Java.
   - Optimized parent-child index calculations using bitwise shifting (`>>> childExponent` and `<< childExponent`) for high-performance execution.
   - Maintained strict heap properties across variable branching factors with zero dependencies.

2. **Software Architecture (`diagrams/DataProcessor_UML_Class_Diagram.pdf`)**
   - Designed a modular class diagram implementing the **Strategy Pattern** for dynamic processing modes (`Dump`, `Passthrough`, `Validate`) and database connectors (`Postgres`, `Redis`, `Elastic`).

3. **Relational Database Design (`diagrams/Relational_Database_ERD.pdf`)**
   - Architected a normalized Entity-Relationship Schema (3NF) utilizing Supertype/Subtype inheritance for products (Food, Toys, Apparel), junction tables for many-to-many animal associations, and decoupled shipping origin/destination tracking.

4. **Data Munging & ETL (`src/populate_database.py`)**
   - Built a Python ETL pipeline utilizing `pandas` and `sqlite3` to clean, merge, aggregate, and ingest shipping spreadsheets into a structured relational database.

---

## Setup & Execution
```bash
git clone https://github.com/riyaz2018786/walmart-advanced-software-engineering.git
cd walmart-advanced-software-engineering
python -r requirements.txt
```
