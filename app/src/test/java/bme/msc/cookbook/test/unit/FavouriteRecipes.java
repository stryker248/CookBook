package bme.msc.cookbook.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import bme.msc.cookbook.BuildConfig;
import bme.msc.cookbook.ui.savedrecipes.FavouriteRecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.FavouriteRecipesScreen;
import bme.msc.cookbook.utils.RobolectricDaggerTestRunner;

import static bme.msc.cookbook.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FavouriteRecipes {
    private FavouriteRecipesPresenter favouriteRecipesPresenter;
    private FavouriteRecipesScreen favouriteRecipesScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        favouriteRecipesScreen = mock(FavouriteRecipesScreen.class);
        favouriteRecipesPresenter = new FavouriteRecipesPresenter();
        favouriteRecipesPresenter.attachScreen(favouriteRecipesScreen);
    }

    @Test
    public void testRefreshRecipes() {
        favouriteRecipesPresenter.refreshRecipes();

        ArgumentCaptor<List> recipesCaptor = ArgumentCaptor.forClass(List.class);
        verify(favouriteRecipesScreen).showRecipes(recipesCaptor.capture());
        assertTrue(recipesCaptor.getValue().size() > 0);
    }

    @Test
    public void testRemoveRecipeFromFavourites() {
        favouriteRecipesPresenter.removeRecipeFromFavourites(1L);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(favouriteRecipesScreen).removeRecipe(1L);
        verify(favouriteRecipesScreen).showMessage(messageCaptor.capture());
        assertTrue(messageCaptor.getValue().length() > 0);
    }

    @After
    public void tearDown() {
        favouriteRecipesPresenter.detachScreen();
    }
}
