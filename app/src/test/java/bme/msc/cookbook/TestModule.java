package bme.msc.cookbook;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.ui.main.MainPresenter;
import bme.msc.cookbook.ui.newrecipe.NewRecipePresenter;
import bme.msc.cookbook.ui.recipedetails.RecipeDirectionsPresenter;
import bme.msc.cookbook.ui.recipedetails.RecipeIngredientsPresenter;
import bme.msc.cookbook.ui.recipedetails.RecipeSummaryPresenter;
import bme.msc.cookbook.ui.recipes.RecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.FavouriteRecipesPresenter;
import bme.msc.cookbook.ui.savedrecipes.OwnRecipesPresenter;
import bme.msc.cookbook.utils.UiExecutor;
import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() { return context; }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() { return new MainPresenter(); }

    @Provides
    @Singleton
    public RecipesPresenter provideRecipesPresenter() { return new RecipesPresenter(); }

    @Provides
    @Singleton
    public FavouriteRecipesPresenter provideFavouriteRecipesPresenter() { return new FavouriteRecipesPresenter(); }

    @Provides
    @Singleton
    public OwnRecipesPresenter provideOwnRecipesPresenter() { return new OwnRecipesPresenter(); }

    @Provides
    @Singleton
    public NewRecipePresenter provideNewRecipePresenter() { return new NewRecipePresenter(); }

    @Provides
    @Singleton
    public RecipeSummaryPresenter provideRecipeSummaryPresenter() { return new RecipeSummaryPresenter(); }

    @Provides
    @Singleton
    public RecipeIngredientsPresenter provideRecipeIngredientsPresenter() { return new RecipeIngredientsPresenter(); }

    @Provides
    @Singleton
    public RecipeDirectionsPresenter provideRecipeDirectionsPresenter() { return new RecipeDirectionsPresenter(); }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }
}
