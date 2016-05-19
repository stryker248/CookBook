package bme.msc.cookbook.manager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {
    @Provides
    @Singleton
    public RecipesManager provideRecipesManager() { return new RecipesManagerImpl(); }
}
