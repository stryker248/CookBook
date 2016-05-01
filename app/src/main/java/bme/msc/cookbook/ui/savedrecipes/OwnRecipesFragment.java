package bme.msc.cookbook.ui.savedrecipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.R;
import bme.msc.cookbook.adapter.RecipesAdapter;
import bme.msc.cookbook.model.Recipe;

public class OwnRecipesFragment extends Fragment implements SavedRecipesScreen {
    private RecyclerView recyclerViewRecipes;
    private TextView tvEmpty;
    private List<Recipe> recipesList;
    private RecipesAdapter recipesAdapter;

    @Inject
    OwnRecipesPresenter ownRecipesPresenter;

    public OwnRecipesFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        ownRecipesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        ownRecipesPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_own_recipes, container, false);

        tvEmpty = (TextView) view.findViewById(R.id.savedrecipes_own_tvEmpty);

        recyclerViewRecipes = (RecyclerView) view.findViewById(R.id.savedrecipes_own_recyclerViewRecipes);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecipes.setLayoutManager(llm);

        recipesList = new ArrayList<>();
        recipesAdapter = new RecipesAdapter(getContext(), recipesList);
        recyclerViewRecipes.setAdapter(recipesAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ownRecipesPresenter.refreshRecipes();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipesList.clear();
        recipesList.addAll(recipes);
        recipesAdapter.notifyDataSetChanged();

        if(recipesList.isEmpty()) {
            recyclerViewRecipes.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerViewRecipes.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetworkError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
