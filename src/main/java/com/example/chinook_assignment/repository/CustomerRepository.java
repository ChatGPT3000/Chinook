package com.example.chinook_assignment.repository;

import com.example.chinook_assignment.entity.Customer;
import java.util.*;

/**
 * The CustomerRepository interface extends the CrudRepository and provides additional
 * methods for managing Customer entities.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    /**
     * Retrieves a list of customers with the given name.
     *
     * @param name The name to search for.
     * @return A list of customers with the specified name.
     */
    List<Customer> getCustomerByName(String name);

    /**
     * Retrieves a limited list of customers with an offset.
     *
     * @param limit  The maximum number of customers to retrieve.
     * @param offset The offset from where to start retrieving customers.
     * @return A list of customers with the specified limit and offset.
     */
    List<Customer> getAllCustomersLimited(int limit, int offset);

    /**
     * Retrieves a list of the most common countries among customers.
     *
     * @return A list of the most common countries among customers.
     */
    List<String> getMostCommonCountries();

    /**
     * Retrieves a list of the highest spenders among customers.
     *
     * @return A list of the highest spenders among customers.
     */
    List<String> getHighestSpender();

    /**
     * Retrieves a list of the most popular genres for a specific customer.
     *
     * @param id The ID of the customer.
     * @return A list of the most popular genres for the specified customer.
     */
    List<String> getMostPopularGenre(int id);
}

