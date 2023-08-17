package com.example.chinook_assignment;

import com.example.chinook_assignment.entity.Customer;
import com.example.chinook_assignment.repository.CustomerRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChinookAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChinookAssignmentApplication.class, args);
    }

    private void testTask1() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<Customer> allCustomers = customerRepository.findAll();
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    private void testTask2() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = customerRepository.findById(1);
        System.out.println(customer.toString());
    }

    private void testTask3() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<Customer> allCustomers = customerRepository.getCustomerByName("John");
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    private void testTask4() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<Customer> allCustomers = customerRepository.getAllCustomersLimited(5, 3);
        for (Customer customer : allCustomers) {
            System.out.println(customer.toString());
        }
    }

    private void testTask5() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        // Id number provided is arbitrary, as id will be auto incremented in the database
        customerRepository.save(new Customer(0, "Harry", "Potter", "UK", "222 33", "1234567890", "harry.potter@wizardmail.com"));
    }

    private void testTask6() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        System.out.println(customerRepository.findById(1));
        System.out.println(customerRepository.update(1, "new.email@gmail.com"));
        System.out.println(customerRepository.findById(1));
    }

    private void testTask7() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<String> countries = customerRepository.getMostCommonCountries();
        for ( String country : countries ) {
            System.out.println(country);
        }
    }

    private void testTask8() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<String> customerData = customerRepository.getHighestSpender();
        for ( String data : customerData ) {
            System.out.println(data);
        }
    }

    private void testTask9() {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<String> genres = customerRepository.getMostPopularGenre(1);
        for ( String genre : genres ) {
            System.out.println(genre);
        }
    }
}
