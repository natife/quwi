package com.natife.testapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseProject {

    @SerializedName("project")
    public final ProjectDto project;

    public ResponseProject(ProjectDto project) {
        this.project = project;
    }
}
