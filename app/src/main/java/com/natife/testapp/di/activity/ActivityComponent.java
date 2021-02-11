package com.natife.testapp.di.activity;

import android.app.Activity;

import com.natife.testapp.MainActivity;
import com.natife.testapp.di.fragment.FragmentComponent;
import com.natife.testapp.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        ActivityComponent create(@BindsInstance Activity activity);
    }

    FragmentComponent.Factory fragmentComponent();

    void inject(MainActivity activity);
}
