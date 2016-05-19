package bme.msc.cookbook.ui.main;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.interactor.recipes.event.GetFavouriteRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.RecipeRemovedFromFavouritesEvent;
import bme.msc.cookbook.interactor.recipes.event.RemoveRecipeFromFavouritesEvent;
import bme.msc.cookbook.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    public void attachScreen(MainScreen screen) {
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
                recipesInteractor.getFavouriteRecipes();
            }
        });
    }

    public void removeRecipeFromFavourites(final Long id) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.removeRecipeFromFavourites(id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetFavouriteRecipesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showRecipes(event.getRecipes());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final RemoveRecipeFromFavouritesEvent event) {
        removeRecipeFromFavourites(event.getId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final RecipeRemovedFromFavouritesEvent event) {
        screen.removeRecipe(event.getId());
        screen.showMessage(event.getMessage());
    }
}
