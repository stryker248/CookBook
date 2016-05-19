package bme.msc.cookbook.test.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

import bme.msc.cookbook.model.apimodel.NewRating;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static junit.framework.Assert.assertTrue;

public class RecipesApiTest {
    @Test
    public void testGetRecipes() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/recipes")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Gson gson = new Gson();
            List<Recipe> recipes = gson.fromJson(responseBody, new TypeToken<List<Recipe>>(){}.getType());
            assertTrue(recipes.size() > 0);
        } catch (Exception e) {
            System.out.println("Exception: testGetRecipes() - " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testGetRecipesByUserEmail() {
        String userEmail = "admin@cookbook.asd";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/recipes/creator/" + userEmail)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Gson gson = new Gson();
            List<Recipe> recipes = gson.fromJson(responseBody, new TypeToken<List<Recipe>>(){}.getType());
            assertTrue(recipes.size() > 0);
        } catch (Exception e) {
            System.out.println("Exception: testGetRecipesByUserEmail() - " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testSearchRecipes() {
        String searchText = "bruschetta";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/recipes/search/" + searchText)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Gson gson = new Gson();
            List<Recipe> recipes = gson.fromJson(responseBody, new TypeToken<List<Recipe>>(){}.getType());
            assertTrue(recipes.size() > 0);
        } catch (Exception e) {
            System.out.println("Exception: testSearchRecipes() - " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testAddNewRecipe() {
        Gson gson = new Gson();
        NewRecipe newRecipe = new NewRecipe("TestNewRecipeName", "ImgUrl", "60 min", "Ingredients",
                "Directions", "user1@cookbook.hu", 1);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), gson.toJson(newRecipe));
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/recipe")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Recipe recipe = gson.fromJson(responseBody, Recipe.class);
            assertTrue(recipe.getName().equals("TestNewRecipeName"));
        } catch (Exception e) {
            System.out.println("Exception: testAddNewRecipe() - " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testRateRecipe() {
        Gson gson = new Gson();
        NewRating newRating = new NewRating(1L, 5);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), gson.toJson(newRating));
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/recipe/rate")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Recipe recipe = gson.fromJson(responseBody, Recipe.class);
            assertTrue(response.code() == 200);
        } catch (Exception e) {
            System.out.println("Exception: testRateRecipe() - " + e.getMessage());
            assertTrue(false);
        }
    }

    @AfterClass
    public static void cleanUp() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/recipes/reset")
                .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            System.out.println("Exception in cleanUp()");
        }
    }
}
