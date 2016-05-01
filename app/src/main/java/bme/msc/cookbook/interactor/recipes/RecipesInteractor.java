package bme.msc.cookbook.interactor.recipes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeEvent;
import bme.msc.cookbook.interactor.recipes.event.GetRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.LoadFavouriteRecipesEvent;
import bme.msc.cookbook.interactor.recipes.event.LoadOwnRecipesEvent;
import bme.msc.cookbook.model.NewRecipe;
import bme.msc.cookbook.model.Recipe;
import bme.msc.cookbook.model.apiresult.RecipesResult;
import bme.msc.cookbook.network.RecipesApi;
import retrofit2.Call;
import retrofit2.Response;

public class RecipesInteractor {
    @Inject
    RecipesApi recipesApi;

    public RecipesInteractor() {
        CookBookApplication.injector.inject(this);
    }

    //TODO: hackelést kiszedni
    public void getRecipes() {
        /*Call<RecipesResult> recipesQueryCall = recipesApi.getRecipes();
        GetRecipesEvent event = new GetRecipesEvent();
        try {
            Response<RecipesResult> response = recipesQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setRecipes(response.body().getRecipes());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }*/

        Recipe recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe1.setName("name1");
        recipe1.setCategory("category1");
        recipe1.setRating(1.0);
        recipe1.setCookingTime("cookingTime1");
        recipe1.setIngredients("ingredients1");
        recipe1.setDirections("directions1");

        Recipe recipe2 = new Recipe();
        recipe2.setId(1);
        recipe2.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe2.setName("name2");
        recipe2.setCategory("category2");
        recipe2.setRating(2.0);
        recipe2.setCookingTime("cookingTime2");
        recipe2.setIngredients("ingredients2");
        recipe2.setDirections("directions2");

        Recipe recipe3 = new Recipe();
        recipe3.setId(1);
        recipe3.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe3.setName("name2");
        recipe3.setCategory("category2");
        recipe3.setRating(2.0);
        recipe3.setCookingTime("cookingTime2");
        recipe3.setIngredients("ingredients2");
        recipe3.setDirections("directions2");

        Recipe recipe4 = new Recipe();
        recipe4.setId(1);
        recipe4.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe4.setName("name2");
        recipe4.setCategory("category2");
        recipe4.setRating(2.0);
        recipe4.setCookingTime("cookingTime2");
        recipe4.setIngredients("ingredients2");
        recipe4.setDirections("directions2");

        Recipe recipe5 = new Recipe();
        recipe5.setId(1);
        recipe5.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe5.setName("name2");
        recipe5.setCategory("category2");
        recipe5.setRating(2.0);
        recipe5.setCookingTime("cookingTime2");
        recipe5.setIngredients("ingredients2");
        recipe5.setDirections("directions2");

        Recipe recipe6 = new Recipe();
        recipe6.setId(1);
        recipe6.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe6.setName("name2");
        recipe6.setCategory("category2");
        recipe6.setRating(2.0);
        recipe6.setCookingTime("cookingTime2");
        recipe6.setIngredients("ingredients2");
        recipe6.setDirections("directions2");

        Recipe recipe7 = new Recipe();
        recipe7.setId(1);
        recipe7.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe7.setName("name2");
        recipe7.setCategory("category2");
        recipe7.setRating(2.0);
        recipe7.setCookingTime("cookingTime2");
        recipe7.setIngredients("ingredients2");
        recipe7.setDirections("directions2");

        Recipe recipe8 = new Recipe();
        recipe8.setId(1);
        recipe8.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe8.setName("name2");
        recipe8.setCategory("category2");
        recipe8.setRating(2.0);
        recipe8.setCookingTime("cookingTime2");
        recipe8.setIngredients("ingredients2");
        recipe8.setDirections("directions2");

        Recipe recipe9 = new Recipe();
        recipe9.setId(1);
        recipe9.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe9.setName("name2");
        recipe9.setCategory("category2");
        recipe9.setRating(2.0);
        recipe9.setCookingTime("cookingTime2");
        recipe9.setIngredients("ingredients2");
        recipe9.setDirections("directions2");

        Recipe recipe10 = new Recipe();
        recipe10.setId(1);
        recipe10.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe10.setName("name2");
        recipe10.setCategory("category2");
        recipe10.setRating(2.0);
        recipe10.setCookingTime("cookingTime2");
        recipe10.setIngredients("ingredients2");
        recipe10.setDirections("directions2");

        Recipe recipe11 = new Recipe();
        recipe11.setId(1);
        recipe11.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe11.setName("name2");
        recipe11.setCategory("category2");
        recipe11.setRating(2.0);
        recipe11.setCookingTime("cookingTime2");
        recipe11.setIngredients("ingredients2");
        recipe11.setDirections("directions2");

        Recipe recipe12 = new Recipe();
        recipe12.setId(1);
        recipe12.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe12.setName("name2");
        recipe12.setCategory("category2");
        recipe12.setRating(2.0);
        recipe12.setCookingTime("cookingTime2");
        recipe12.setIngredients("ingredients2");
        recipe12.setDirections("directions2");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        recipes.add(recipe3);
        recipes.add(recipe4);
        recipes.add(recipe5);
        recipes.add(recipe6);
        recipes.add(recipe7);
        recipes.add(recipe8);
        recipes.add(recipe9);
        recipes.add(recipe10);
        recipes.add(recipe11);
        recipes.add(recipe12);

        GetRecipesEvent event = new GetRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void loadFavouriteRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe1.setName("name1Favourite");
        recipe1.setCategory("category1");
        recipe1.setRating(1.0);
        recipe1.setCookingTime("cookingTime1");
        recipe1.setIngredients("ingredients1");
        recipe1.setDirections("directions1");

        Recipe recipe2 = new Recipe();
        recipe2.setId(1);
        recipe2.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe2.setName("name2Favourite");
        recipe2.setCategory("category2");
        recipe2.setRating(2.0);
        recipe2.setCookingTime("cookingTime2");
        recipe2.setIngredients("ingredients2");
        recipe2.setDirections("directions2");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        LoadFavouriteRecipesEvent event = new LoadFavouriteRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void loadOwnRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe1.setName("name1Own");
        recipe1.setCategory("category1");
        recipe1.setRating(1.0);
        recipe1.setCookingTime("cookingTime1");
        recipe1.setIngredients("ingredients1");
        recipe1.setDirections("directions1");

        Recipe recipe2 = new Recipe();
        recipe2.setId(1);
        recipe2.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe2.setName("name2Own");
        recipe2.setCategory("category2");
        recipe2.setRating(2.0);
        recipe2.setCookingTime("cookingTime2");
        recipe2.setIngredients("ingredients2");
        recipe2.setDirections("directions2");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        LoadOwnRecipesEvent event = new LoadOwnRecipesEvent();
        event.setCode(200);
        event.setRecipes(recipes);
        EventBus.getDefault().post(event);
    }

    public void addNewRecipe(NewRecipe newRecipe) {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setImgUrl("http://www.recipe.com/images/lemon-chicken-50366-ss.jpg");
        recipe.setName(newRecipe.getName());
        recipe.setCategory("category2");
        recipe.setRating(0.0);
        recipe.setCookingTime(newRecipe.getCookingTime());
        recipe.setIngredients(newRecipe.getIngredients());
        recipe.setDirections(newRecipe.getDirections());

        AddRecipeEvent event = new AddRecipeEvent();
        event.setCode(200);
        event.setRecipe(recipe);
        EventBus.getDefault().post(event);
    }
}
