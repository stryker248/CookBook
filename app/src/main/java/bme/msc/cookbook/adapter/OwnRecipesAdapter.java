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
import bme.msc.cookbook.model.orm.OwnRecipe;
import bme.msc.cookbook.ui.recipedetails.RecipeDetailsActivity;

public class OwnRecipesAdapter extends RecyclerView.Adapter<OwnRecipesAdapter.ViewHolder> {
    private Context context;
    private List<OwnRecipe> recipesList;

    public OwnRecipesAdapter(Context context, List<OwnRecipe> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_own_recipe, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OwnRecipe recipe = recipesList.get(position);

        Glide.with(context).load(recipe.getImgUrl()).into(holder.ivRecipeImage);
        holder.tvName.setText(recipe.getName());
        holder.tvCategory.setText(recipe.getCategory());
        holder.tvRating.setText(Float.toString(recipe.getRating()));
        holder.tvTotalTime.setText(recipe.getTotalTime());
        holder.tvId.setText(Long.toString(recipe.getRecipeId()));
        holder.tvImgUrl.setText(recipe.getImgUrl());
        holder.tvIngredients.setText(recipe.getIngredients());
        holder.tvDirections.setText(recipe.getDirections());
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public ImageView ivRecipeImage;
        public TextView tvName;
        public TextView tvCategory;
        public TextView tvRating;
        public TextView tvTotalTime;
        public TextView tvId;
        public TextView tvImgUrl;
        public TextView tvIngredients;
        public TextView tvDirections;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            ivRecipeImage = (ImageView) itemView.findViewById(R.id.cardOwnRecipe_ivRecipeImage);
            tvName = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvName);
            tvCategory = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvCategory);
            tvRating = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvRating);
            tvTotalTime = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvTotalTime);
            tvId = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvId);
            tvImgUrl = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvImgUrl);
            tvIngredients = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvIngredients);
            tvDirections = (TextView) itemView.findViewById(R.id.cardOwnRecipe_tvDirections);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), RecipeDetailsActivity.class);
            intent.putExtra("id", tvId.getText());
            intent.putExtra("name", tvName.getText());
            intent.putExtra("category", tvCategory.getText());
            intent.putExtra("imgurl", tvImgUrl.getText());
            intent.putExtra("rating", tvRating.getText());
            intent.putExtra("totaltime", tvTotalTime.getText());
            intent.putExtra("ingredients", tvIngredients.getText());
            intent.putExtra("directions", tvDirections.getText());
            v.getContext().startActivity(intent);
        }
    }
}
