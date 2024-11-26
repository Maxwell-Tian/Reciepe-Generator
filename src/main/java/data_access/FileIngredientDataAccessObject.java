package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import entity.Ingredient;
import entity.IngredientFactory;
import use_case.addorcancelingredient.AddorCancelIngredientIngredientDataAccessInterface;
import use_case.delete_ingredient.DeleteIngredientIngredientDataAccessInterface;

public class FileIngredientDataAccessObject implements AddorCancelIngredientIngredientDataAccessInterface,
        DeleteIngredientIngredientDataAccessInterface {
    private static final String HEADER = "ingredient name,expiry date";

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Ingredient> ingredients = new HashMap<>();
    private List<Ingredient> currentIngredients = new ArrayList<>();

    public FileIngredientDataAccessObject(String csvPath, IngredientFactory ingredientFactory) throws IOException {
        csvFile = new File(csvPath);
        headers.put("ingredient name", 0);
        headers.put("expiry date", 1);
        ensureFileExists();

        if (csvFile.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                final String header = reader.readLine();

                if (!header.equals(HEADER)) {
                    throw new RuntimeException(String.format("header should be:%n%s%nbut was:%n%s", HEADER, header));
                }

                String row;
                while ((row = reader.readLine()) != null) {
                    final String[] col = row.split(",");
                    final String ingredientName = col[headers.get("ingredient name")];
                    String expiryDateString = col[headers.get("expiry date")];
                    LocalDate expiryDate = LocalDate.parse(expiryDateString, DateTimeFormatter.ISO_LOCAL_DATE);
                    final Ingredient ingredient = ingredientFactory.create(ingredientName, expiryDate);
                    ingredients.put(ingredientName, ingredient);
                    currentIngredients.add(ingredient);  // Add to currentIngredients list
                }
            }
        }
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Write the header
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            // Write the remaining ingredients after deletion
            for (Ingredient ingredient : ingredients.values()) {
                String line = String.format("%s,%s", ingredient.getName(), ingredient.getExpiryDate().toString());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to save ingredients", ex);
        }
    }

    private void ensureFileExists() {
        try {
            if (!csvFile.exists()) {
                File parentDir = csvFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                if (csvFile.createNewFile()) {
                    System.out.println("File created at: " + csvFile.getAbsolutePath());
                    initializeFileContent(csvFile); // Add headers or default content if necessary
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create the file at " + csvFile.getAbsolutePath(), e);
        }
    }

    private void initializeFileContent(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("IngredientName,ExpiryDate\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize file content for " + file.getPath(), e);
        }
    }


    @Override
    public boolean existsByIngredientName(String ingredientName) {
        return ingredients.containsKey(ingredientName);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
        currentIngredients.add(ingredient);
        save();
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return currentIngredients;
    }

    @Override
    public List<Ingredient> getCurrentIngredients() {
        return currentIngredients;
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient.getName());
        currentIngredients.remove(ingredient);
        save();
    }
}