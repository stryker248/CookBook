package bme.msc.cookbook.ui.main;

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

public class MainActivity extends AppCompatActivity {
    private Drawer navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNavigationDrawer();
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
                                Toast.makeText(getApplicationContext(), ((Nameable) drawerItem).getName().getText(), Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(), ((Nameable) drawerItem).getName().getText(), Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(getApplicationContext(), ((Nameable) drawerItem).getName().getText(), Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(getApplicationContext(), ((Nameable) drawerItem).getName().getText(), Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(getApplicationContext(), ((Nameable) drawerItem).getName().getText(), Toast.LENGTH_SHORT).show();
                                break;
                        }

                        navigationDrawer.closeDrawer();
                        navigationDrawer.setSelection(-1, false);
                        return true;
                    }
                })
                .withSelectedItem(-1)
                .build();
    }
}
