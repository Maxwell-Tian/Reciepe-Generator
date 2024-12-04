package use_case.nutrition;
import interface_adapter.NutritionViewModel.NutritionPresenter;
import interface_adapter.NutritionViewModel.NutritionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.initial.InitialViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class NutritionPresenterTest {
    private NutritionPresenter presenter;
    private InitialViewModel mockInitialViewModel;
    private NutritionViewModel mockNutritionViewModel;
    private ViewManagerModel mockViewManagerModel;

    @BeforeEach
    void setUp() {
        mockInitialViewModel = mock(InitialViewModel.class);
        mockNutritionViewModel = mock(NutritionViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);
        presenter = new NutritionPresenter(mockInitialViewModel, mockNutritionViewModel, mockViewManagerModel);
    }

    @Test
    void testPresentNutritionUpdatesState() {
        List<String> nutritionInfo = Arrays.asList("Info1", "Info2");
        presenter.presentNutrition(nutritionInfo);

        verify(mockNutritionViewModel).getState(); // Ensure the state is updated
        verify(mockViewManagerModel).setState(mockNutritionViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testSwitchToInitialView() {
        when(mockInitialViewModel.getViewName()).thenReturn("InitialView");
        presenter.switchToInitialView();
        verify(mockViewManagerModel).setState("InitialView");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPresentErrorMessage() {
        presenter.presentErrorMessage("Error occurred");
        // Ensure additional error handling logic (if implemented) is tested
    }
}
