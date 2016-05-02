package bme.msc.cookbook.interactor.recipes.event;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.OwnRecipe;

public class GetOwnRecipesEvent {
    private int code;
    private List<OwnRecipe> recipes;
    private Throwable throwable;

    public GetOwnRecipesEvent() {
    }

    public GetOwnRecipesEvent(int code, List<OwnRecipe> recipes, Throwable throwable) {
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

    public List<OwnRecipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<OwnRecipe> recipes) {
        this.recipes = recipes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
