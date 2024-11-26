package api;

import entity.CommonIngredient;
import entity.Ingredient;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.management.HotSpotDiagnosticMXBean.ThreadDumpFormat.JSON;

public class initiate {
    private static final String APP_ID = "6b5710fe";
    private static final String APP_KEY = "8d04006d4c25a92a53ba4bc90e36bc03";
    private static final String USER_ID = "MaxwellsCat";
    private static final String URL = "https://api.edamam.com/api/recipes/v2?type=public&app_id=" + APP_ID +
            "&app_key=" + APP_KEY + "&q=chicken&diet=balanced&health=alcohol-free&health=dairy-free&cuisineType=" +
            "American&cuisineType=Chinese&cuisineType=Indian&mealType=Lunch&mealType=Dinner&calories=100-1000&time=0-100";
    private List<CommonIngredient> ingredients = new ArrayList<>();
    // 用于搜索的ingredients

    private String important = "ingredientLines";

    public static void main(String[] args) {
        System.out.println(start_connect());
    }
    public static String start_connect(){
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .addHeader("Edamam-Account-User", "MaxwellsCat")
                .build();
        //sending request through api using okhttp3 package
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
             return e.getMessage();
        }
    }
}
