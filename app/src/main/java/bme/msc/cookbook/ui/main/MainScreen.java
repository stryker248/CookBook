package bme.msc.cookbook.ui.main;

import java.util.List;

import bme.msc.cookbook.model.Recipe;

public interface MainScreen {
    void showRecipes(List<Recipe> recipes);

    void showNetworkError(String errorMessage);
}
