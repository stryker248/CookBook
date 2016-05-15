package bme.msc.cookbook.network;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Category;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesApi {
    @GET("categories")
    Call<List<Category>> getCategories();
}
