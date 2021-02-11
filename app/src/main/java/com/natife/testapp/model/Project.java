package com.natife.testapp.model;

import java.util.Objects;

public class Project {

    public final int id;

    public final String icon;

    public final String name;

    public Project(int id, String name, String icon) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(icon, project.icon) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, icon, name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
