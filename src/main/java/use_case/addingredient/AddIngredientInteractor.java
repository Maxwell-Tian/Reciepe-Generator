package use_case.addingredient;

import entity.Ingredient;
import entity.IngredientFactory;
import entity.User;
import java.time.LocalDate;

public class AddIngredientInteractor implements AddIngredientInputBoundary {
    private final User user;
    private final IngredientFactory ingredientFactory;
    private final AddIngredientOutputBoundary outputBoundary;

    public AddIngredientInteractor(User user, IngredientFactory ingredientFactory, AddIngredientOutputBoundary outputBoundary) {
        this.user = user;
        this.ingredientFactory = ingredientFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addIngredient(String ingredientName, LocalDate expireDate) {
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be empty");
        }

        if (expireDate == null) {
            throw new IllegalArgumentException("Expire date cannot be empty");
        }

        Ingredient ingredient = ingredientFactory.create(ingredientName, expireDate);
        user.addIngredient(ingredient);
        AddIngredientOutputData outputData = new AddIngredientOutputData(ingredientName, expireDate);
        outputBoundary.presentAddIngredientResult(outputData);
    }
}


