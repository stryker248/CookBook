package bme.msc.cookbook.test.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Category;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static junit.framework.Assert.assertTrue;

public class CategoriesApiTest {
    @Test
    public void testGetCategories() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://cookbookbme.herokuapp.com/api/v1/categories")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            Gson gson = new Gson();
            List<Category> categories = gson.fromJson(responseBody, new TypeToken<List<Category>>(){}.getType());
            assertTrue(categories.size() > 0);
        } catch (Exception e) {
            System.out.println("Exception: testGetCategories() - " + e.getMessage());
            assertTrue(false);
        }
    }
}
