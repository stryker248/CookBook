package bme.msc.cookbook;

import javax.inject.Singleton;

import bme.msc.cookbook.interactor.InteractorModule;
import bme.msc.cookbook.interactor.categories.CategoriesInteractor;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.manager.ManagerModule;
import bme.msc.cookbook.manager.RecipesManager;
import bme.msc.cookbook.network.NetworkModule;
import bme.msc.cookbook.ui.UIModule;
import bme.msc.cookbook.ui.main.MainActivity;
import bme.msc.cookbook.ui.main.MainFragment;
import bme.msc.cookbook.ui.main.MainPresenter;
import bme.msc.cookbook.ui.newrecipe.NewRecipeActivity;
import bme.msc.cookbook.ui.newrecipe.NewRecipeFragment;
import bme.msc.cookbook.ui.newrecipe.NewRecipePresenter;
import bme.msc.cookbook.ui.recipedetails.RecipeDetailsActivity;
import bme.msc.cookbook.ui.recipedetails.RecipeDirectionsFragment;
import bme.msc.cookbook.ui.recipedetails.RecipeDirectionsPresenter;
import bme.msc.cookbook.ui.recipedetails.RecipeIngredientsFragment;
import bme.msc.cookbook.ui.recipedetails.RecipeIngredientsPresenter;
import bme.msc.cookbook.ui.recipedetails.RecipeSummaryFragment;
import bme.msc.cookbook.ui.recipedetails.RecipeSummaryPresenter;
import bme.msc.cookbook.ui.recipes.RecipesActivity;
import bme.msc.cookbook.ui.recipes.RecipesFragment;
import bme.msc.cookbook.ui.recipes.RecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.FavouriteRecipesFragment;
import bme.msc.cookbook.ui.savedrecipes.FavouriteRecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.OwnRecipesFragment;
import bme.msc.cookbook.ui.savedrecipes.OwnRecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.SavedRecipesActivity;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class, ManagerModule.class})
public interface CookBookApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
    void inject(MainPresenter mainPresenter);

    void inject(RecipesActivity recipesActivity);
    void inject(RecipesFragment recipesFragment);
    void inject(RecipesPresenter recipesPresenter);

    void inject(SavedRecipesActivity savedRecipesActivity);
    void inject(FavouriteRecipesFragment favouriteRecipesFragment);
    void inject(FavouriteRecipesPresenter favouriteRecipesPresenter);
    void inject(OwnRecipesFragment ownRecipesFragment);
    void inject(OwnRecipesPresenter ownRecipesPresenter);

    void inject(NewRecipeActivity newRecipeActivity);
    void inject(NewRecipeFragment newRecipeFragment);
    void inject(NewRecipePresenter newRecipePresenter);

    void inject(RecipeDetailsActivity recipeDetailsActivity);
    void inject(RecipeSummaryFragment recipeSummaryFragment);
    void inject(RecipeSummaryPresenter recipeSummaryPresenter);
    void inject(RecipeIngredientsFragment recipeIngredientsFragment);
    void inject(RecipeIngredientsPresenter recipeIngredientsPresenter);
    void inject(RecipeDirectionsFragment recipeDirectionsFragment);
    void inject(RecipeDirectionsPresenter recipeDirectionsPresenter);

    void inject(RecipesInteractor recipesInteractor);
    void inject(CategoriesInteractor categoriesInteractor);

    void inject(RecipesManager recipesManager);
}
