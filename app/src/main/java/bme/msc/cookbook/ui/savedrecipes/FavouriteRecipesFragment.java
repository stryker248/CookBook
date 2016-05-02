
package bme.msc.cookbook.ui.savedrecipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import bme.msc.cookbook.model.orm.FavouriteRecipe;

public class FavouriteRecipesFragment extends Fragment implements FavouriteRecipesScreen {
    private RecyclerView recyclerViewRecipes;
    private TextView tvEmpty;
    private List<FavouriteRecipe> recipesList;
    private RecipesAdapter recipesAdapter;

    @Inject
    FavouriteRecipesPresenter favouriteRecipesPresenter;

    public FavouriteRecipesFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        favouriteRecipesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        favouriteRecipesPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_recipes, container, false);

        tvEmpty = (TextView) view.findViewById(R.id.savedrecipes_favourite_tvEmpty);

        recyclerViewRecipes = (RecyclerView) view.findViewById(R.id.savedrecipes_favourite_recyclerViewRecipes);
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
        favouriteRecipesPresenter.refreshRecipes();
    }

    @Override
    public void showRecipes(List<FavouriteRecipe> recipes) {
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
