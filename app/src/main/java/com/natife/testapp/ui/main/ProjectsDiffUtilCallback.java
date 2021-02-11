package com.natife.testapp.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.natife.testapp.model.Project;

class ProjectsDiffUtilCallback extends DiffUtil.ItemCallback<Project> {

    @Override
    public boolean areItemsTheSame(@NonNull Project oldItem, @NonNull Project newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Project oldItem, @NonNull Project newItem) {
        return oldItem.equals(newItem);
    }
}
