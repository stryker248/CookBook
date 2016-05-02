package bme.msc.cookbook.interactor.recipes.event;

public class RemoveRecipeFromFavouritesEvent {
    String message;

    public RemoveRecipeFromFavouritesEvent() {
    }

    public RemoveRecipeFromFavouritesEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
