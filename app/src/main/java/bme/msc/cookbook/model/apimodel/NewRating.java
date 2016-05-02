package bme.msc.cookbook.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewRating {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("rating")
    @Expose
    private int rating;

    public NewRating() {
    }

    public NewRating(Long id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public Long getRecipeId() {
        return id;
    }

    public void setRecipeId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
