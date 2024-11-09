package java.entity;

import java.time.LocalDate;

/**
 * Factory for creating recipes.
 */
public interface IngredientFactory {
    /**
     * Creates a new Ingredient.
     * @param name the name of the Ingredient.
     * @param expiryDate the date of expiration.
     * @return the new Ingredient.
     */
    Ingredient create(String name, LocalDate expiryDate);
}
