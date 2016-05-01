package bme.msc.cookbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewRating {
    @SerializedName("recipe_id")
    @Expose
    private int recipeId;

    @SerializedName("rating")
    @Expose
    private int rating;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
