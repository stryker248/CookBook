package bme.msc.cookbook.network;

import bme.msc.cookbook.model.apiresult.RecipesResult;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesApi {
    @GET("recipes")
    Call<RecipesResult> getRecipes();
}
