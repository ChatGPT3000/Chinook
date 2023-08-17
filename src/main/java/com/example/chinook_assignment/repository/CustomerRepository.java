package com.example.chinook_assignment.repository;

import com.example.chinook_assignment.entity.Customer;
import java.util.*;

// The CustomerRepository interface extends the CrudRepository and provides additional
// methods for managing Customer entities.
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    // Retrieves a list of customers with the given name.
    List<Customer> getCustomerByName(String name);

    // Retrieves a limited list of customers with an offset.
    List<Customer> getAllCustomersLimited(int limit, int offset);

    // Retrieves a list of the most common countries among customers.
    List<String> getMostCommonCountries();

    // Retrieves a list of the highest spenders among customers.
    List<String> getHighestSpender();

    // Retrieves a list of the most popular genres for a specific customer.
    List<String> getMostPopularGenre(int id);
}
