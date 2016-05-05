package bme.msc.cookbook.mock.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bme.msc.cookbook.manager.RecipesManager;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;
import bme.msc.cookbook.model.orm.OwnRecipe;

public class MockRecipesManager implements RecipesManager {
    private List<FavouriteRecipe> favouriteRecipes;
    private List<OwnRecipe> ownRecipes;

    public MockRecipesManager() {
        favouriteRecipes = new ArrayList<>();
        ownRecipes = new ArrayList<>();

        FavouriteRecipe favouriteRecipe = new FavouriteRecipe(
                "Name",
                "ImgUrl",
                "60 min",
                3,
                "Ingredients",
                "Directions",
                "Category",
                new Date()
        );

        OwnRecipe ownRecipe = new OwnRecipe(
                "Name",
                "ImgUrl",
                "60 min",
                3,
                "Ingredients",
                "Directions",
                "Category"
        );

        favouriteRecipes.add(favouriteRecipe);
        ownRecipes.add(ownRecipe);
    }

    @Override
    public List<FavouriteRecipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    @Override
    public List<OwnRecipe> getOwnRecipes() {
        return ownRecipes;
    }

    @Override
    public boolean addRecipeToFavourites(Recipe recipe) {
        for (FavouriteRecipe r : favouriteRecipes) {
            if (r.getId().equals(recipe.getId())) {
                return false;
            }
        }

        FavouriteRecipe newRecipe = new FavouriteRecipe(
                recipe.getName(),
                recipe.getImgUrl(),
                recipe.getTotalTime(),
                recipe.getRating(),
                recipe.getIngredients(),
                recipe.getDirections(),
                recipe.getCategory(),
                new Date()
        );
        favouriteRecipes.add(newRecipe);
        return true;
    }

    @Override
    public void removeRecipeFromFavourites(Long id) {
        FavouriteRecipe recipe = null;
        for (FavouriteRecipe r : favouriteRecipes) {
            if (r.getId().equals(id)) {
                recipe = r;
            }
        }

        if (recipe != null) {
            favouriteRecipes.remove(recipe);
        }
    }
}
