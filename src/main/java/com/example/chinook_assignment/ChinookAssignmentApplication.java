package com.example.chinook_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChinookAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChinookAssignmentApplication.class, args);
    }

    private void testTask1() {
        PostGradDAO postGradDAO = new PostGradDAO();
        List<Customer> allCustomers = postGradDAO.getAllCustomers();
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    private void testTask2() {
        PostGradDAO postGradDAO = new PostGradDAO();
        Customer customer = postGradDAO.getCustomerById(1);
        System.out.println(customer.toString());
    }

    private void testTask3() {
        PostGradDAO postGradDAO = new PostGradDAO();
        List<Customer> allCustomers = postGradDAO.getCustomerByName("John");
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    private void testTask4() {
        PostGradDAO postGradDAO = new PostGradDAO();
        List<Customer> allCustomers = postGradDAO.getAllCustomersLimited(5, 3);
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    private void testTask5() {
        PostGradDAO postGradDAO = new PostGradDAO();
        // Id number provided is arbitrary, as id will be auto incremented in the database
        postGradDAO.insert(new Customer(0, "Harry", "Potter", "UK", "222 33", "1234567890", "harry.potter@wizardmail.com"));
    }

    private void testTask6() {
        PostGradDAO postGradDAO = new PostGradDAO();
        System.out.println(postGradDAO.getCustomerById(1));
        System.out.println(postGradDAO.update(1, "new.email@gmail.com"));
    }

    private void testTask7() {
        PostGradDAO postGradDAO = new PostGradDAO();
        List<String> countries = postGradDAO.getMostCommonCountries();
        for ( String country : countries ) {
            System.out.println(country);
        }
    }

    private void testTask8() {
        PostGradDAO postGradDAO = new PostGradDAO();
        List<String> customerData = postGradDAO.getHighestSpender();
        for ( String data : customerData ) {
            System.out.println(data);
        }
    }

    private void testTask9() {
        PostGradDAO postGradDAO = new PostGradDAO();
        List<String> genres = postGradDAO.getMostPopularGenre(1);
        for ( String genre : genres ) {
            System.out.println(genre);
        }
    }

}
