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
    public static void main(String[] args) throws FileNotFoundException {
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                                            .addInitialView()
                                            .addAddIngredientView()
                                            .addRecipeInfoView()
                                            .addRecipeListView()
                                            .addNutritionView()
                                            .addExpirationWarningView()
                                            .addSAoCIUseCase()
                                            .addDeleteIngredientUseCase()
                                            .addSearchNutritionUseCase()
                                            .addgenerateRecipeUseCase()
                                            .addExpirationWarningUseCase()

                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
