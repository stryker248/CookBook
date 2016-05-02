package bme.msc.cookbook.interactor.categories;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.interactor.categories.event.GetCategoriesEvent;
import bme.msc.cookbook.model.apiresult.CategoriesResult;
import bme.msc.cookbook.model.apiresult.Category;
import bme.msc.cookbook.network.CategoriesApi;
import retrofit2.Call;
import retrofit2.Response;

public class CategoriesInteractor {
    @Inject
    CategoriesApi categoriesApi;

    public CategoriesInteractor() { CookBookApplication.injector.inject(this); }

    public void getCategories() {
        Call<CategoriesResult> categoriesQueryCall = categoriesApi.getCategories();
        GetCategoriesEvent event = new GetCategoriesEvent();
        try {
            Response<CategoriesResult> response = categoriesQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Something went wrong!");
            }
            event.setCode(response.code());
            event.setCategories(response.body().getCategories());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
