package bme.msc.cookbook;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        CookBookApplication application = (CookBookApplication) RuntimeEnvironment.application;
        CookBookApplicationComponent injector =
                DaggerTestComponent.builder().testModule(
                        new TestModule(application.getApplicationContext())
                ).build();
        application.injector = injector;
    }
}
