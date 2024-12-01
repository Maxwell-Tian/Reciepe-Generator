package data;

import entity.CommonIngredient;

import entity.Ingredient;
import use_case.addorcancelingredient.AddorCancelIngredientIngredientDataAccessInterface;
import use_case.delete_ingredient.DeleteIngredientIngredientDataAccessInterface;
import use_case.expired_food.ExpiredIngredientUserDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class txtConnector implements AddorCancelIngredientIngredientDataAccessInterface, DeleteIngredientIngredientDataAccessInterface, ExpiredIngredientUserDataAccessInterface {
    private final String fileName = "src/java/data/Data.txt";
//    private Ingredient ingredient;

//    public txtConnector(Ingredient ingredient) {
//        this.ingredient = ingredient;
//    }
    // just use the default constructor.

    @Override
    public void save(Ingredient ingredient) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            if (ingredient.getName().contains("|")){
                throw new IOException("name format error: " + ingredient.getName());
            }
            if (finder(ingredient)) {
                throw new IOException("repeated name error: " + ingredient.getName());
            }
            fw.write(ingredient.getName()+"|"+ingredient.getExpiryDate());
            fw.write(System.lineSeparator());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        try {
            if (!finder(ingredient)) {
                throw new IOException("ingredient not found: " + ingredient.getName());
            }
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (!line.split("\\|")[0].equals(ingredient.getName())) {
                    updatedLines.add(line);
                }
            }
            Files.write(Paths.get(fileName), updatedLines);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    @Override
    public LocalDate searchDate(Ingredient ingredient) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                if (!line.split("\\|")[0].equals(ingredient.getName())) {
                    return LocalDate.parse(line.split("\\|")[1]);
                }
            }
            throw new IOException("Ingredient not found: " + ingredient.getName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean finder(Ingredient ingredient) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String ingredientName = line.split("\\|")[0];
            if (ingredientName.equals(ingredient.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByIngredientName(String ingredientname) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String ingredientName = line.split("\\|")[0];
            if (ingredientName.equals(ingredientname)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Ingredient> getCurrentIngredients() throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        List<Ingredient> ingredients = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String ingredientName = line.split("\\|")[0];
            LocalDate date = LocalDate.parse(line.split("\\|")[1]);
            CommonIngredient newIngredient = new CommonIngredient(ingredientName, date);
            ingredients.add(newIngredient);
        }
        return ingredients;
    }
}
