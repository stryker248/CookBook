package bme.msc.cookbook.ui.recipes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeToFavouritesEvent;
import bme.msc.cookbook.interactor.recipes.event.GetRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.RecipeAddedToFavouritesEvent;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.Presenter;

public class RecipesPresenter extends Presenter<RecipesScreen> {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    public void attachScreen(RecipesScreen screen) {
        super.attachScreen(screen);
        CookBookApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void refreshRecipes() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.getRecipes();
            }
        });
    }

    public void searchRecipes(final String searchText) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.searchRecipes(searchText);
            }
        });
    }

    public void addRecipeToFavourites(final Recipe recipe) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.addRecipeToFavourites(recipe);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetRecipesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showRecipes(event.getRecipes());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final AddRecipeToFavouritesEvent event) {
        addRecipeToFavourites(event.getRecipe());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final RecipeAddedToFavouritesEvent event) {
        screen.showMessage(event.getMessage());
    }
}
