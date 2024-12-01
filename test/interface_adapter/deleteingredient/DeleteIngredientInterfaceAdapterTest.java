package interface_adapter.deleteingredient;

import data_access.InMemoryIngredientDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.addorcancelingredient.AddorCancelIngredientViewModel;
import interface_adapter.initial.InitialViewModel;
import interface_adapter.initial.InitialState;
import interface_adapter.recipemanagement.RecipeManagementViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.delete_ingredient.*;

import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class DeleteIngredientInterfaceAdapterTest {
    private InitialViewModel mockViewModel;
    private AddorCancelIngredientViewModel mockAddorCancelIngredientViewModel;
    private RecipeManagementViewModel mockRecipeManagementViewModel;
    private ViewManagerModel mockViewManagerModel;

    private DeleteIngredientPresenter presenter;
    private DeleteIngredientInputBoundary mockDeleteIngredientInteractor;
    private DeleteIngredientController controller;

    @BeforeEach
    void setUp() {
        mockViewModel = mock(InitialViewModel.class);
        mockAddorCancelIngredientViewModel = mock(AddorCancelIngredientViewModel.class);
        mockRecipeManagementViewModel = mock(RecipeManagementViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);

        mockDeleteIngredientInteractor = mock(DeleteIngredientInputBoundary.class);
        controller = new DeleteIngredientController(mockDeleteIngredientInteractor);

        presenter = new DeleteIngredientPresenter(
                mockViewModel,
                mockAddorCancelIngredientViewModel,
                mockRecipeManagementViewModel,
                mockViewManagerModel
        );
    }

    @Test
    void testControllerExecuteDeletesIngredientSuccessfully() throws FileNotFoundException {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient mockIngredient = factory.create("tomato", LocalDate.parse("2026-12-11"));

        List<Ingredient> ingredients = Collections.singletonList(mockIngredient);

        controller.execute(ingredients, mockIngredient);

        verify(mockDeleteIngredientInteractor).execute(
                argThat(inputData -> inputData.getIngredient().equals(mockIngredient))
        );
    }

    @Test
    void testControllerExecuteThrowsFileNotFoundException() throws FileNotFoundException {
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient mockIngredient = factory.create("tomato", LocalDate.parse("2026-12-11"));
        List<Ingredient> ingredients = Collections.singletonList(mockIngredient);

        doThrow(FileNotFoundException.class).when(mockDeleteIngredientInteractor).execute(any());

        org.junit.jupiter.api.Assertions.assertThrows(FileNotFoundException.class, () ->
                controller.execute(ingredients, mockIngredient));
    }

    @Test
    void testControllerSwitchToAddIngredientView() {
        controller.switchToAddIngredientView();
        verify(mockDeleteIngredientInteractor).switchToAddIngredientView();
    }

    @Test
    void testControllerSwitchToRecipeView() {
        controller.switchToRecipeView();
        verify(mockDeleteIngredientInteractor).switchToRecipeView();
    }


    @Test
    void testPrepareSuccessView() {
        InMemoryIngredientDataAccessObject ingredientRepository = new InMemoryIngredientDataAccessObject();
        IngredientFactory factory = new CommonIngredientFactory();
        Ingredient ingredient = factory.create("tomato", LocalDate.parse("2024-12-11"));
        ingredientRepository.save(ingredient);

        InitialState mockInitialState = mock(InitialState.class);
        DeleteIngredientOutputData mockResponse = mock(DeleteIngredientOutputData.class);
        when(mockViewModel.getState()).thenReturn(mockInitialState);
        when(mockResponse.getIngredient()).thenReturn(ingredient);

        presenter.prepareSuccessView(mockResponse);

        verify(mockInitialState).deleteIngredient(ingredient);
        verify(mockViewModel).setState(mockInitialState);
        verify(mockViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setState(mockViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        InitialState mockInitialState = mock(InitialState.class);
        String errorMessage = "Ingredient not found";
        when(mockViewModel.getState()).thenReturn(mockInitialState);

        presenter.prepareFailView(errorMessage);

        verify(mockInitialState).setError(errorMessage);
        verify(mockAddorCancelIngredientViewModel).firePropertyChanged();
    }

    @Test
    void testSwitchToAddIngredientView() {
        when(mockAddorCancelIngredientViewModel.getViewName()).thenReturn("AddIngredientView");

        presenter.switchToAddIngredientView();

        verify(mockViewManagerModel).setState("AddIngredientView");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testSwitchToRecipeView() {
        when(mockRecipeManagementViewModel.getViewName()).thenReturn("RecipeManagementView");

        presenter.switchToRecipeView();

        verify(mockViewManagerModel).setState("RecipeManagementView");
        verify(mockViewManagerModel).firePropertyChanged();
    }

}