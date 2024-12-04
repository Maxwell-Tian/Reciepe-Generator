package api;

import entity.CommonIngredient;
import entity.Ingredient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class API_UseCase_Demo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        CommonIngredient ingredient = new CommonIngredient("tomato",date);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        RecipeRequest request = new RecipeRequest(ingredients);
//        System.out.println(request.Searching_Recipe().toString());
        for (List<String> sublist: request.Searching_Recipe()){
            for (String str : sublist){
                System.out.println(str);
            }
        }
        NutritionRequest nutritionRequest = new NutritionRequest(ingredient);
        System.out.println(nutritionRequest.get_nutrition());
    }

//    Ramen Soup with Vegetables, Ramped Up Ramen


}


