package com.natife.testapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.natife.testapp.base.BaseFragment;
import com.natife.testapp.databinding.FragmentMainBinding;
import com.natife.testapp.di.fragment.FragmentComponent;

public class MainFragment extends BaseFragment<MainViewModel> {

    private FragmentMainBinding binding;

    private ProjectsAdapter adapter = new ProjectsAdapter(id -> viewModel.navigateToDetails(id));

    @Override
    protected View createBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void injectWith(FragmentComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.recyclerView.setAdapter(adapter);
        viewModel.projectsLiveData.observe(
                getViewLifecycleOwner(),
                projects -> adapter.submitList(projects)
        );

        viewModel.loadingLiveData.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.recyclerView.setVisibility(View.GONE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }
}
