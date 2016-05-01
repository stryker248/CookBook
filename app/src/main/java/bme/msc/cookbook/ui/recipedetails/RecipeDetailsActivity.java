package bme.msc.cookbook.ui.recipedetails;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import bme.msc.cookbook.R;
import bme.msc.cookbook.adapter.ViewPagerAdapter;

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
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecipeSummaryFragment(), "Summary");
        adapter.addFragment(new RecipeIngredientsFragment(), "Ingredients");
        adapter.addFragment(new RecipeDirectionsFragment(), "Directions");
        viewPager.setAdapter(adapter);
    }
}
