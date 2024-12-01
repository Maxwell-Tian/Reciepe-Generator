package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CommonIngredient;
import entity.Ingredient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        CommonIngredient ingredient = new CommonIngredient("Potato", date);
        CommonIngredient ingredient1 = new CommonIngredient("Egg", date);
        CommonIngredient ingredient2 = new CommonIngredient("Tomato", date);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        API_request request = new API_request(ingredients, "", "Asian", "");
        System.out.println(request.Searching_Recipe().toString());
    }




}


