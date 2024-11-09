package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                                            .addAddIngredientView()
                                            .addDeleteIngredientReminderView()
                                            .addInitialView()
                                            .addRecipeInfoView()
                                            .addRecipeListView()
                                            .addExpirationWarningView() // this could be a JDialog!!!!!!!!!!
                                            // TODO: add use cases!!!!!!!!!!!
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
