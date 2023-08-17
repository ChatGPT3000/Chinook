package com.example.chinook_assignment.repository;

import java.util.List;

// A basic repository interface for CRUD operations on entities.

// @param <T>  The entity type.
// @param <ID> The ID type of the entity.

public interface CrudRepository<T, ID> {

    // Saves an entity.
    int save(T entity);

   //Retrieves an entity by its ID.
    T findById(ID id);

    //Retrieves all entities.
    List<T> findAll();

    //Updates an entity's email by ID.
    // @return The number of entities updated (0 or 1).
    int update(ID id, String newEmail);

     //Deletes an entity by its ID.
    void deleteById(ID id);
}
