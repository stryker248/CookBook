package bme.msc.cookbook.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import bme.msc.cookbook.BuildConfig;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.newrecipe.NewRecipePresenter;
import bme.msc.cookbook.ui.newrecipe.NewRecipeScreen;
import bme.msc.cookbook.utils.RobolectricDaggerTestRunner;

import static bme.msc.cookbook.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class NewRecipe {
    private NewRecipePresenter newRecipePresenter;
    private NewRecipeScreen newRecipeScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        newRecipeScreen = mock(NewRecipeScreen.class);
        newRecipePresenter = new NewRecipePresenter();
        newRecipePresenter.attachScreen(newRecipeScreen);
    }

    @Test
    public void testRefreshCategories() {
        newRecipePresenter.refreshCategories();

        ArgumentCaptor<List> categoriesCaptor = ArgumentCaptor.forClass(List.class);
        verify(newRecipeScreen).showCategories(categoriesCaptor.capture());
        assertTrue(categoriesCaptor.getValue().size() > 0);
    }

    @Test
    public void testAddNewRecipe() {
        newRecipePresenter.addNewRecipe(new bme.msc.cookbook.model.apimodel.NewRecipe());

        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(newRecipeScreen).showNewRecipe(recipeCaptor.capture());
        assertTrue(recipeCaptor.getValue() != null);
    }

    @After
    public void tearDown() {
        newRecipePresenter.detachScreen();
    }
}
