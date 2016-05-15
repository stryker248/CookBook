package bme.msc.cookbook;

import android.app.Application;

import com.orm.SugarContext;

import bme.msc.cookbook.ui.UIModule;

public class CookBookApplication extends Application {
    public static CookBookApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);

        injector =
                DaggerCookBookApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        SugarContext.terminate();
    }
}
