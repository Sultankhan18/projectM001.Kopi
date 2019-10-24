package no.kristiania.projectManagement;

import java.util.Objects;

public class ProjectMember {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMember projectMember = (ProjectMember) o;
        return Objects.equals(name, projectMember.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
