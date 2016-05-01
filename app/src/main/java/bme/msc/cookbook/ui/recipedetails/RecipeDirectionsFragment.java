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

public class RecipeDirectionsFragment extends Fragment implements RecipeDetailsScreen {
    @Inject
    RecipeDirectionsPresenter recipeDirectionsPresenter;

    public RecipeDirectionsFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        recipeDirectionsPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        recipeDirectionsPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_directions, container, false);

        Bundle intentExtras = getActivity().getIntent().getExtras();
        String ingredients = intentExtras.getString("directions");

        TextView tvDirections = (TextView) view.findViewById(R.id.recipedetails_directions);
        tvDirections.setText(ingredients);

        return view;
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
