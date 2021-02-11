package com.natife.testapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.natife.testapp.databinding.FragmentEditNameBinding;

public class EditNameDialog extends DialogFragment {

    public static final String KEY_NAME = "KEY_NAME";

    private FragmentEditNameBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditNameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditNameDialogArgs args = EditNameDialogArgs.fromBundle(getArguments());
        binding.nameEt.setText(args.getName());
        binding.cancelBtn.setOnClickListener(v -> dismiss());
        binding.saveBtn.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_NAME, binding.nameEt.getText().toString());
            getParentFragmentManager().setFragmentResult("qwerty", bundle);
        });
    }
}
