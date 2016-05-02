package bme.msc.cookbook.ui.recipedetails;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
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

    public void rateRecipe(int id, int rating) {
        //TODO: recept értékelése
    }
}
