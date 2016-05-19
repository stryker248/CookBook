package bme.msc.cookbook.interactor.recipes;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeEvent;
import bme.msc.cookbook.interactor.recipes.event.GetFavouriteRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.GetOwnRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.GetRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.RateRecipeEvent;
import bme.msc.cookbook.interactor.recipes.event.RecipeAddedToFavouritesEvent;
import bme.msc.cookbook.interactor.recipes.event.RecipeRemovedFromFavouritesEvent;
import bme.msc.cookbook.manager.RecipesManager;
import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.orm.FavouriteRecipe;
import bme.msc.cookbook.model.orm.OwnRecipe;
import bme.msc.cookbook.network.RecipesApi;
import retrofit2.Call;
import retrofit2.Response;

public class RecipesInteractor {
    @Inject
    RecipesApi recipesApi;

    @Inject
    RecipesManager recipesManager;

    public RecipesInteractor() {
        CookBookApplication.injector.inject(this);
    }

    public void getRecipes() {
        Call<List<Recipe>> queryCall = recipesApi.getRecipes();

        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<List<Recipe>> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());

            List<Recipe> recipes = response.body();
            recipesManager.updateFavouriteRecipes(recipes);
            event.setRecipes(recipes);
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void searchRecipes(String searchText) {
        Call<List<Recipe>> queryCall = recipesApi.searchRecipes(searchText);

        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<List<Recipe>> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipes(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void rateRecipe(Long id, int rating) {
        NewRating newRating = new NewRating(id, rating);
        Call<Recipe> queryCall = recipesApi.rateRecipe(newRating);

        RateRecipeEvent event = new RateRecipeEvent();
        try {
            Response<Recipe> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipe(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void addNewRecipe(NewRecipe newRecipe) {
        Call<Recipe> queryCall = recipesApi.addNewRecipe(newRecipe);

        AddRecipeEvent event = new AddRecipeEvent();
        try {
            Response<Recipe> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipe(response.body());

            recipesManager.addOwnRecipe(event.getRecipe());

            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void updateFavouriteRecipes() {
        Call<List<Recipe>> queryCall = recipesApi.getRecipes();

        try {
            Response<List<Recipe>> response = queryCall.execute();
            if (response.code() == 200) {
                List<Recipe> recipes = response.body();
                recipesManager.updateFavouriteRecipes(recipes);
            }
        } catch (Exception e) {
            Log.i("CookBookLog", "RecipesInteractor.updateFavouriteRecipes(): " + e.getMessage());
        }
    }

    public void updateFavouriteRecipeVisitDate(Long id) {
        recipesManager.updateFavouriteRecipeVisitDate(id);
    }

    public void getFavouriteRecipes() {
        updateFavouriteRecipes();
        List<FavouriteRecipe> recipes = recipesManager.getFavouriteRecipes();
        GetFavouriteRecipesEvent event = new GetFavouriteRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void addRecipeToFavourites(Recipe recipe) {
        boolean success = recipesManager.addRecipeToFavourites(recipe);
        RecipeAddedToFavouritesEvent event = new RecipeAddedToFavouritesEvent();
        if (success) {
            event.setMessage("Recipe added to your favourites!");
        } else {
            event.setMessage("Recipe is already on your favourites list!");
        }
        EventBus.getDefault().post(event);
    }

    public void removeRecipeFromFavourites(Long id) {
        recipesManager.removeRecipeFromFavourites(id);
        RecipeRemovedFromFavouritesEvent event = new RecipeRemovedFromFavouritesEvent();
        event.setMessage("Recipe removed from your favourites!");
        event.setId(id);
        EventBus.getDefault().post(event);
    }

    public void getOwnRecipes() {
        List<OwnRecipe> recipes = recipesManager.getOwnRecipes();
        GetOwnRecipesEvent event = new GetOwnRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void updateOwnRecipes(String userEmail) {
        Call<List<Recipe>> queryCall = recipesApi.getRecipesForUser(userEmail);

        try {
            Response<List<Recipe>> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }

            recipesManager.updateOwnRecipes(response.body());
        } catch (Exception e) {
            Log.i("CookBookLog", "RecipesInteractor.updateOwnRecipes(" + userEmail + ") exception: " + e.getMessage());
        }
    }
}
