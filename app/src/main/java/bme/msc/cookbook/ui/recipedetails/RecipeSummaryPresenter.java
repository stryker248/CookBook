package bme.msc.cookbook.ui.recipedetails;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.interactor.recipes.event.RateRecipeEvent;
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
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void rateRecipe(final Long id, final int rating) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.rateRecipe(id, rating);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final RateRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.updateRecipe(event.getRecipe());
            }
        }
    }
}
