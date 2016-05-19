package bme.msc.cookbook.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import bme.msc.cookbook.BuildConfig;
import bme.msc.cookbook.interactor.recipes.event.RemoveRecipeFromFavouritesEvent;
import bme.msc.cookbook.ui.main.MainPresenter;
import bme.msc.cookbook.ui.main.MainScreen;
import bme.msc.cookbook.utils.RobolectricDaggerTestRunner;

import static bme.msc.cookbook.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainTest {
    private MainPresenter mainPresenter;
    private MainScreen mainScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachScreen(mainScreen);
    }

    @Test
    public void testRefreshRecipes() {
        mainPresenter.refreshRecipes();

        ArgumentCaptor<List> recipesCaptor = ArgumentCaptor.forClass(List.class);
        verify(mainScreen).showRecipes(recipesCaptor.capture());
        assertTrue(recipesCaptor.getValue().size() > 0);
    }

    @Test
    public void testRemoveRecipeFromFavourites() {
        mainPresenter.removeRecipeFromFavourites(1L);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(mainScreen).removeRecipe(1L);
        verify(mainScreen).showMessage(messageCaptor.capture());
        assertTrue(messageCaptor.getValue() != null);
    }

    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }
}
