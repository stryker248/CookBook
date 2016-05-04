package bme.msc.cookbook.ui.recipedetails;

import bme.msc.cookbook.model.apiresult.Recipe;

public interface RecipeDetailsScreen {
    void showError(String errorMessage);

    void updateRecipe(Recipe recipe);
}
