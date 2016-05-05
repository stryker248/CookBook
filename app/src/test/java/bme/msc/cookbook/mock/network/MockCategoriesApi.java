package bme.msc.cookbook.mock.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bme.msc.cookbook.model.apiresult.CategoriesResult;
import bme.msc.cookbook.model.apiresult.Category;
import bme.msc.cookbook.network.CategoriesApi;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockCategoriesApi implements CategoriesApi {
    @Override
    public Call<CategoriesResult> getCategories() {
        final CategoriesResult categoriesResult = new CategoriesResult();
        List<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setId(1);
        category.setName("Category1");

        categories.add(category);
        categoriesResult.setCategories(categories);

        Call<CategoriesResult> call = new Call<CategoriesResult>() {
            @Override
            public Response<CategoriesResult> execute() throws IOException {
                return Response.success(categoriesResult);
            }

            @Override
            public void enqueue(Callback<CategoriesResult> callback) {
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<CategoriesResult> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return call;
    }
}
