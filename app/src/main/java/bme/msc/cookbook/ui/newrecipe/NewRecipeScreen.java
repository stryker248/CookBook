package bme.msc.cookbook.ui.newrecipe;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Category;
import bme.msc.cookbook.model.apiresult.Recipe;

public interface NewRecipeScreen {
    void showCategories(List<Category> categories);

    void showNewRecipe(Recipe recipe);

    void showError(String errorMessage);
}
