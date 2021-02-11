package com.natife.testapp.di.fragment;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.natife.testapp.ViewModelProviderFactory;
import com.natife.testapp.di.scope.FragmentScope;
import com.natife.testapp.ui.EditNameDialogArgs;
import com.natife.testapp.ui.details.DetailsFragmentArgs;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class FragmentModule {

    @Provides
    @FragmentScope
    static ViewModelProvider.Factory viewModelFactory(ViewModelProviderFactory factory) {
        return factory;
    }

    @Provides
    @FragmentScope
    static DetailsFragmentArgs projectId(Fragment fragment) {
        return DetailsFragmentArgs.fromBundle(fragment.getArguments());
    }

    @Provides
    @FragmentScope
    static EditNameDialogArgs name(Fragment fragment) {
        return EditNameDialogArgs.fromBundle(fragment.getArguments());
    }
}
