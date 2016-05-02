package bme.msc.cookbook.interactor.recipes.event;

public class AddRecipeToFavouritesEvent {
    String message;

    public AddRecipeToFavouritesEvent() {
    }

    public AddRecipeToFavouritesEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
