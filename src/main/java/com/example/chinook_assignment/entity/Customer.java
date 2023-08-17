package com.example.chinook_assignment.entity;

/**
 * A record representing a customer entity.
 */
public record Customer(
        /**
         * The unique identifier for the customer.
         */
        int id,

        /**
         * The first name of the customer.
         */
        String firstName,

        /**
         * The last name of the customer.
         */
        String lastName,

        /**
         * The country of the customer.
         */
        String country,

        /**
         * The postal code of the customer's address.
         */
        String postalCode,

        /**
         * The phone number of the customer.
         */
        String phoneNumber,

        /**
         * The email address of the customer.
         */
        String email
) {
}

