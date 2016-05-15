package bme.msc.cookbook.interactor.recipes.event;

public class RecipeRemovedFromFavouritesEvent {
    String message;
    Long id;

    public RecipeRemovedFromFavouritesEvent() {
    }

    public RecipeRemovedFromFavouritesEvent(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
