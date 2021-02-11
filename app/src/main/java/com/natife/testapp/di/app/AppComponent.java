package com.natife.testapp.di.app;

import com.natife.testapp.App;
import com.natife.testapp.di.activity.ActivityComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class,
                UseCaseBinding.class
        }
)
public interface AppComponent {

        @Component.Factory
        interface Factory {
                AppComponent create(@BindsInstance App app);
        }

        ActivityComponent.Factory activityComponent();
}
