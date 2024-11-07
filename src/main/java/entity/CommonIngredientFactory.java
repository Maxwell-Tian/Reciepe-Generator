package entity;

import java.time.LocalDate;

/**
 * Factory for creating CommonIngredient objects.
 */
public class CommonIngredientFactory implements IngredientFactory {

    @Override
    public Ingredient create(String name, LocalDate expiryDate) {
        return new CommonIngredient(name, expiryDate);
    };
}
