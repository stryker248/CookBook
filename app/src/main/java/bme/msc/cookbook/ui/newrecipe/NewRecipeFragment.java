package bme.msc.cookbook.ui.newrecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.R;
import bme.msc.cookbook.model.apiresult.Category;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.recipedetails.RecipeDetailsActivity;

public class NewRecipeFragment extends Fragment implements NewRecipeScreen {
    private EditText etName;
    private EditText etImgUrl;
    private EditText etTotalTime;
    private Spinner spnnrCategory;
    private EditText etIngredients;
    private EditText etDirections;

    @Inject
    NewRecipePresenter newRecipePresenter;

    public NewRecipeFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        newRecipePresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        newRecipePresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_recipe, container, false);

        etName = (EditText) view.findViewById(R.id.newrecipe_etName);
        etImgUrl = (EditText) view.findViewById(R.id.newrecipe_etImgUrl);
        etTotalTime = (EditText) view.findViewById(R.id.newrecipe_etTotalTime);
        spnnrCategory = (Spinner) view.findViewById(R.id.newrecipe_spnnrCategory);
        etIngredients = (EditText) view.findViewById(R.id.newrecipe_etIngredients);
        etDirections = (EditText) view.findViewById(R.id.newrecipe_etDirections);

        //TODO: kategóriák lekérése
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Test category 1"));
        categories.add(new Category(2, "Test category 2"));
        categories.add(new Category(3, "Test category 3"));
        categories.add(new Category(4, "Test category 4"));
        ArrayAdapter<Category> dataAdapter = new ArrayAdapter<>(
                getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        spnnrCategory.setAdapter(dataAdapter);

        Button btnSave = (Button) view.findViewById(R.id.newrecipe_btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRecipe newRecipe = new NewRecipe();
                newRecipe.setName(etName.getText().toString());
                newRecipe.setImgUrl(etImgUrl.getText().toString());
                newRecipe.setTotalTime(etTotalTime.getText().toString());
                newRecipe.setIngredients(etIngredients.getText().toString());
                newRecipe.setDirections(etDirections.getText().toString());
                newRecipe.setCreatedBy("testuser"); //TODO: módosítani beállításokra
                newRecipe.setCategoryId(((Category) spnnrCategory.getSelectedItem()).getId());

                newRecipePresenter.addNewRecipe(newRecipe);
            }
        });

        return view;
    }

    @Override
    public void showNewRecipe(Recipe recipe) {
        Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
        intent.putExtra("id", recipe.getId());
        intent.putExtra("name", recipe.getName());
        intent.putExtra("category", recipe.getCategory());
        intent.putExtra("imgurl", recipe.getImgUrl());
        intent.putExtra("rating", Float.toString(recipe.getRating()));
        intent.putExtra("totaltime", recipe.getTotalTime());
        intent.putExtra("ingredients", recipe.getIngredients());
        intent.putExtra("directions", recipe.getDirections());
        getContext().startActivity(intent);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
