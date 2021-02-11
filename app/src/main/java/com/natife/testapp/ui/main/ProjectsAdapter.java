package com.natife.testapp.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import com.natife.testapp.databinding.ItemProjectBinding;
import com.natife.testapp.model.Project;

public class ProjectsAdapter extends ListAdapter<Project, ProjectViewHolder> {

    private final ItemClickListener listener;

    protected ProjectsAdapter(ItemClickListener listener) {
        super(new ProjectsDiffUtilCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectViewHolder(
                ItemProjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                position -> {
                    listener.onItemClicked(getCurrentList().get(position).id);
                }
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(getCurrentList().get(position));
    }

    interface ItemClickListener {
        void onItemClicked(int id);
    }
}
