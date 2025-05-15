package org.example.digitaltirtha.dao;

import java.util.List;

/**
 * Generic DAO interface that defines common CRUD operations.
 *
 * @param <T> The entity type
 * @param <ID> The type of the entity's identifier
 */
public interface GenericDAO<T, ID> {
    
    /**
     * Creates a new entity.
     *
     * @param entity The entity to create
     * @return The created entity with ID assigned
     */
    T create(T entity);
    
    /**
     * Retrieves an entity by ID.
     *
     * @param id The ID of the entity to retrieve
     * @return The entity, or null if not found
     */
    T findById(ID id);
    
    /**
     * Retrieves all entities.
     *
     * @return A list of all entities
     */
    List<T> findAll();
    
    /**
     * Updates an existing entity.
     *
     * @param entity The entity to update
     * @return The updated entity, or null if not found
     */
    T update(T entity);
    
    /**
     * Deletes an entity by ID.
     *
     * @param id The ID of the entity to delete
     * @return true if deleted, false if not found
     */
    boolean delete(ID id);
}