package bme.msc.cookbook.ui.savedrecipes;

import java.util.List;

import bme.msc.cookbook.model.Recipe;

public interface SavedRecipesScreen {
    void showRecipes(List<Recipe> recipes);

    void showNetworkError(String errorMessage);
}
