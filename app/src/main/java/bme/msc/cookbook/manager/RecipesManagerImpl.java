package bme.msc.cookbook.manager;

import com.orm.query.Select;
import com.orm.util.NamingHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;
import bme.msc.cookbook.model.orm.OwnRecipe;

public class RecipesManagerImpl implements RecipesManager {
    public List<FavouriteRecipe> getFavouriteRecipes() {
        List<FavouriteRecipe> recipes =
                FavouriteRecipe.find(FavouriteRecipe.class,
                        null, null, null,
                        NamingHelper.toSQLNameDefault("lastDate") + " Desc",
                        null );

        /*List<FavouriteRecipe> recipes =
                (ArrayList<FavouriteRecipe>) Select.from(FavouriteRecipe.class)
                        .orderBy(NamingHelper.toSQLNameDefault("lastDate") + " Desc")
                        .list();*/
        return recipes;
    }

    public List<OwnRecipe> getOwnRecipes() {
        List<OwnRecipe> recipes = OwnRecipe.listAll(OwnRecipe.class);
        return recipes;
    }

    public boolean addRecipeToFavourites(Recipe recipe) {
        List<FavouriteRecipe> favouriteRecipe = FavouriteRecipe.find(
                FavouriteRecipe.class,
                NamingHelper.toSQLNameDefault("recipeId") + " = ?", recipe.getId().toString()
        );

        if (favouriteRecipe.size() == 0) {
            FavouriteRecipe newRecipe = new FavouriteRecipe(
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getImgUrl(),
                    recipe.getTotalTime(),
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

    public void addOwnRecipe(Recipe recipe) {
        List<OwnRecipe> ownRecipes = OwnRecipe.find(
                OwnRecipe.class,
                NamingHelper.toSQLNameDefault("recipeId") + " = ?", recipe.getId().toString()
        );

        if (ownRecipes.size() == 0) {
            OwnRecipe newRecipe = new OwnRecipe(
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getImgUrl(),
                    recipe.getTotalTime(),
                    recipe.getRating(),
                    recipe.getIngredients(),
                    recipe.getDirections(),
                    recipe.getCategory()
            );
            newRecipe.save();
        }
    }

    public void updateFavouriteRecipeVisitDate(Long id) {
        List<FavouriteRecipe> favouriteRecipes = FavouriteRecipe.find(
                FavouriteRecipe.class,
                NamingHelper.toSQLNameDefault("recipeId") + " = ?", id.toString()
        );

        if (favouriteRecipes.size() > 0) {
            FavouriteRecipe recipe = favouriteRecipes.get(0);
            recipe.setLastDate(new Date());
            recipe.save();
        }
    }

    public void updateFavouriteRecipes(List<Recipe> recipes) {
        List<FavouriteRecipe> favouriteRecipes = FavouriteRecipe.listAll(FavouriteRecipe.class);

        if (favouriteRecipes.size() > 0) {
            for(Recipe r : recipes) {
                for(FavouriteRecipe fr : favouriteRecipes) {
                    if (fr.getRecipeId().equals(r.getId())) {
                        if (fr.getRating() != r.getRating()) {
                            fr.setRating(r.getRating());
                            fr.save();
                        }
                    }
                }
            }
        }
    }

    public void updateOwnRecipes(List<Recipe> recipes) {
        List<OwnRecipe> ownRecipes = OwnRecipe.listAll(OwnRecipe.class);

        if (ownRecipes.size() > recipes.size()) {
            OwnRecipe.deleteAll(OwnRecipe.class);
        } else {
            boolean recipeFound = false;
            for(Recipe r : recipes) {
                for(OwnRecipe or : ownRecipes) {
                    if (or.getRecipeId().equals(r.getId())) {
                        if (or.getRating() != r.getRating()) {
                            or.setRating(r.getRating());
                            or.save();
                        }

                        recipeFound = true;
                    }
                }

                if (!recipeFound) {
                    OwnRecipe newRecipe = new OwnRecipe(
                            r.getId(),
                            r.getName(),
                            r.getImgUrl(),
                            r.getTotalTime(),
                            r.getRating(),
                            r.getIngredients(),
                            r.getDirections(),
                            r.getCategory()
                    );
                    newRecipe.save();
                }

                recipeFound = false;
            }
        }
    }
}
