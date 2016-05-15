package bme.msc.cookbook.ui.savedrecipes;

import java.util.List;

import bme.msc.cookbook.model.orm.FavouriteRecipe;

public interface FavouriteRecipesScreen {
    void showRecipes(List<FavouriteRecipe> recipes);

    void removeRecipe(Long id);

    void showMessage(String message);
}
