package com.natife.testapp.model;

import com.google.gson.annotations.SerializedName;

public class ProjectDto {

    @SerializedName("id")
    public final int id;

    @SerializedName("name")
    public final String name;

    @SerializedName("logo_url")
    public final String icon;

    public ProjectDto(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }
}
