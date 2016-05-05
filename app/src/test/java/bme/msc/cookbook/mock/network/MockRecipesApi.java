package bme.msc.cookbook.mock.network;

import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.apiresult.RecipesResult;
import bme.msc.cookbook.network.RecipesApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class MockRecipesApi implements RecipesApi {
    @Override
    public Call<RecipesResult> getRecipes() {
        return null;
    }

    @Override
    public Call<RecipesResult> getRecipesForUser(@Path("userEmail") String userEmail) {
        return null;
    }

    @Override
    public Call<RecipesResult> searchRecipes(@Path("searchText") String searchText) {
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
