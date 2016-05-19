package bme.msc.cookbook.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import bme.msc.cookbook.BuildConfig;
import bme.msc.cookbook.ui.recipes.RecipesPresenter;
import bme.msc.cookbook.ui.recipes.RecipesScreen;
import bme.msc.cookbook.utils.RobolectricDaggerTestRunner;

import static bme.msc.cookbook.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RecipesTest {
    private RecipesPresenter recipesPresenter;
    private RecipesScreen recipesScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        recipesScreen = mock(RecipesScreen.class);
        recipesPresenter = new RecipesPresenter();
        recipesPresenter.attachScreen(recipesScreen);
    }

    @Test
    public void testRefreshRecipes() {
        recipesPresenter.refreshRecipes();

        ArgumentCaptor<List> recipesCaptor = ArgumentCaptor.forClass(List.class);
        verify(recipesScreen).showRecipes(recipesCaptor.capture());
        assertTrue(recipesCaptor.getValue().size() > 0);
    }

    @Test
    public void testSearchRecipes() {
        recipesPresenter.searchRecipes("Recipe");

        ArgumentCaptor<List> recipesCaptor = ArgumentCaptor.forClass(List.class);
        verify(recipesScreen).showRecipes(recipesCaptor.capture());
        assertTrue(recipesCaptor.getValue().size() > 0);
    }

    @After
    public void tearDown() {
        recipesPresenter.detachScreen();
    }
}
