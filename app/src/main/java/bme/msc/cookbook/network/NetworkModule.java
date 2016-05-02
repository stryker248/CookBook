package bme.msc.cookbook.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
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
    public RecipesApi provideRecipesApi(Retrofit retrofit) {
        return retrofit.create(RecipesApi.class);
    }

    @Provides
    @Singleton
    public CategoriesApi provideCategoriesApi(Retrofit retrofit) {
        return retrofit.create(CategoriesApi.class);
    }
}
