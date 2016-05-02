package bme.msc.cookbook.ui.savedrecipes;

import java.util.List;

import bme.msc.cookbook.model.orm.FavouriteRecipe;

public interface FavouriteRecipesScreen {
    void showRecipes(List<FavouriteRecipe> recipes);

    void showNetworkError(String errorMessage);
}
