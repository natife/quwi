package com.natife.testapp.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.natife.testapp.ImageUtils;
import com.natife.testapp.R;
import com.natife.testapp.base.BaseFragment;
import com.natife.testapp.databinding.FragmentDetailsBinding;
import com.natife.testapp.di.fragment.FragmentComponent;

import static com.natife.testapp.ui.EditNameDialog.KEY_NAME;

public class DetailsFragment extends BaseFragment<DetailsViewModel> {

    private FragmentDetailsBinding binding;

    @Override
    protected View createBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void injectWith(FragmentComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        binding.toolbar.setNavigationOnClickListener(v -> viewModel.back());
        binding.editNameBtn.setOnClickListener(v -> {
            viewModel.editName();
        });

        viewModel.projectLiveData.observe(getViewLifecycleOwner(), project -> {
            binding.nameTv.setText(project.name);
            ImageUtils.load(binding.iconIv, project.icon);
        });

        getParentFragmentManager().setFragmentResultListener("qwerty", getViewLifecycleOwner(), (requestKey, result) -> {
            String name = result.getString(KEY_NAME);
            if (name != null) {
                viewModel.updateName(name);
            }
        });
    }
}
