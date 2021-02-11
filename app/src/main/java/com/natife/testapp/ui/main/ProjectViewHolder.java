package com.natife.testapp.ui.main;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.natife.testapp.ImageUtils;
import com.natife.testapp.databinding.ItemProjectBinding;
import com.natife.testapp.model.Project;

public class ProjectViewHolder extends RecyclerView.ViewHolder {

    private final ItemProjectBinding binding;

    public ProjectViewHolder(ItemProjectBinding binding, ItemClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(v -> listener.onItemClicked(getAdapterPosition()));
    }

    public void bind(Project project) {
        binding.nameTv.setText(project.name);
        ImageUtils.load(binding.iconIv, project.icon);
    }

    interface ItemClickListener {
        void onItemClicked(int position);
    }
}
