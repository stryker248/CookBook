package bme.msc.cookbook.model.apiresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    @SerializedName("totalTime")
    @Expose
    private String totalTime;

    @SerializedName("rating")
    @Expose
    private float rating;

    @SerializedName("ingredients")
    @Expose
    private String ingredients;

    @SerializedName("directions")
    @Expose
    private String directions;

    @SerializedName("category")
    @Expose
    private String category;

    public Recipe() {
    }

    public Recipe(Long id, String name, String imgUrl, String totalTime, float rating,
                  String ingredients, String directions, String category) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.totalTime = totalTime;
        this.rating = rating;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
