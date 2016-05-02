package bme.msc.cookbook.manager;

import java.util.Date;
import java.util.List;

import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;
import bme.msc.cookbook.model.orm.OwnRecipe;

public class RecipesManager {
    public List<FavouriteRecipe> getFavouriteRecipes() {
        List<FavouriteRecipe> recipes = FavouriteRecipe.listAll(FavouriteRecipe.class);
        return recipes;
    }

    public List<OwnRecipe> getOwnRecipes() {
        List<OwnRecipe> recipes = OwnRecipe.listAll(OwnRecipe.class);
        return recipes;
    }

    public boolean addRecipeToFavourites(Recipe recipe) {
        if (FavouriteRecipe.findById(FavouriteRecipe.class, recipe.getId()) == null) {
            FavouriteRecipe newRecipe = new FavouriteRecipe(
                    recipe.getName(),
                    recipe.getImgUrl(),
                    recipe.getCookingTime(),
                    recipe.getRating(),
                    recipe.getIngredients(),
                    recipe.getDirections(),
                    recipe.getCategory(),
                    new Date()
            );
            newRecipe.save();
            return true;
        }

        return false;
    }

    public void removeRecipeFromFavourites(Long id) {
        FavouriteRecipe recipe = FavouriteRecipe.findById(FavouriteRecipe.class, id);
        if (recipe != null) {
            recipe.delete();
        }
    }
}
