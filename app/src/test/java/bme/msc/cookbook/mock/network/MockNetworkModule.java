package bme.msc.cookbook.mock.network;

import javax.inject.Singleton;

import bme.msc.cookbook.network.CategoriesApi;
import bme.msc.cookbook.network.NetworkConfig;
import bme.msc.cookbook.network.RecipesApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MockNetworkModule {
    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(NetworkConfig.ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public RecipesApi provideRecipesApi(Retrofit retrofit) { return new MockRecipesApi(); }

    @Provides
    @Singleton
    public CategoriesApi provideCategoriesApi(Retrofit retrofit) { return new MockCategoriesApi(); }
}
