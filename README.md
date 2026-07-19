# Smart Cafe Management System

## Overview
This is a Java-based Command Line Interface (CLI) application developed to manage cafe operations. It efficiently handles table availability, customer order processing, and allows for correcting cashier mistakes. 

This project was developed as a practical assignment for the **Data Structures and Algorithms (ICT 143-2)** module at Uva Wellassa University of Sri Lanka.

## Data Structures Used
To ensure optimal performance and functionality, this system leverages three core data structures:
1. **ArrayList (`tables`):** Manages the cafe tables. It provides fast index-based access to dynamically check and update the occupancy status (Available/Occupied) of each table.
2. **Queue / LinkedList (`orderQueue`):** Manages incoming customer orders. It enforces a First-Come, First-Served (FIFO) logic, ensuring fair processing for the kitchen staff.
3. **Stack (`recentOrdersStack`):** Acts as an "Undo" mechanism. It stores recent orders using Last-In, First-Out (LIFO) logic, allowing the quick cancellation of mistaken orders entered by the cashier.

## Setup Instructions (How to Run)
1. Ensure you have the Java Development Kit (JDK) installed on your system.
2. Clone this repository or download the `CafeManagementSystem.java` file.
3. Open your terminal or command prompt and navigate to the folder containing the file.
4. Compile the code using the command: `javac CafeManagementSystem.java`
5. Run the application using the command: `java CafeManagementSystem`
6. Follow the on-screen instructions (1-5) to interact with the system.
