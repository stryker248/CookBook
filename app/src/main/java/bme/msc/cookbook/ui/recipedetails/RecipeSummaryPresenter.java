package bme.msc.cookbook.ui.recipedetails;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeToFavouritesEvent;
import bme.msc.cookbook.interactor.recipes.event.RateRecipeEvent;
import bme.msc.cookbook.interactor.recipes.event.RecipeAddedToFavouritesEvent;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.Presenter;

public class RecipeSummaryPresenter extends Presenter<RecipeDetailsScreen> {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    public void attachScreen(RecipeDetailsScreen screen) {
        super.attachScreen(screen);
        CookBookApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        EventBus.getDefault().unregister(this);
    }

    public void updateFavouriteRecipeVisitDate(final Long id) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.updateFavouriteRecipeVisitDate(id);
            }
        });
    }

    public void rateRecipe(final Long id, final int rating) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.rateRecipe(id, rating);
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
    public void onEventMainThread(final RateRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.updateRecipe(event.getRecipe());
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
