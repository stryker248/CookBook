package bme.msc.cookbook.model;

public interface RecipeBase {
    Long getId();

    String getName();

    String getImgUrl();

    String getTotalTime();

    float getRating();

    String getIngredients();

    String getDirections();

    String getCategory();
}
