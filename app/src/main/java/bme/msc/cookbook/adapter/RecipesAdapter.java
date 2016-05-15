package bme.msc.cookbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bme.msc.cookbook.R;
import bme.msc.cookbook.interactor.recipes.event.AddRecipeToFavouritesEvent;
import bme.msc.cookbook.model.apiresult.Recipe;
import bme.msc.cookbook.ui.recipedetails.RecipeDetailsActivity;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private Context context;
    private List<Recipe> recipesList;

    public RecipesAdapter(Context context, List<Recipe> recipesList) {
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
        Recipe recipe = recipesList.get(position);

        Glide.with(context).load(recipe.getImgUrl()).into(holder.ivRecipeImage);
        holder.tvName.setText(recipe.getName());
        holder.tvCategory.setText(recipe.getCategory());
        holder.tvRating.setText(Float.toString(recipe.getRating()));
        holder.tvTotalTime.setText(recipe.getTotalTime());
        holder.tvId.setText(Long.toString(recipe.getId()));
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

            ivRecipeImage = (ImageView) itemView.findViewById(R.id.cardRecipe_ivRecipeImage);
            tvName = (TextView) itemView.findViewById(R.id.cardRecipe_tvName);
            tvCategory = (TextView) itemView.findViewById(R.id.cardRecipe_tvCategory);
            tvRating = (TextView) itemView.findViewById(R.id.cardRecipe_tvRating);
            tvTotalTime = (TextView) itemView.findViewById(R.id.cardRecipe_tvTotalTime);
            tvId = (TextView) itemView.findViewById(R.id.cardRecipe_tvId);
            tvImgUrl = (TextView) itemView.findViewById(R.id.cardRecipe_tvImgUrl);
            tvIngredients = (TextView) itemView.findViewById(R.id.cardRecipe_tvIngredients);
            tvDirections = (TextView) itemView.findViewById(R.id.cardRecipe_tvDirections);

            ImageButton contextMenu = (ImageButton) itemView.findViewById(R.id.cardRecipe_contextMenuButton);
            contextMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu(v);
                }
            });
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

        private void showPopupMenu(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
            popupMenu.inflate(R.menu.card_recipe_menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Recipe recipe = new Recipe(
                            Long.parseLong(tvId.getText().toString()),
                            tvName.getText().toString(),
                            tvImgUrl.getText().toString(),
                            tvTotalTime.getText().toString(),
                            Float.parseFloat(tvRating.getText().toString()),
                            tvIngredients.getText().toString(),
                            tvDirections.getText().toString(),
                            tvCategory.getText().toString()
                    );
                    AddRecipeToFavouritesEvent event = new AddRecipeToFavouritesEvent();
                    event.setRecipe(recipe);
                    EventBus.getDefault().post(event);
                    return true;
                }
            });
            popupMenu.show();
        }
    }
}
