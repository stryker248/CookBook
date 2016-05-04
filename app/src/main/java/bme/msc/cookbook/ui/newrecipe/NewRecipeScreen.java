package bme.msc.cookbook.ui.newrecipe;

import bme.msc.cookbook.model.apiresult.Recipe;

public interface NewRecipeScreen {
    void showNewRecipe(Recipe recipe);

    void showError(String errorMessage);
}
