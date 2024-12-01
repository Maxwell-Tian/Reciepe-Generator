//package data;
//
//import entity.Ingredient;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class groupConnectorTXT {
//    private List<txtConnector> connectors = new ArrayList<>();
//
//    public groupConnectorTXT(List<Ingredient> ingredients) {
//        for (Ingredient ingredient : ingredients) {
//            connectors.add(new txtConnector(ingredient));
//        }
//    }
//
//    public void save() throws IOException {
//        for (txtConnector connector : connectors) {
//            connector.save();
//        }
//    }
//
//    public void delete() {
//        for (txtConnector connector : connectors) {
//            connector.delete();
//        }
//    }
//
//    public List<LocalDate> searchDate() {
//        List<LocalDate> dates = new ArrayList<>();
//        for (txtConnector connector : connectors) {
//            dates.add(connector.searchDate());
//        }
//        return dates;
//    }
//}
