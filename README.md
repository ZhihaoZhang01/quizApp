# Quiz Web Application

This project is a personal online quiz web application developed in Java using Spring Boot, JDBC Template, and MySQL. It also provides Hibernate-based DAO implementations for comparison and flexibility. The application follows the Spring MVC architecture and uses JSP with JSTL for views (without Thymeleaf). A global navigation bar is present on every page, and the system supports both regular user and admin sections with different functionalities.

---

## Table of Contents

- [Features](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Project Structure](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Prerequisites](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Installation and Configuration](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Running the Application](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Switching Between JDBC and Hibernate Implementations](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Admin Section](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)
- [Additional Notes](https://www.notion.so/197db9fb07b48073a41ef988b37c9176?pvs=21)

---

## Features

- **User Section:**
    - **Registration:** New users can create an account by providing their email, first name, last name, password, and other details.
    - **Login:** Users can log in with their credentials. If login is successful, regular users are redirected to the home page and admins to the admin home page.
    - **Home Page:** Displays quiz categories (retrieved from the Category table), recent quiz history (from the Quiz table), and if available, a link to resume an unfinished quiz.
    - **Quiz Page:** When a user selects a quiz category, a new quiz is created and five random questions are assigned. A countdown timer (5 minutes) is displayed, and if the timer expires, the quiz is auto-submitted.
    - **Quiz Result Page:** After submitting a quiz, users can view their results (Pass/Fail based on correct answers) along with detailed information for each question.
    - **Contact Us Page:** Users can send messages to the admin using a contact form.
- **Admin Section:**
    - **Admin Home Page:** Contains navigation links/buttons to access User Management, Quiz Result Management, Question Management, and Contact Us Management pages.
    - **User Management:** Displays a table with all user information (full name, email, status, etc.) and allows the admin to activate or suspend users. Pagination is available as a bonus feature.
    - **Quiz Result Management:** Lists all quiz results (taken time, category, user full name, number of questions, score, and result) with a link to view detailed results. Admins can filter results by category or user email, and pagination is available.
    - **Question Management:** Displays all questions with their category, description, and status, and allows the admin to edit (update description, choices, correct answer) or toggle the status (activate/suspend). An "Add" button is provided to create new questions.
    - **Contact Us Management:** Lists all messages sent via the Contact Us form, and provides a "View" button to see the full message content.
- **Global Navigation Bar:**
    
    The application features a global navigation bar that adapts to the login status:
    
    - If the user is not logged in, the navigation bar shows **Login** and **Register** links.
    - If the user is logged in, it shows **Home**, **Logout**, and if applicable, a link to resume an unfinished quiz.
    - The **Contact** link is always visible.

---

## Project Structure

```
bash
Copy
QuizApp/
├── src/main/java/com/zhihao/quizapp/
│   ├── config/                  # Hibernate and application configuration classes
│   ├── controller/              # Controllers for user and admin sections
│   ├── dao/                     # DAO interfaces and implementations
│   │   ├── jdbc/                # JDBC Template based DAO implementations
│   │   └── hibernate/           # Hibernate-based DAO implementations
│   ├── model/                   # Entity and DTO classes (using Lombok and JPA annotations)
│   └── service/                 # Service layer classes
├── src/main/resources/
│   ├── application.properties   # Application configuration (data source, Hibernate, etc.)
│   └── static/resources/css/    # CSS files and other static resources
└── src/main/webapp/WEB-INF/jsp/  # JSP pages for views

```

---

## Prerequisites

- **JDK 1.8+**
- **Maven**
- **MySQL Database**

---

## Installation and Configuration

1. **Clone the repository:**
    
    ```bash
    bash
    Copy
    git clone <repository_url>
    cd QuizApp
    
    ```
    
2. **Configure MySQL Database:**
    
    Create a database using the provided SQL schema. Update the `application.properties` file (or your custom HibernateProperty configuration) with your database URL, username, and password.
    
3. **Maven Build:**
    
    Build the project using Maven:
    
    ```bash
    bash
    Copy
    mvn clean install
    
    ```
    
4. **Configure Profiles (Optional):**
    
    If you want to switch between JDBC Template and Hibernate DAO implementations, consider using Spring Profiles. For example, annotate your JDBC implementations with `@Profile("jdbc")` and Hibernate implementations with `@Profile("hibernate")`, then set the active profile in `application.properties`:
    
    ```
    properties
    Copy
    spring.profiles.active=hibernate
    
    ```
    

---

## Running the Application

Run the application using Maven or your IDE. For Maven:

```bash
bash
Copy
mvn spring-boot:run

```

Then open your browser and navigate to [http://localhost:8080](http://localhost:8080/). You can log in, register, and use the quiz features.

---

## Switching Between JDBC and Hibernate Implementations

- **DAO Interfaces:** Each DAO is abstracted as an interface (e.g., `ICategoryDAO`, `IChoiceDAO`, etc.).
- **Implementations:**
    - JDBC Template implementations are located under `com.zhihao.quizapp.dao.jdbc` and annotated with a unique bean name (e.g., `@Repository("jdbcCategoryDAO")`).
    - Hibernate implementations are located under `com.zhihao.quizapp.dao.hibernate` and annotated with a unique bean name (e.g., `@Repository("hibernateCategoryDAO")`).
- **Service Injection:**
    
    In Service classes, you can inject a DAO interface and use `@Qualifier` to choose the implementation. For example:
    
    ```java
    java
    Copy
    @Autowired
    public CategoryService(@Qualifier("hibernateCategoryDAO") ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
    ```
    
- **Spring Profiles:**
    
    Optionally, annotate DAO implementations with `@Profile("jdbc")` or `@Profile("hibernate")` and set the active profile in your properties file.
    

---

## Admin Section

- **Admin Login:**
    
    Admin users (with `isAdmin` flag set) are redirected to the admin home page upon successful login.
    
- **Admin Home:**
    
    Contains navigation links to User Management, Quiz Result Management, Question Management, and Contact Management pages.
    
- **User Management:**
    
    Displays user details and provides functionality to activate or suspend users.
    
- **Quiz Result Management:**
    
    Shows a table of quiz results with filtering options and pagination.
    
- **Question Management:**
    
    Lists questions with options to edit, update choices, toggle active status, and add new questions.
    
- **Contact Management:**
    
    Lists contact messages and allows viewing full message details.
    

---

## Additional Notes

- **Global Navigation Bar:**
    
    The navigation bar dynamically displays Login/Register or Logout based on whether a session-scoped user object exists (using `${sessionScope.user}`).
    
- **Lazy Loading Considerations:**
    
    For entities with lazy-loaded collections, Lombok’s auto-generated `toString()` may trigger lazy initialization exceptions. Use Lombok’s `@ToString(exclude = "collectionFieldName")` to exclude such fields from the generated `toString()` method.
    
- **Hibernate Configuration:**
    
    The application uses a custom `HibernateConfig` class to configure `LocalSessionFactoryBean`, `DataSource`, and `HibernateTransactionManager`. You can modify these configurations as needed.
    
- **Force Push & Branch Merging:**
    
    If you need to override remote branches with your local changes, use caution with force pushes (`git push --force-with-lease`) and merging strategies as documented in the repository guidelines.
