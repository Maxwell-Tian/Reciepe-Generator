package data;

import entity.Ingredient;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class groupConnectorSQL {
    private List<SQLiteConnector> connectors = new ArrayList<>();

    public groupConnectorSQL(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            connectors.add(new SQLiteConnector(ingredient));
        }
    }

    public void save() throws IOException {
        for (SQLiteConnector connector : connectors) {
            connector.save();
        }
    }

    public void delete() {
        for (SQLiteConnector connector : connectors) {
            connector.delete();
        }
    }

    public List<LocalDate> searchDate() {
        List<LocalDate> dates = new ArrayList<>();
        for (SQLiteConnector connector : connectors) {
            dates.add(connector.searchDate());
        }
        return dates;
    }

}
