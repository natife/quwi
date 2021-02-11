package com.natife.testapp.di.fragment;

import androidx.fragment.app.Fragment;

import com.natife.testapp.di.scope.FragmentScope;
import com.natife.testapp.ui.details.DetailsFragment;
import com.natife.testapp.ui.login.LoginFragment;
import com.natife.testapp.ui.main.MainFragment;
import com.natife.testapp.ui.start.StartFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = {
                FragmentModule.class,
                ViewModelModule.class
        }
)
public interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        FragmentComponent create(@BindsInstance Fragment fragment);
    }

    void inject(StartFragment startFragment);

    void inject(LoginFragment mainFragment);

    void inject(MainFragment mainFragment);

    void inject(DetailsFragment mainFragment);
}
