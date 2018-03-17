package com.PT.service;

import com.PT.entity.Project;

import java.util.List;

public interface ProjectsService {
    public Boolean addProjects(Project project, int userId);
    public Boolean deleteProjects(List<Integer> ids, int userId);
    public List<Project> getByUserIdAndType(int userId, String q);
}
