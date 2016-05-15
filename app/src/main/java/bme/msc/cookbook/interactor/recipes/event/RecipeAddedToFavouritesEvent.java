package bme.msc.cookbook.interactor.recipes.event;

public class RecipeAddedToFavouritesEvent {
    String message;

    public RecipeAddedToFavouritesEvent() {
    }

    public RecipeAddedToFavouritesEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
