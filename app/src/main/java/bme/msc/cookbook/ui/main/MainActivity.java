package bme.msc.cookbook.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import bme.msc.cookbook.R;
import bme.msc.cookbook.ui.about.AboutActivity;
import bme.msc.cookbook.ui.newrecipe.NewRecipeActivity;
import bme.msc.cookbook.ui.recipes.RecipesActivity;
import bme.msc.cookbook.ui.savedrecipes.SavedRecipesActivity;
import bme.msc.cookbook.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    private Drawer navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNavigationDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (navigationDrawer != null) {
            navigationDrawer.setSelection(-1, false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (navigationDrawer != null) {
            navigationDrawer.closeDrawer();
            navigationDrawer.setSelection(-1, false);
        }
    }

    private void createNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_header_background)
                .addProfiles(
                        new ProfileDrawerItem().withName("").withEmail("").withIcon(R.drawable.drawer_header_profile)
                )
                .build();

        navigationDrawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName("Recipes").withIcon(FontAwesome.Icon.faw_list),
                        new PrimaryDrawerItem().withIdentifier(2).withName("New recipe").withIcon(FontAwesome.Icon.faw_plus),
                        new PrimaryDrawerItem().withIdentifier(3).withName("Saved recipes").withIcon(FontAwesome.Icon.faw_floppy_o),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withIdentifier(4).withName("Settings").withIcon(FontAwesome.Icon.faw_cog),
                        new PrimaryDrawerItem().withIdentifier(5).withName("About").withIcon(FontAwesome.Icon.faw_info)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        int id = (int)drawerItem.getIdentifier();
                        switch (id) {
                            case 1:
                                startActivity(new Intent(MainActivity.this, RecipesActivity.class));
                                break;
                            case 2:
                                startActivity(new Intent(MainActivity.this, NewRecipeActivity.class));
                                break;
                            case 3:
                                startActivity(new Intent(MainActivity.this, SavedRecipesActivity.class));
                                break;
                            case 4:
                                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                                break;
                            case 5:
                                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                                break;
                        }

                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        return true;
                    }
                })
                .build();
    }
}
