package bme.msc.cookbook.interactor.recipes.event;

public class RemoveRecipeFromFavouritesEvent {
    Long id;

    public RemoveRecipeFromFavouritesEvent() {
    }

    public RemoveRecipeFromFavouritesEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
