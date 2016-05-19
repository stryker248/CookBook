package bme.msc.cookbook.ui.recipedetails;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import bme.msc.cookbook.R;
import bme.msc.cookbook.adapter.ViewPagerAdapter;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeToFavouritesEvent;
import bme.msc.cookbook.model.apiresult.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        toolbar = (Toolbar) findViewById(R.id.recipedetails_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.recipedetails_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.recipedetails_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.recipedetails_addtofavourites);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle intentExtras = getIntent().getExtras();
                Recipe recipe = new Recipe(
                        Long.parseLong(intentExtras.getString("id")),
                        intentExtras.getString("name"),
                        intentExtras.getString("imgurl"),
                        intentExtras.getString("totaltime"),
                        Float.parseFloat(intentExtras.getString("rating")),
                        intentExtras.getString("ingredients"),
                        intentExtras.getString("directions"),
                        intentExtras.getString("category")
                );
                AddRecipeToFavouritesEvent event = new AddRecipeToFavouritesEvent();
                event.setRecipe(recipe);
                EventBus.getDefault().post(event);
                Log.i("CookBookLog", "FloatingActionButton onClick");
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecipeSummaryFragment(), "Summary");
        adapter.addFragment(new RecipeIngredientsFragment(), "Ingredients");
        adapter.addFragment(new RecipeDirectionsFragment(), "Directions");
        viewPager.setAdapter(adapter);
    }
}
