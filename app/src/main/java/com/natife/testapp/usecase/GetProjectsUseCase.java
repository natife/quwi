package com.natife.testapp.usecase;

import com.natife.testapp.api.ApiService;
import com.natife.testapp.model.Project;
import io.reactivex.Single;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class GetProjectsUseCase {

    private ApiService apiService;

    @Inject
    public GetProjectsUseCase(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<List<Project>> execute() {
        return apiService.getProjects()
                .map(response -> response.projects.stream()
                        .map(projectDto -> new Project(
                                projectDto.id,
                                projectDto.name,
                                projectDto.icon
                        )).collect(Collectors.toList()));
    }
}
