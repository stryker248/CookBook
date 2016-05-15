package bme.msc.cookbook.model.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewRecipe {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    @SerializedName("totalTime")
    @Expose
    private String totalTime;

    @SerializedName("ingredients")
    @Expose
    private String ingredients;

    @SerializedName("directions")
    @Expose
    private String directions;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("categoryId")
    @Expose
    private int categoryId;

    public NewRecipe() {
    }

    public NewRecipe(String name, String imgUrl, String totalTime, String ingredients,
                     String directions, String createdBy, int categoryId) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.totalTime = totalTime;
        this.ingredients = ingredients;
        this.directions = directions;
        this.createdBy = createdBy;
        this.categoryId = categoryId;
    }

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
