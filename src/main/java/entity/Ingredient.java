package java.entity;

import java.time.LocalDate;

/**
 * The representation of an ingredient in our program.
 */
public interface Ingredient {

    /**
     * Return the name of the ingredient.
     * @return the name of the ingredient.
     */
    String getName();

    /**
     * Return the expiration date of the ingredient.
     * @return the expiration date of the ingredient.
     */
    LocalDate getExpiryDate();
}
