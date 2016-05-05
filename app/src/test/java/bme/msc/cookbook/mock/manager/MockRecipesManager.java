package bme.msc.cookbook.mock.manager;

import java.util.List;

import bme.msc.cookbook.manager.RecipesManager;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;
import bme.msc.cookbook.model.orm.OwnRecipe;

public class MockRecipesManager implements RecipesManager {
    @Override
    public List<FavouriteRecipe> getFavouriteRecipes() {
        return null;
    }

    @Override
    public List<OwnRecipe> getOwnRecipes() {
        return null;
    }

    @Override
    public boolean addRecipeToFavourites(Recipe recipe) {
        return false;
    }

    @Override
    public void removeRecipeFromFavourites(Long id) {

    }
}
