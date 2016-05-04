package bme.msc.cookbook.ui.recipedetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
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

public class RecipeSummaryFragment extends Fragment implements RecipeDetailsScreen {
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
        View view = inflater.inflate(R.layout.fragment_recipe_summary, container, false);

        Bundle intentExtras = getActivity().getIntent().getExtras();
        String id = intentExtras.getString("id");
        String name = intentExtras.getString("name");
        String imgUrl = intentExtras.getString("imgurl");
        String category = intentExtras.getString("category");
        String rating = intentExtras.getString("rating");
        String totalTime = intentExtras.getString("totaltime");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);
        ImageView ivImage = (ImageView) view.findViewById(R.id.recipedetails_image);
        TextView tvCategory = (TextView) view.findViewById(R.id.recipedetails_category);
        TextView tvTotalTime = (TextView) view.findViewById(R.id.recipedetails_totalTime);
        RatingBar rbRating = (RatingBar) view.findViewById(R.id.recipedetails_rating);

        Glide.with(getContext()).load(imgUrl).into(ivImage);
        tvCategory.setText(category);
        tvTotalTime.setText("Total time: " + totalTime);
        rbRating.setRating(Float.parseFloat(rating));

        Log.i("CookBookLog", "Rating: " + rbRating.getRating());

        return view;
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
