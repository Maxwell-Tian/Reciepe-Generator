package data;

import entity.CommonIngredient;
import entity.Ingredient;

import java.io.IOException;
import java.time.LocalDate;

public class SQLiteConnector implements Connector {
    private Ingredient ingredient;

    public SQLiteConnector(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public void delete() {

    }

    @Override
    public LocalDate searchDate() {
        return null;
    }
}
