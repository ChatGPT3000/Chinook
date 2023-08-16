//1. Read all the customers in the database, this should display their: Id, first name, last name, country,
//postal code, phone number and email.

package com.example.chinook_assignment;

public record Customer(
        int id,
        String firstName,
        String lastName,
        String country,
        String postalCode,
        String phoneNumber,
        String email
) {
}
