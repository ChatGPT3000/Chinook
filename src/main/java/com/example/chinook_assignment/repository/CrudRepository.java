package com.example.chinook_assignment.repository;

import java.util.List;

/**
 * A basic repository interface for CRUD operations on entities.
 *
 * @param <T>  The entity type.
 * @param <ID> The ID type of the entity.
 */
public interface CrudRepository<T, ID> {

    /**
     * Saves an entity.
     *
     * @param entity The entity to be saved.
     * @return The number of entities saved (0 or 1).
     */
    int save(T entity);

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The retrieved entity, or null if not found.
     */
    T findById(ID id);

    /**
     * Retrieves all entities.
     *
     * @return A list of all entities.
     */
    List<T> findAll();

    /**
     * Updates an entity's email by ID.
     *
     * @param id       The ID of the entity to update.
     * @param newEmail The new email value.
     * @return The number of entities updated (0 or 1).
     */
    int update(ID id, String newEmail);

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     */
    void deleteById(ID id);
}
