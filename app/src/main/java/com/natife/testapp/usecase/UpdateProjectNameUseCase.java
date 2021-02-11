package com.natife.testapp.usecase;

import com.natife.testapp.api.ApiService;
import com.natife.testapp.listeners.ProjectChangeListener;
import com.natife.testapp.model.Project;
import io.reactivex.Completable;
import javax.inject.Inject;

public class UpdateProjectNameUseCase {

    private final ApiService apiService;
    private final ProjectChangeListener listener;

    @Inject
    public UpdateProjectNameUseCase(ApiService apiService, ProjectChangeListener listener) {
        this.apiService = apiService;
        this.listener = listener;
    }

    public Completable execute(int id, String name) {
        return apiService.updateProjectName(id, name)
                .doOnSuccess(response -> listener.notifyChanged(
                        new Project(
                                response.project.id,
                                response.project.name,
                                response.project.icon
                        )))
                .ignoreElement();
    }
}
