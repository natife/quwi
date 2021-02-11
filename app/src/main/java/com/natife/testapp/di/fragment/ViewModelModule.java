package com.natife.testapp.di.fragment;

import androidx.lifecycle.ViewModel;

import com.natife.testapp.di.ViewModelKey;
import com.natife.testapp.ui.details.DetailsViewModel;
import com.natife.testapp.ui.login.LoginViewModel;
import com.natife.testapp.ui.main.MainViewModel;
import com.natife.testapp.ui.start.StartViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel.class)
    ViewModel bindStartViewModel(StartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    ViewModel bindDetailsViewModel(DetailsViewModel viewModel);
}
