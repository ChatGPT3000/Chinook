# Database Access Project with PostgreSQL and Maven

This project is part of the Experis Academy assignment and focuses on accessing a PostgreSQL database using Java and Maven. It demonstrates various methods for interacting with the database, including data retrieval, insertion, updating, and deletion.

## Prerequisites

Before you begin, ensure you have the following:

- Java Development Kit (JDK) installed
- Apache Maven installed
- PostgreSQL database installed and running
- Necessary database configuration (username, password, database URL) available

## Assignment Part 1
All .sql scripts for part 1 of the assignment, can be found in SQL Scripts directory.

## Getting Started

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/ChatGPT3000/Chinook.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Chinook_Assignment
   ```

3. Update the database configuration in the `application.properties` file located in the `src/main/resources` directory. Modify the following properties:

   ```properties
   spring.datasource.url=<your-database-url>
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   ```
    Or set up the database connection in IDE (E.g. IntelliJ)
## Running the Project

To run the project, execute the following steps:

1. Build the project using Maven:

   ```bash
   mvn clean install
   ```

2. Run the application:

   ```bash
   java -jar target/experis-database-project.jar
   ```
    Or via IDE UI.
## Usage

The project provides several methods for interacting with the database:

- **Retrieve Data**: Fetch data from the database based on specific criteria.
- **Insert Data**: Add new records to the database.
- **Update Data**: Modify existing records in the database.
- **Delete Data**: Remove records from the database.

You can explore and run these methods by interacting with the application.

## License

This project is licensed under the [MIT License](LICENSE).
