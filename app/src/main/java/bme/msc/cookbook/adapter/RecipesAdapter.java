package bme.msc.cookbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bme.msc.cookbook.R;
import bme.msc.cookbook.model.RecipeBase;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.recipedetails.RecipeDetailsActivity;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private Context context;
    private List<? extends RecipeBase> recipesList;

    public RecipesAdapter(Context context, List<? extends RecipeBase> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_recipe, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeBase recipe = recipesList.get(position);

        Glide.with(context).load(recipe.getImgUrl()).into(holder.ivImage);
        holder.tvName.setText(recipe.getName());
        holder.tvCategory.setText(recipe.getCategory());
        holder.tvRating.setText(Double.toString(recipe.getRating()));
        holder.tvCookingTime.setText(recipe.getCookingTime());
        holder.tvId.setText(Long.toString(recipe.getId()));
        holder.tvImgUrl.setText(recipe.getImgUrl());
        holder.tvIngredients.setText(recipe.getIngredients());
        holder.tvDirections.setText(recipe.getDirections());
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivImage;
        public TextView tvName;
        public TextView tvCategory;
        public TextView tvRating;
        public TextView tvCookingTime;
        public TextView tvId;
        public TextView tvImgUrl;
        public TextView tvIngredients;
        public TextView tvDirections;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            ivImage = (ImageView) itemView.findViewById(R.id.cardRecipe_ivImage);
            tvName = (TextView) itemView.findViewById(R.id.cardRecipe_tvName);
            tvCategory = (TextView) itemView.findViewById(R.id.cardRecipe_tvCategory);
            tvRating = (TextView) itemView.findViewById(R.id.cardRecipe_tvRating);
            tvCookingTime = (TextView) itemView.findViewById(R.id.cardRecipe_tvCookingTime);
            tvId = (TextView) itemView.findViewById(R.id.cardRecipe_tvId);
            tvImgUrl = (TextView) itemView.findViewById(R.id.cardRecipe_tvImgUrl);
            tvIngredients = (TextView) itemView.findViewById(R.id.cardRecipe_tvIngredients);
            tvDirections = (TextView) itemView.findViewById(R.id.cardRecipe_tvDirections);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), RecipeDetailsActivity.class);
            intent.putExtra("id", ((TextView) itemView.findViewById(R.id.cardRecipe_tvId)).getText());
            intent.putExtra("name", ((TextView) itemView.findViewById(R.id.cardRecipe_tvName)).getText());
            intent.putExtra("category", ((TextView) itemView.findViewById(R.id.cardRecipe_tvCategory)).getText());
            intent.putExtra("imgurl", ((TextView) itemView.findViewById(R.id.cardRecipe_tvImgUrl)).getText());
            intent.putExtra("rating", ((TextView) itemView.findViewById(R.id.cardRecipe_tvRating)).getText());
            intent.putExtra("cookingtime", ((TextView) itemView.findViewById(R.id.cardRecipe_tvCookingTime)).getText());
            intent.putExtra("ingredients", ((TextView) itemView.findViewById(R.id.cardRecipe_tvIngredients)).getText());
            intent.putExtra("directions", ((TextView) itemView.findViewById(R.id.cardRecipe_tvDirections)).getText());
            v.getContext().startActivity(intent);
        }
    }
}
