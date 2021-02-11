package com.natife.testapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseProjects {

    @SerializedName("projects")
    public final List<ProjectDto> projects;

    public ResponseProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}
