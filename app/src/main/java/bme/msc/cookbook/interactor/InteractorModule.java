package bme.msc.cookbook.interactor;

import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    public RecipesInteractor provideRecipesInteractor() { return new RecipesInteractor(); }
}
