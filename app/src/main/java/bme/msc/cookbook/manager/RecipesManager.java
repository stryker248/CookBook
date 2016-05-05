package bme.msc.cookbook.manager;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;
import bme.msc.cookbook.model.orm.OwnRecipe;

public interface RecipesManager {
    List<FavouriteRecipe> getFavouriteRecipes();

    List<OwnRecipe> getOwnRecipes();

    boolean addRecipeToFavourites(Recipe recipe);

    void removeRecipeFromFavourites(Long id);
}
