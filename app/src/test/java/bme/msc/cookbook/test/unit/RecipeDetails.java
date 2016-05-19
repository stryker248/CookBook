package bme.msc.cookbook.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import bme.msc.cookbook.BuildConfig;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeToFavouritesEvent;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.recipedetails.RecipeDetailsScreen;
import bme.msc.cookbook.ui.recipedetails.RecipeSummaryPresenter;
import bme.msc.cookbook.utils.RobolectricDaggerTestRunner;

import static bme.msc.cookbook.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RecipeDetails {
    private RecipeSummaryPresenter recipeSummaryPresenter;
    private RecipeDetailsScreen recipeDetailsScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        recipeDetailsScreen = mock(RecipeDetailsScreen.class);
        recipeSummaryPresenter = new RecipeSummaryPresenter();
        recipeSummaryPresenter.attachScreen(recipeDetailsScreen);
    }

    @Test
    public void testRateRecipe() {
        recipeSummaryPresenter.rateRecipe(1L, 5);

        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeDetailsScreen).updateRecipe(recipeCaptor.capture());
        assertTrue(recipeCaptor.getValue() != null);
    }

    @Test
    public void testAddRecipeToFavourites() {
        recipeSummaryPresenter.addRecipeToFavourites(new Recipe());

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(recipeDetailsScreen).showMessage(messageCaptor.capture());
        assertTrue(messageCaptor.getValue() != null);
    }

    @After
    public void tearDown() {
        recipeSummaryPresenter.detachScreen();
    }
}
