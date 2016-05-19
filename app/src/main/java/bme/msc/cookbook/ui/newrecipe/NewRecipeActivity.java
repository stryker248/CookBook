package bme.msc.cookbook.ui.newrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import bme.msc.cookbook.R;

public class NewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.newrecipe_toolbar);
        setSupportActionBar(toolbar);
    }
}
