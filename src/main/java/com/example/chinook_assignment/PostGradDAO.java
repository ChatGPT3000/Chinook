package com.example.chinook_assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostGradDAO {
    // Default values that can be overridden
    private String url = "jdbc:postgresql://localhost:5432/Assignment-2-db";
    private String username = "postgres";
    private String password = "postgres";

    public PostGradDAO() {
    }

    public PostGradDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void test() {
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Task 1
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
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
            e.printStackTrace();
        }
        return customers;
    }

    // Task 2
    public Customer getCustomerById(int customerID) {
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
            e.printStackTrace();
        }
        return customer;
    }

    // Task 3
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
            e.printStackTrace();
        }
        return customers;
    }

    //Task 4
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
            e.printStackTrace();
        }
        return customers;
    }

    // Task 5
    public int insert(Customer customer) {
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
            e.printStackTrace();
        }
        return result;
    }

    // Task 6
    public int update(int id, String newEmail) {
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
            e.printStackTrace();
        }
        return result;
    }

    // Task 7
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
            e.printStackTrace();
        }
        return commonCountries;
    }

    //Task 8
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
            e.printStackTrace();
        }
        return customerData;
    }

    //Task 9
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
            e.printStackTrace();
        }
        return popularGenre;
    }
}

