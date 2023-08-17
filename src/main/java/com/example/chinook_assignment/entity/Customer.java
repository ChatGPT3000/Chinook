package com.example.chinook_assignment.entity;

//Record for customer
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
