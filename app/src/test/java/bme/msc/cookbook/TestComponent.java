package bme.msc.cookbook;

import javax.inject.Singleton;

import bme.msc.cookbook.interactor.InteractorModule;
import bme.msc.cookbook.mock.manager.MockManagerModule;
import bme.msc.cookbook.mock.network.MockNetworkModule;
import bme.msc.cookbook.test.network.CategoriesApiTest;
import dagger.Component;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, MockManagerModule.class})
public interface TestComponent extends CookBookApplicationComponent {
}
