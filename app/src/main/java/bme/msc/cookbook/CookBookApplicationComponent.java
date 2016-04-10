package bme.msc.cookbook;

import javax.inject.Singleton;

import bme.msc.cookbook.interactor.InteractorModule;
import bme.msc.cookbook.network.NetworkModule;
import bme.msc.cookbook.ui.UIModule;
import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class, InteractorModule.class})
public interface CookBookApplicationComponent {
}
