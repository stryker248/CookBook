package bme.msc.cookbook.ui.recipes;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.R;
import bme.msc.cookbook.di.Network;
import bme.msc.cookbook.interactor.recipes.RecipesInteractor;

public class RecipesActivity extends AppCompatActivity {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    RecipesInteractor recipesInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        CookBookApplication.injector.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.recipes_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipes_search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.recipes_menu_action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                if (query.length() > 0) {
                    networkExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            recipesInteractor.searchRecipes(query);
                        }
                    });
                } else {
                    networkExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            recipesInteractor.getRecipes();
                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                networkExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        recipesInteractor.getRecipes();
                    }
                });
                return true;
            }
        });

        return true;
    }
}
