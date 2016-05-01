package bme.msc.cookbook.ui.recipes;

import java.util.List;

import bme.msc.cookbook.model.Recipe;

public interface RecipesScreen {
    void showRecipes(List<Recipe> recipes);

    void showNetworkError(String errorMessage);
}
