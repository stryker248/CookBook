package bme.msc.cookbook.model.orm;

import com.orm.SugarRecord;

public class OwnRecipe extends SugarRecord {
    private Long recipeId;
    private String name;
    private String imgUrl;
    private String totalTime;
    private float rating;
    private String ingredients;
    private String directions;
    private String category;

    public OwnRecipe() {
    }

    public OwnRecipe(Long recipeId, String name, String imgUrl, String totalTime, float rating,
                           String ingredients, String directions, String category) {
        this.recipeId = recipeId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.totalTime = totalTime;
        this.rating = rating;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
    }

    public Long getRecipeId() { return recipeId; }

    public void setRecipeId(Long recipeId) { this.recipeId = recipeId; }

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

    public void setTotalTimeTime(String totalTime) {
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