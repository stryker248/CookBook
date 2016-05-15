package bme.msc.cookbook.ui.newrecipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.R;
import bme.msc.cookbook.model.apiresult.Category;
import bme.msc.cookbook.model.apimodel.NewRecipe;
import bme.msc.cookbook.model.apiresult.Recipe;

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

        Button btnSave = (Button) view.findViewById(R.id.newrecipe_btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(getActivity());

                String userEmail = sharedPreferences.getString("pref_key_user_email", "");
                if (userEmail.length() == 0) {
                    showError("Email address not found! \nEnter your email in settings!");
                } else {
                    if (validateInput()) {
                        NewRecipe newRecipe = new NewRecipe();
                        newRecipe.setName(etName.getText().toString());
                        newRecipe.setImgUrl(etImgUrl.getText().toString());
                        newRecipe.setTotalTime(etTotalTime.getText().toString());
                        newRecipe.setIngredients(etIngredients.getText().toString());
                        newRecipe.setDirections(etDirections.getText().toString());
                        newRecipe.setCategoryId(((Category) spnnrCategory.getSelectedItem()).getId());
                        newRecipe.setCreatedBy(userEmail);

                        newRecipePresenter.addNewRecipe(newRecipe);
                    }
                }
            }
        });

        newRecipePresenter.refreshCategories();

        return view;
    }

    private boolean validateInput() {
        etName.setError(null);
        etImgUrl.setError(null);
        etTotalTime.setError(null);
        etIngredients.setError(null);
        etDirections.setError(null);

        boolean isValid = true;
        View focusView = null;

        if (TextUtils.isEmpty(etDirections.getText())) {
            etDirections.setError("Directions is required!");
            focusView = etDirections;
            isValid = false;
        }

        if (TextUtils.isEmpty(etIngredients.getText())) {
            etIngredients.setError("Ingredients is required!");
            focusView = etIngredients;
            isValid = false;
        }

        if (TextUtils.isEmpty(etTotalTime.getText())) {
            etTotalTime.setError("TotalTime is required!");
            focusView = etTotalTime;
            isValid = false;
        }

        if (TextUtils.isEmpty(etImgUrl.getText())) {
            etImgUrl.setError("ImgUrl is required!");
            focusView = etImgUrl;
            isValid = false;
        }

        if (TextUtils.isEmpty(etName.getText())) {
            etName.setError("Name is required!");
            focusView = etName;
            isValid = false;
        }

        if (!isValid) {
            focusView.requestFocus();
        }

        return isValid;
    }

    @Override
    public void addCategories(List<Category> categories) {
        ArrayAdapter<Category> dataAdapter = new ArrayAdapter<>(
                getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        spnnrCategory.setAdapter(dataAdapter);
    }

    @Override
    public void showNewRecipe(Recipe recipe) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("id", Long.toString(recipe.getId()));
        returnIntent.putExtra("name", recipe.getName());
        returnIntent.putExtra("category", recipe.getCategory());
        returnIntent.putExtra("imgurl", recipe.getImgUrl());
        returnIntent.putExtra("rating", Float.toString(recipe.getRating()));
        returnIntent.putExtra("totaltime", recipe.getTotalTime());
        returnIntent.putExtra("ingredients", recipe.getIngredients());
        returnIntent.putExtra("directions", recipe.getDirections());
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
