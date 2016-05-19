package bme.msc.cookbook.ui.savedrecipes;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import bme.msc.cookbook.R;
import bme.msc.cookbook.adapter.ViewPagerAdapter;

public class SavedRecipesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        toolbar = (Toolbar) findViewById(R.id.savedrecipes_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.savedrecipes_viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.savedrecipes_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavouriteRecipesFragment(), "Favourite recipes");
        adapter.addFragment(new OwnRecipesFragment(), "Own recipes");
        viewPager.setAdapter(adapter);
    }
}
