package app;

import view.*;

import javax.swing.*;
import java.awt.*;

import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private AddIngredientView addIngredientView;
    private DeleteIngredientReminderView deleteIngredientReminderView;
    private InitialView initialView;
    private RecipeListView recipeListView;
    private RecipeInfoView recipeInfoView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the AddIngredientView to the application.
     * @return this builder
     */
    public AppBuilder addAddIngredientView() {
        addIngredientView = new AddIngredientView();
        addIngredientView = new AddIngredientView(AddIngredientViewModel);
        cardPanel.add(addIngredientView, addIngredientView.getViewName());
        return this;
    }

    /**
     * Adds the DeleteIngredientReminderView to the application.
     * @return this builder
     */
    public AppBuilder addDeleteIngredientReminderView() {
        deleteIngredientReminderView = new DeleteIngredientReminderView();
        deleteIngredientReminderView = new DeleteIngredientReminderView(DeleteIngredientReminderViewModel);
        cardPanel.add(deleteIngredientReminderView, deleteIngredientReminderView, deleteIngredientReminderView.getViewName());
        return this;
    }

    /**
     * Adds the InitialView to the application.
     * @return this builder
     */
    public AppBuilder addInitialView() {
        initialView = new InitialView();
        initialView = new InitialView(InitialViewModel);
        cardPanel.add(initialView, initialView.getViewName());
        return this;
    }

    /**
     * Adds the RecipeListView to the application.
     * @return this builder
     */
    public AppBuilder addRecipeListView() {
        recipeListView = new RecipeListView();
        recipeListView = new RecipeListView(RecipeListViewModel);
        cardPanel.add(recipeListView, recipeListView.getViewName());
        return this;
    }

    /**
     * Adds the RecipeInfoView to the application.
     * @return this builder
     */
    public AppBuilder addRecipeInfoView() {
        recipeInfoView = new RecipeInfoView();
        recipeInfoView = new RecipeInfoView(RecipeInfoViewModel);
        cardPanel.add(recipeInfoView, recipeInfoView.getViewName());
        return this;
    }
}
