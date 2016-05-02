package bme.msc.cookbook.network;

import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.model.apiresult.RecipesResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RecipesApi {
    @GET("recipes")
    Call<RecipesResult> getRecipes();

    @GET("recipes/creator/{userEmail}")
    Call<RecipesResult> getRecipesForUser(@Path("userEmail") String userEmail);

    @GET("recipes/search/{searchText}")
    Call<RecipesResult> searchRecipes(@Path("searchText") String searchText);

    @POST("recipe")
    Call<Recipe> addNewRecipe(@Body NewRecipe newRecipe);

    @POST("recipe/rate")
    Call<Recipe> rateRecipe(@Body NewRating newRating);
}
