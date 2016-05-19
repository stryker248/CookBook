package bme.msc.cookbook.ui.recipedetails;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.ui.Presenter;

public class RecipeDirectionsPresenter extends Presenter<RecipeDetailsScreen> {
    @Override
    public void attachScreen(RecipeDetailsScreen screen) {
        super.attachScreen(screen);
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}
