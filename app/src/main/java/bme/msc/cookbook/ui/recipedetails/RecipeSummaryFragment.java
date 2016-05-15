package bme.msc.cookbook.ui.recipedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import bme.msc.cookbook.CookBookApplication;
import bme.msc.cookbook.R;
import bme.msc.cookbook.model.apiresult.Recipe;

public class RecipeSummaryFragment extends Fragment implements RecipeDetailsScreen {
    private View view;
    private boolean isRated;

    @Inject
    RecipeSummaryPresenter recipeSummaryPresenter;

    public RecipeSummaryFragment() {
        CookBookApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        recipeSummaryPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        recipeSummaryPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipe_summary, container, false);

        Bundle intentExtras = getActivity().getIntent().getExtras();
        final String id = intentExtras.getString("id");
        String name = intentExtras.getString("name");
        String imgUrl = intentExtras.getString("imgurl");
        String category = intentExtras.getString("category");
        String rating = intentExtras.getString("rating");
        String totalTime = intentExtras.getString("totaltime");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);
        ImageView ivImage = (ImageView) view.findViewById(R.id.recipedetails_image);
        TextView tvCategory = (TextView) view.findViewById(R.id.recipedetails_category);
        TextView tvTotalTime = (TextView) view.findViewById(R.id.recipedetails_totalTime);
        final RatingBar rbRating = (RatingBar) view.findViewById(R.id.recipedetails_rating);
        rbRating.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (isRated) {
                        rbRating.setRating(rbRating.getRating());
                        Toast.makeText(getContext(), "You already rated this recipe!", Toast.LENGTH_LONG).show();
                    } else {
                        float touchPositionX = event.getX();
                        float width = rbRating.getWidth();
                        float starsf = (touchPositionX / width) * 5.0f;
                        int stars = (int)starsf + 1;

                        rbRating.setRating(stars);
                        isRated = true;

                        recipeSummaryPresenter.rateRecipe(Long.parseLong(id), stars);
                    }

                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return true;
            }
        });

        Glide.with(getContext()).load(imgUrl).into(ivImage);
        tvCategory.setText(category);
        tvTotalTime.setText(totalTime);
        rbRating.setRating(Float.parseFloat(rating));

        recipeSummaryPresenter.updateFavouriteRecipeVisitDate(Long.parseLong(id));

        return view;
    }

    @Override
    public void showMessage(String errorMessage) {
        RatingBar rbRating = (RatingBar) view.findViewById(R.id.recipedetails_rating);
        isRated = false;
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        RatingBar rbRating = (RatingBar) view.findViewById(R.id.recipedetails_rating);
        rbRating.setRating(recipe.getRating());
    }
}
