package com.PT.test;

import com.PT.entity.Project;
import com.PT.service.ProjectsService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsServiceTest extends BaseTest {
    @Autowired
    ProjectsService projectsService;

    @Test
    public void addProjects() {
        Project project = new Project();
        Integer userId = 41;
        project.setType("组装新车");
        project.setDescp("新车改造加强");
        project.setPrice(1000);
        System.out.println(projectsService.addProjects(project, userId));
    }

    @Test
    public void deleteProjects() {
        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        projectsService.deleteProjects(list);
    }

    @Test
    public void getByUserIdAndType() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Integer userId = 41;
        String q1 = "";
        String q2 = "";
        List list = projectsService.getByUserIdAndType(userId, q1);
        OutputMessage.outputList(list);
    }
}
