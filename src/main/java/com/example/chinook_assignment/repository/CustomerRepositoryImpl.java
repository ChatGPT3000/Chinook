package com.example.chinook_assignment.repository;

import com.example.chinook_assignment.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the {@link CustomerRepository} interface and provides methods
 * to manage Customer entities using a database connection.
 *
 * @see CustomerRepository
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    // Database URL for connecting to the PostgreSQL database.
    private String url = "jdbc:postgresql://localhost:5432/Assignment-2-db";

    // Username for authenticating the database connection.
    private String username = "postgres";

    // Password for authenticating the database connection.
    private String password = "postgres";

    /**
     * Default constructor for the CustomerRepositoryImpl class.
     */
    public CustomerRepositoryImpl() {
        // Empty constructor body.
    }

    /**
     * Constructor for the CustomerRepositoryImpl class with custom database connection parameters.
     *
     * @param url      The database URL.
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    public CustomerRepositoryImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    /**
     * Method for testing the database connection.
     */
    public void test() {
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            System.out.println("Connected to Postgres...");  // Print message indicating successful connection.
        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace in case of connection error.
        }
    }


    //Task 1
    /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of Customer objects containing information about each customer.
     */
    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Create a PreparedStatement for the SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute the query and get the result set
            ResultSet result = statement.executeQuery();
            // Process the result set and populate the customers list
            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace in case of an SQL exception.
        }
        return customers;  // Return the list of retrieved customers.
    }


    // Task 2
    /**
     * Finds Customer from provided ID.
     *
     * @param customerID The ID of the customer to retrieve.
     * @return A Customer object containing information about the retrieved customer,
     *         or null if the customer is not found.
     */
    @Override
    public Customer findById(Integer customerID) {
        String sql = "SELECT * FROM customer WHERE customer_id=?";
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerID);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            if (result.next()) {
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return customer; // Returns retrieved customer.
    }

    // Task 3
    /**
     * Returns a Customer or Customers (in case naming string fits multiple customers).
     *
     * @param customerName The name to search for.
     * @return A list of Customer entities with the specified name.
     */
    public List<Customer> getCustomerByName(String customerName) {
        String sql = "SELECT * FROM customer WHERE first_name LIKE ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + customerName + "%");
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return customers; // Return the list of retrieved customers.
    }

    //Task 4
    /**
     * Gets a list of customers with size and offset.
     *
     * @param limit  The maximum number of customers to retrieve.
     * @param offset The starting position for retrieving customers.
     * @return A list of Customer entities limited by the specified parameters.
     */
    public List<Customer> getAllCustomersLimited(int limit, int offset) {
        String sql = "SELECT * FROM customer LIMIT ? OFFSET ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, limit);
            statement.setInt(2, offset);

            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return customers; // Return the list of retrieved customers.
    }

    // Task 5
    /**
     * Saves customer to the Database and returns response as an int.
     *
     * @param customer The customer to save.
     * @return An int indicating the status of the save operation.
     */
    public int save(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?)";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer.firstName());
            statement.setString(2, customer.lastName());
            statement.setString(3, customer.country());
            statement.setString(4, customer.postalCode());
            statement.setString(5, customer.phoneNumber());
            statement.setString(6, customer.email());

            // Execute statement
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return result; // Returns response as int
    }

    // Task 6
    /**
     * Updates an email of a chosen customer by ID.
     *
     * @param id       The ID of the customer.
     * @param newEmail The new email to update.
     * @return An int indicating the status of the update operation.
     */
    @Override
    public int update(Integer id, String newEmail) {
        String sql = "UPDATE customer SET email = ? WHERE customer_id = ?";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(2, id);
            statement.setString(1, newEmail);

            // Execute statement
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return result; // Returns response as an int
    }

    // Task 7
    /**
     * Returns the country or countries with the most number of residents.
     *
     * @return A list of strings representing the most common countries.
     */
    public List<String> getMostCommonCountries() {
        String sql = "SELECT country, COUNT(*) as frequency FROM customer GROUP BY country ORDER BY COUNT(*) DESC;";
        List<String> commonCountries = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            int lastFrequency = 0;
            while (result.next()) {
                if (lastFrequency > result.getInt("frequency")) {
                    break;
                }else {
                    String country = result.getString("country");
                    commonCountries.add(country);
                    lastFrequency = result.getInt("frequency");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return commonCountries; // Countries are returned as a string list
    }

    //Task 8
    /**
     * Combines invoices for all customers and returns customer data for the highest spender.
     *
     * @return A list of strings representing customer name, last name, and total amount spent.
     */
    public List<String> getHighestSpender() {
        String sql =
                "SELECT SUM(invoice.total) AS total, customer.first_name AS name, customer.last_name AS surname " +
                        "FROM invoice " +
                        "INNER JOIN customer " +
                        "ON invoice.customer_id = customer.customer_id " +
                        "GROUP BY customer.first_name, customer.last_name " +
                        "ORDER BY SUM(invoice.total) DESC";

        List<String> customerData = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            if (result.next()) {
                customerData.add(result.getString("name"));
                customerData.add(result.getString("surname"));
                customerData.add(Double.toString(result.getDouble("total")));

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return customerData;
    }

    //Task 9
    /**
     * Returns most common genre of a single customer (or multiple genres).
     *
     * @param customerID The ID of the customer.
     * @return A list of strings representing the most popular genres for the customer.
     */
    public List<String> getMostPopularGenre(int customerID) {
        String sql =
                " SELECT customer.first_name, customer.last_name, genre.name, COUNT(genre.name) AS frequency " +
                        "FROM customer " +
                        "INNER JOIN invoice " +
                        "ON customer.customer_id = invoice.customer_id " +
                        "INNER JOIN invoice_line " +
                        "ON invoice.invoice_id = invoice_line.invoice_id " +
                        "INNER JOIN track " +
                        "ON invoice_line.track_id = track.track_id " +
                        "INNER JOIN genre " +
                        "ON track.genre_id = genre.genre_id " +
                        "WHERE customer.customer_id = ? " +
                        "GROUP BY customer.first_name, customer.last_name, genre.name " +
                        "ORDER BY frequency DESC ";
        List<String> popularGenre = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerID);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            int lastFrequency = 0;
            while (result.next()) {
                if (lastFrequency > result.getInt("frequency")) {
                    break;
                }else {
                    String genre = result.getString("name");
                    popularGenre.add(genre);
                    lastFrequency = result.getInt("frequency");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
        return popularGenre;
    }

    // Not part of the origin requirement, but part of the basic CRUD functions
    /**
     * Deletes a customer of a chosen ID.
     *
     * @param customerID The ID of the customer to delete.
     */
    @Override
    public void deleteById(Integer customerID) {
        String sql = "DELETE FROM customers WHERE customer_id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerID);
            statement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace(); // Print the stack trace in case of an SQL exception.
        }
    }
}
