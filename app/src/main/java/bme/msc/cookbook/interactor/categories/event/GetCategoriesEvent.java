package bme.msc.cookbook.interactor.categories.event;

import java.util.List;

import bme.msc.cookbook.model.apiresult.Category;

public class GetCategoriesEvent {
    private int code;
    private List<Category> categories;
    private Throwable throwable;

    public GetCategoriesEvent() {
    }

    public GetCategoriesEvent(int code, List<Category> categories, Throwable throwable) {
        this.code = code;
        this.categories = categories;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
