package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Ingredient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.ErrorInfoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NutritionRequest {
    private String QUERY;
    private String URL = "https://api.api-ninjas.com/v1/nutrition?query=";
    private final String[] terms = {"name", "fat_total_g", "fat_saturated_g", "sodium_mg", "potassium_mg", "cholesterol_mg", "carbohydrates_total_g", "fiber_g","sugar_g"};

    public NutritionRequest(Ingredient ingredient){
        this.QUERY = "100g of " + ingredient.getName();
        this.URL = this.URL + this.QUERY;
    }

    public List<String> get_nutrition(){
        String feedback = Requesting();
        List<String> result = new ArrayList<>();
        List<String> temp = Response_analyzer(feedback);
        try {
            if (feedback != null&&!feedback.isEmpty()&&temp != null&&!temp.isEmpty()) {
                for(int i = 0; i < temp.size(); i++){
                    result.add(terms[i] + ": " + temp.get(i));
                }
                return result;
            }
            else{
                throw new IOException("Connection error / food not found");
            }
        } catch (Exception e) {
            ErrorInfoView view  = new ErrorInfoView();
            view.ShowErrorView(e.getMessage());;
        }
        return null;
    }

    private String Requesting(){
        okhttp3.Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .addHeader("X-Api-Key", "5gIA3EyO22xg0kERljfrIQ==vuIZ3bFV5aqJL5yV")
                .build();
        //sending request through api using okhttp3 package
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            ErrorInfoView view  = new ErrorInfoView();
            view.ShowErrorView(e.getMessage());
            return null;
        }
    }

    private List<String> Response_analyzer(String intake) {
        List<String> result = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(intake);
            if (rootNode != null && rootNode.isArray()) {
                for (String term : terms) {
                    JsonNode termNode = rootNode.get(0).get(term);
                    result.add(termNode.asText());
                }
            }
            else{
                throw new IOException("Nothing received from Response Message");
            }
            return result;
        } catch(Exception e){
                ErrorInfoView view = new ErrorInfoView();
                view.ShowErrorView(e.getMessage());
                return null;
        }
    }

}
