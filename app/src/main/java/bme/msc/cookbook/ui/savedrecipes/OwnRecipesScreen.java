package bme.msc.cookbook.ui.savedrecipes;

import java.util.List;

import bme.msc.cookbook.model.orm.OwnRecipe;

public interface OwnRecipesScreen {
    void showRecipes(List<OwnRecipe> recipes);

    void showNetworkError(String errorMessage);
}
