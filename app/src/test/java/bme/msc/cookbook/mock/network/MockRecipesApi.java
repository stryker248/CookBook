package bme.msc.cookbook.mock.network;

import java.util.List;

import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.network.RecipesApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class MockRecipesApi implements RecipesApi {
    @Override
    public Call<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public Call<List<Recipe>> getRecipesForUser(@Path("userEmail") String userEmail) {
        return null;
    }

    @Override
    public Call<List<Recipe>> searchRecipes(@Path("searchText") String searchText) {
        return null;
    }

    @Override
    public Call<Recipe> addNewRecipe(@Body NewRecipe newRecipe) {
        return null;
    }

    @Override
    public Call<Recipe> rateRecipe(@Body NewRating newRating) {
        return null;
    }
}
