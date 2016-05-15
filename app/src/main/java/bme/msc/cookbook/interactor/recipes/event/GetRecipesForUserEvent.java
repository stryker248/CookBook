package bme.msc.cookbook.interactor.recipes.event;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Recipe;

public class GetRecipesForUserEvent {
    private int code;
    private List<Recipe> recipes;
    private Throwable throwable;

    public GetRecipesForUserEvent() {
    }

    public GetRecipesForUserEvent(int code, List<Recipe> recipes, Throwable throwable) {
        this.code = code;
        this.recipes = recipes;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
