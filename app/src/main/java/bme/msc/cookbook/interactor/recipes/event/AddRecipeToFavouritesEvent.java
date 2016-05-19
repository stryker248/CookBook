package bme.msc.cookbook.interactor.recipes.event;

import bme.msc.cookbook.model.apiresult.Recipe;

public class AddRecipeToFavouritesEvent {
    Recipe recipe;

    public AddRecipeToFavouritesEvent() {
    }

    public AddRecipeToFavouritesEvent(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
