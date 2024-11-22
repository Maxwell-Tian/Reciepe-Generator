package data;

//import entity.Ingredient;

import java.io.IOException;
import java.time.LocalDate;

public interface Connector {

    void save() throws IOException;

    void delete();

    LocalDate searchDate();
}
