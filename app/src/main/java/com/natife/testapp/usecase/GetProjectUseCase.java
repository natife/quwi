package com.natife.testapp.usecase;

import com.natife.testapp.api.ApiService;
import com.natife.testapp.model.Project;
import io.reactivex.Single;
import javax.inject.Inject;

public class GetProjectUseCase {

    private final ApiService apiService;

    @Inject
    public GetProjectUseCase(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<Project> execute(int id) {
        return apiService.getProject(id)
                .map(response -> new Project(
                        response.project.id,
                        response.project.name,
                        response.project.icon
                ));
    }
}
