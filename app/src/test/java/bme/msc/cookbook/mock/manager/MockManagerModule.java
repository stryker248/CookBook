package bme.msc.cookbook.mock.manager;

import javax.inject.Singleton;

import bme.msc.cookbook.manager.RecipesManager;
import dagger.Module;
import dagger.Provides;

@Module
public class MockManagerModule {
    @Provides
    @Singleton
    public RecipesManager provideRecipesManager() { return new MockRecipesManager(); }
}
