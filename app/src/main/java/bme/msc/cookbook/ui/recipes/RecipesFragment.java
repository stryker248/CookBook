package bme.msc.cookbook.ui.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import bme.msc.cookbook.model.apiresult.Recipe;

public class RecipesFragment extends Fragment implements RecipesScreen {
    private RecyclerView recyclerViewRecipes;
    private SwipeRefreshLayout swipeRefreshLayoutRecipes;
    private TextView tvEmpty;
    private List<Recipe> recipesList;
    private RecipesAdapter recipesAdapter;

    @Inject
    RecipesPresenter recipesPresenter;

    public RecipesFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        recipesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        recipesPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        tvEmpty = (TextView) view.findViewById(R.id.recipes_tvEmpty);

        recyclerViewRecipes = (RecyclerView) view.findViewById(R.id.recipes_recyclerViewRecipes);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecipes.setLayoutManager(llm);

        recipesList = new ArrayList<>();
        recipesAdapter = new RecipesAdapter(getContext(), recipesList);
        recyclerViewRecipes.setAdapter(recipesAdapter);

        swipeRefreshLayoutRecipes = (SwipeRefreshLayout) view.findViewById(R.id.recipes_swipeRefreshLayoutRecipes);
        swipeRefreshLayoutRecipes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recipesPresenter.refreshRecipes();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recipesPresenter.refreshRecipes();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        if (swipeRefreshLayoutRecipes != null) {
            swipeRefreshLayoutRecipes.setRefreshing(false);
        }

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
        if (swipeRefreshLayoutRecipes != null) {
            swipeRefreshLayoutRecipes.setRefreshing(false);
        }

        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
