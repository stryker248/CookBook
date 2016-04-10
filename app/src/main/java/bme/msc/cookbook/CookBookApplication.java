package bme.msc.cookbook;

import android.app.Application;

import bme.msc.cookbook.ui.UIModule;

public class CookBookApplication extends Application {
    public static CookBookApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerCookBookApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
