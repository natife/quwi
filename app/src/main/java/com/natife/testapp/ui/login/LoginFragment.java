package com.natife.testapp.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.natife.testapp.base.BaseFragment;
import com.natife.testapp.databinding.FragmentLoginBinding;
import com.natife.testapp.di.fragment.FragmentComponent;

public class LoginFragment extends BaseFragment<LoginViewModel> {

    private FragmentLoginBinding binding;

    @Override
    protected View createBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void injectWith(FragmentComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.loginBtn.setOnClickListener(v -> {
            viewModel.login(
                    binding.emailEt.getText().toString(),
                    binding.passwordEt.getText().toString()
            );
        });

        viewModel.loadingLiveData.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.loginBtn.setVisibility(View.GONE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                binding.loginBtn.setVisibility(View.VISIBLE);
            }
        });
    }
}
