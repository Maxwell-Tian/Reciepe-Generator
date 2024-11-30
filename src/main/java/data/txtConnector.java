package data;

//import entity.CommonIngredient;

import entity.CommonIngredient;
import entity.Ingredient;
import view.ErrorInfoView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class txtConnector implements Connector {
    private final String fileName = "Data.txt";
    private Ingredient ingredient;

    public txtConnector(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void save() {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            if (ingredient.getName().contains("|")){
                throw new IOException("name format error: " + this.ingredient.getName());
            }
            if (finder()) {
                throw new IOException("repeated name error: " + this.ingredient.getName());
            }
            fw.write(ingredient.getName()+"|"+ingredient.getExpiryDate());
            fw.write(System.lineSeparator());
            fw.close();
        } catch (Exception e) {
            ErrorInfoView view  = new ErrorInfoView();
            view.ShowErrorView(e.getMessage());;
        }
    }

    @Override
    public void delete() {
        try {
            if (!finder()) {
                throw new IOException("ingredient not found: " + this.ingredient.getName());
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
            ErrorInfoView view  = new ErrorInfoView();
            view.ShowErrorView(e.getMessage());;
        }
    }

    @Override
    public LocalDate searchDate() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                if (!line.split("\\|")[0].equals(ingredient.getName())) {
                    return LocalDate.parse(line.split("\\|")[1]);
                }
            }
            throw new IOException("Ingredient not found: " + this.ingredient.getName());
        }
        catch (IOException e) {
            ErrorInfoView view  = new ErrorInfoView();
            view.ShowErrorView(e.getMessage());;
        }
        return null;
    }

    private boolean finder() throws FileNotFoundException {
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
}
