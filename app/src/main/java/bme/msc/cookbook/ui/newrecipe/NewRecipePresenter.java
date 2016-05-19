package bme.msc.cookbook.ui.newrecipe;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.categories.CategoriesInteractor;
import bme.msc.cookbook.interactor.categories.event.GetCategoriesEvent;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeEvent;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.ui.Presenter;

public class NewRecipePresenter extends Presenter<NewRecipeScreen> {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    RecipesInteractor recipesInteractor;

    @Inject
    CategoriesInteractor categoriesInteractor;

    @Override
    public void attachScreen(NewRecipeScreen screen) {
        super.attachScreen(screen);
        CookBookApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void refreshCategories() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoriesInteractor.getCategories();
            }
        });
    }

    public void addNewRecipe(final NewRecipe newRecipe) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipesInteractor.addNewRecipe(newRecipe);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final AddRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showNewRecipe(event.getRecipe());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetCategoriesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showCategories(event.getCategories());
            }
        }
    }
}
