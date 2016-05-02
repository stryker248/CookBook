package bme.msc.cookbook.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewRating {
    @SerializedName("recipe_id")
    @Expose
    private int recipeId;

    @SerializedName("rating")
    @Expose
    private int rating;

    public NewRating() {
    }

    public NewRating(int recipeId, int rating) {
        this.recipeId = recipeId;
        this.rating = rating;
    }

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
