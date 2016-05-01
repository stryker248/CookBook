package bme.msc.cookbook.ui.recipedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.R;

public class RecipeIngredientsFragment extends Fragment implements RecipeDetailsScreen {
    @Inject
    RecipeIngredientsPresenter recipeIngredientsPresenter;

    public RecipeIngredientsFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        recipeIngredientsPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        recipeIngredientsPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);

        Bundle intentExtras = getActivity().getIntent().getExtras();
        String ingredients = intentExtras.getString("ingredients");

        TextView tvIngredients = (TextView) view.findViewById(R.id.recipedetails_ingredients);
        tvIngredients.setText(ingredients);

        return view;
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
