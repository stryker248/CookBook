package bme.msc.cookbook.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import bme.msc.cookbook.BuildConfig;
import bme.msc.cookbook.ui.savedrecipes.OwnRecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.OwnRecipesScreen;
import bme.msc.cookbook.utils.RobolectricDaggerTestRunner;

import static bme.msc.cookbook.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class OwnRecipes {
    private OwnRecipesPresenter ownRecipesPresenter;
    private OwnRecipesScreen ownRecipesScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        ownRecipesScreen = mock(OwnRecipesScreen.class);
        ownRecipesPresenter = new OwnRecipesPresenter();
        ownRecipesPresenter.attachScreen(ownRecipesScreen);
    }

    @Test
    public void testRefreshRecipes() {
        ownRecipesPresenter.refreshRecipes("email");

        ArgumentCaptor<List> recipesCaptor = ArgumentCaptor.forClass(List.class);
        verify(ownRecipesScreen).showRecipes(recipesCaptor.capture());
        assertTrue(recipesCaptor.getValue().size() > 0);
    }

    @After
    public void tearDown() {
        ownRecipesPresenter.detachScreen();
    }
}
