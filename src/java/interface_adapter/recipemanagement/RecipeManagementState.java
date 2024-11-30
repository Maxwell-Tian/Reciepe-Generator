package interface_adapter.recipemanagement;

public class RecipeManagementState {
    private String errorMessage;

    @Override
    public String toString() {
        return "RecipeManagementState{}";
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
