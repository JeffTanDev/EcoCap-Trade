# EcoCap README

## 1. About / Overview
EcoCap is a web application focused on managing company emission quotas. It utilizes Spring Boot
for the backend and MySQL for the database, enabling users to search for company information,
view emission quotas and conduct transactions such as buying and selling quotas. The frontend is
built with HTML, CSS and JavaScript.

## 2. List of Features
- **User Authentication**: Secure login for users and administrators.
- **Company Information Lookup**: Search for companies by name and view their details.
- **Emission Quota Management**: View and manage direct and indirect emission quotas.
- **Transaction Handling**: Buy and sell emission quotas with transaction records.
- **Admin Dashboard**: Manage tickets and oversee user activities.
- **Responsive Design**: User-friendly interface that works on various devices.

## 3. How to Run
1. **Clone the Repository**: 
   ```bash
   git clone <repository-url>
   cd EcoCap
   ```

2. **Set Up the Database**:
   - Ensure MySQL is installed and running.
   - Execute the SQL script to create the database:
     ```sql
     source src/main/resources/sql/Produce/1create_database.sql
     ```

3. **Configure Application Properties**:
   - Update the `application.properties` file with your MySQL database credentials.

4. **Run the Application**:
   - Use Maven to build and run the application:
     ```bash
     mvn spring-boot:run
     ```

5. **Access the Application**:
   - Open a web browser and navigate to `http://localhost:8080`.

## 4. How to Use
- **Login**: Use your credentials to log in to the application.
- **Search for Companies**: Enter a company name in the search box to retrieve information.
- **Manage Quotas**: View your emission quotas and perform buy/sell transactions.
- **Admin Functions**: Admin users can manage tickets and oversee user activities.

## 5. Design
- The database schema includes tables for `Company`, `Company_User`, `Transaction`, and various emission-related tables.
- Relationships between tables are established using foreign keys to maintain data integrity.
- Indexes have been implemented on key columns to improve query performance.

## 6. Assumptions
- Users have a basic understanding of web applications and database concepts.
- MySQL is installed and configured on the local machine.
- The application is intended for use in environments where emission quota management is relevant.

## 7. Limitations
- The application currently supports only basic user roles (admin and regular users).
- There is no automated backup process for the MySQL database, which increases the risk of data loss.
