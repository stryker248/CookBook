package bme.msc.cookbook.interactor.recipes.event;

import bme.msc.cookbook.model.Recipe;

public class AddRecipeEvent {
    private int code;
    private Recipe recipe;
    private Throwable throwable;

    public AddRecipeEvent() {
    }

    public AddRecipeEvent(int code, Recipe recipe, Throwable throwable) {
        this.code = code;
        this.recipe = recipe;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
