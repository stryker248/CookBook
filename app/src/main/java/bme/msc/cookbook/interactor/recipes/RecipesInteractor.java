package bme.msc.cookbook.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeEvent;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeToFavouritesEvent;
import bme.msc.cookbook.interactor.recipes.event.GetFavouriteRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.GetOwnRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.GetRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.RateRecipeEvent;
import bme.msc.cookbook.manager.RecipesManager;
import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.apiresult.RecipesResult;
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
        Call<RecipesResult> queryCall = recipesApi.getRecipes();

        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<RecipesResult> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipes(response.body().getRecipes());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void getRecipesForUser(String userEmail) {
        Call<RecipesResult> queryCall = recipesApi.getRecipesForUser(userEmail);

        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<RecipesResult> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipes(response.body().getRecipes());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void searchRecipes(String searchText) {
        Call<RecipesResult> queryCall = recipesApi.searchRecipes(searchText);

        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<RecipesResult> response = queryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipes(response.body().getRecipes());
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
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }

    public void getOwnRecipes() {
        List<OwnRecipe> recipes = recipesManager.getOwnRecipes();
        GetOwnRecipesEvent event = new GetOwnRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void getFavouriteRecipes() {
        List<FavouriteRecipe> recipes = recipesManager.getFavouriteRecipes();
        GetFavouriteRecipesEvent event = new GetFavouriteRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void addRecipeToFavourites(Recipe recipe) {
        boolean success = recipesManager.addRecipeToFavourites(recipe);
        AddRecipeToFavouritesEvent event = new AddRecipeToFavouritesEvent();
        if (success) {
            event.setMessage("Recipe added to your favourites!");
        } else {
            event.setMessage("Recipe is already on your favourites list!");
        }
        EventBus.getDefault().post(event);
    }

    public void removeRecipeFromFavourites(Long id) {
        recipesManager.removeRecipeFromFavourites(id);
        AddRecipeToFavouritesEvent event = new AddRecipeToFavouritesEvent();
        event.setMessage("Recipe removed from your favourites!");
        EventBus.getDefault().post(event);
    }
}
