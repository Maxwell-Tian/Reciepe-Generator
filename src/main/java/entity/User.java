package main.java.entity;

import java.util.List;

/**
 * The representation of a user in our program.
 * It is equivalent to a fridge.
 * We call it a user just in case we need to tag the user as vegetarian or halal or anything else.
 */
public interface User {

    /**
     * Returns the List of Ingredients.
     * @return the List of Ingredients.
     */
    List<Ingredient> getIngredients();

    /**
     * Add a Ingredient to the user / fridge.
     * @param ingredient the ingredient to add
     */
    void addIngredient(Ingredient ingredient);
}
