package bme.msc.cookbook.interactor;

import bme.msc.cookbook.interactor.categories.CategoriesInteractor;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    public RecipesInteractor provideRecipesInteractor() { return new RecipesInteractor(); }

    @Provides
    public CategoriesInteractor provideCategoriesInteractor() { return new CategoriesInteractor(); }
}
