package bme.msc.cookbook.ui.recipedetails;

import bme.msc.cookbook.model.apiresult.Recipe;

public interface RecipeDetailsScreen {
    void showMessage(String message);

    void updateRecipe(Recipe recipe);
}
