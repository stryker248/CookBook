package bme.msc.cookbook.ui.main;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;

public interface MainScreen {
    void showRecipes(List<FavouriteRecipe> recipes);

    void showNetworkError(String errorMessage);
}
