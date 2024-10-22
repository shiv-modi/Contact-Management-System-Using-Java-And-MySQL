# Contact Management System 📇

## Description:
The Contact Management System is a web application that allows users to manage their contacts with functionalities such as adding, editing, removing, viewing, importing, and exporting contacts. This application helps users keep track of their personal or professional contact lists in a user-friendly way.

## Features:
1. **CRUD Operations ✏️:**
   - **Create:** Add new contacts with details like name, phone, and email.
   - **Read:** View a list of all contacts.
   - **Update:** Edit the details of existing contacts.
   - **Delete:** Remove contacts from the list.

2. **Contact Import/Export 📤📥:**
   - **Import:** Users can import contacts from VCF (vCard) files.
   - **Export:** Users can export their contacts to a CSV file (vCard export in progress).

3. **Data Access and Authentication 🔐:**
   - The application includes basic authentication with login functionality.
   - Users can log in to access their contacts. (Sign-up and account creation process is manual for this assignment.)

4. **Search and Tagging 🔍 (In Progress):**
   - **Tagging:** Users will be able to add custom tags to contacts for better organization.
   - **Advanced Search:** Search functionality based on tags, name, and email is in development.

5. **Reminder Feature ⏰:**
   - Users can set a time and date and choose a contact to receive a reminder to call that contact.

## Setup Instructions:

### Requirements:
- **Java:** Backend built using Java, with Servlets and JSP.
- **MySQL:** Database used to store contact information.
- **Tomcat:** For running the web server.

### Database Setup:
1. **Create a MySQL database** called `cms`.
2. Run the provided SQL script to set up the `contacts` table.

### Configure Database Connection:
Update your database credentials in the `dbConnection` class or configuration file:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/cms";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "yourpassword";
```
### Run the Application:
- Deploy the application on a Tomcat server.
- Access the application in the browser at [http://localhost:8080/CMS](http://localhost:8080/CMS).

## Final Deliverables:
- **Video Demonstration:** A short video demonstrating the functionality of the app. [Link to Video](https://drive.google.com/file/d/1rQ70g1qHMj1L6P55FS0-nE6OMZmU-ApA/view?usp=drive_link)
