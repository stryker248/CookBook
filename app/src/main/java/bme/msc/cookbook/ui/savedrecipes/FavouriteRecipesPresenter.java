package bme.msc.cookbook.ui.savedrecipes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.interactor.recipes.event.GetFavouriteRecipesEvent;
import bme.msc.cookbook.ui.Presenter;

public class FavouriteRecipesPresenter extends Presenter<FavouriteRecipesScreen> {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    public void attachScreen(FavouriteRecipesScreen screen) {
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

    public void removeRecipeFromFavourites(final long id) {
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
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showRecipes(event.getRecipes());
            }
        }
    }
}
