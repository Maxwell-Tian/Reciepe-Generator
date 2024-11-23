package use_case.expired_food;

import entity.Ingredient;
import java.util.List;

public interface CheckExpiredIngredientInputBoundary {
    List<Ingredient> execute();
}
