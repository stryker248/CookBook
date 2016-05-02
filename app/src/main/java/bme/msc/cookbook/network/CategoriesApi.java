package bme.msc.cookbook.network;

import bme.msc.cookbook.model.apiresult.CategoriesResult;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesApi {
    @GET("categories")
    Call<CategoriesResult> getCategories();
}
