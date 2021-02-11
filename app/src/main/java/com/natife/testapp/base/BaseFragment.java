package com.natife.testapp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.natife.testapp.di.fragment.FragmentComponent;
import java.lang.reflect.ParameterizedType;
import javax.inject.Inject;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    @Inject
    ViewModelProvider.Factory factory;

    protected VM viewModel;

    protected FragmentComponent component;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        component = ((BaseActivity) context).component
                .fragmentComponent()
                .create(this);
        injectWith(component);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, factory).get(getViewModelClass());
        viewModel.errorLiveData.observe(getViewLifecycleOwner(), this::onError);
        return createBinding(inflater, container);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    private Class<VM> getViewModelClass() {
        return (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected abstract View createBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void injectWith(FragmentComponent component);

    protected void onError(Throwable throwable) {
        Toast.makeText(
                getContext(),
                throwable.getLocalizedMessage(),
                Toast.LENGTH_SHORT
        ).show();
    }
}
