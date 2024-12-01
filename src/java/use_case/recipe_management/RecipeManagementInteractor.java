package use_case.recipe_management;

public class RecipeManagementInteractor implements RecipeManagementInputBoundary {
    private final RecipeManagementOutputBoundary outputBoundary;

    public RecipeManagementInteractor(RecipeManagementOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    @Override
    public void switchToInitialView() {
        outputBoundary.switchToInitialView();
    }

    @Override
    public void switchToRecipeListView() { outputBoundary.switchToRecipeListView(); }

    @Override
    public void switchToRecipeInfoView() {
        outputBoundary.switchToRecipeInfoView();
    }
}
