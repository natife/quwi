package com.natife.testapp.ui.start;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.natife.testapp.base.BaseFragment;
import com.natife.testapp.databinding.FragmentStartBinding;
import com.natife.testapp.di.fragment.FragmentComponent;

public class StartFragment extends BaseFragment<StartViewModel> {

    @Override
    protected View createBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentStartBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    protected void injectWith(FragmentComponent component) {
        component.inject(this);
    }
}

