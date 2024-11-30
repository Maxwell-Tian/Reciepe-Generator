package app;

import javax.swing.JFrame;
import java.io.FileNotFoundException;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.®
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                                            .addInitialView()
                                            .addAddIngredientView()
                                            .addRecipeListView()
                                            .addSAoCIUseCase()
                                            .addDeleteIngredientUseCase()
                                            .generateRecipeUseCase()
//                                            .addExpirationWarningView()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
