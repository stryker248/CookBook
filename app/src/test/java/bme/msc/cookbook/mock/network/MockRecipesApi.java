package bme.msc.cookbook.mock.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.network.RecipesApi;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class MockRecipesApi implements RecipesApi {
    @Override
    public Call<List<Recipe>> getRecipes() {
        final List<Recipe> recipes = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Recipe");
        recipe.setImgUrl("ImgUrl");
        recipe.setTotalTime("60 min");
        recipe.setRating(5.0f);
        recipe.setIngredients("Ingredients");
        recipe.setDirections("Directions");
        recipe.setCategory("Category");

        recipes.add(recipe);

        Call<List<Recipe>> call = new Call<List<Recipe>>() {
            @Override
            public Response<List<Recipe>> execute() throws IOException {
                return Response.success(recipes);
            }

            @Override
            public void enqueue(Callback<List<Recipe>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<Recipe>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<List<Recipe>> getRecipesForUser(@Path("userEmail") String userEmail) {
        final List<Recipe> recipes = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Recipe");
        recipe.setImgUrl("ImgUrl");
        recipe.setTotalTime("60 min");
        recipe.setRating(5.0f);
        recipe.setIngredients("Ingredients");
        recipe.setDirections("Directions");
        recipe.setCategory("Category");

        recipes.add(recipe);

        Call<List<Recipe>> call = new Call<List<Recipe>>() {
            @Override
            public Response<List<Recipe>> execute() throws IOException {
                return Response.success(recipes);
            }

            @Override
            public void enqueue(Callback<List<Recipe>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<Recipe>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<List<Recipe>> searchRecipes(@Path("searchText") String searchText) {
        final List<Recipe> recipes = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Recipe");
        recipe.setImgUrl("ImgUrl");
        recipe.setTotalTime("60 min");
        recipe.setRating(5.0f);
        recipe.setIngredients("Ingredients");
        recipe.setDirections("Directions");
        recipe.setCategory("Category");

        recipes.add(recipe);

        Call<List<Recipe>> call = new Call<List<Recipe>>() {
            @Override
            public Response<List<Recipe>> execute() throws IOException {
                return Response.success(recipes);
            }

            @Override
            public void enqueue(Callback<List<Recipe>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<Recipe>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<Recipe> addNewRecipe(@Body NewRecipe newRecipe) {
        final Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Recipe");
        recipe.setImgUrl("ImgUrl");
        recipe.setTotalTime("60 min");
        recipe.setRating(5.0f);
        recipe.setIngredients("Ingredients");
        recipe.setDirections("Directions");
        recipe.setCategory("Category");

        Call<Recipe> call = new Call<Recipe>() {
            @Override
            public Response<Recipe> execute() throws IOException {
                return Response.success(recipe);
            }

            @Override
            public void enqueue(Callback<Recipe> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Recipe> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }

    @Override
    public Call<Recipe> rateRecipe(@Body NewRating newRating) {
        final Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Recipe");
        recipe.setImgUrl("ImgUrl");
        recipe.setTotalTime("60 min");
        recipe.setRating(5.0f);
        recipe.setIngredients("Ingredients");
        recipe.setDirections("Directions");
        recipe.setCategory("Category");

        Call<Recipe> call = new Call<Recipe>() {
            @Override
            public Response<Recipe> execute() throws IOException {
                return Response.success(recipe);
            }

            @Override
            public void enqueue(Callback<Recipe> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Recipe> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }
}
