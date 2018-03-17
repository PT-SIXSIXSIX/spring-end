package com.PT.web;


import com.PT.entity.Project;
import com.PT.service.ProjectsService;
import com.PT.tools.BeanToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by yxhuang
 */
@Controller
@RequestMapping("/api/v1/projects/{userId}")
public class ProjectsController {
    @Autowired
    ProjectsService projectsService;


    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getProjects(@PathVariable("userId") int userId,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                 @RequestParam(value = "ipp", required = false, defaultValue = "5") int ipp,
                                                 @RequestParam(value = "q", required = false, defaultValue = "") String q,
                                                 HttpServletResponse response) {
        response.setStatus(200);
        Map result = new HashMap<String, Object>();
        try {
            List list = projectsService.getByUserIdAndType(userId, q);
            result.put("projects", list);
            result.put("total", list.size());
        } catch (Exception e) {
            response.setStatus(400);
            result.put("statusCode", 1);
            result.put("errorDesc", e.getMessage());
        }
        return result;
    }
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addProject(@RequestBody Map<String, Object> out,
                                   @PathVariable("userId") int userId,
                                    HttpServletResponse response) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        Project project = (Project) BeanToMapUtil.convertMap(Project.class, out);
        response.setStatus(200);
        Map result = new HashMap<String, Object>();
        try {
            if(projectsService.addProjects(project, userId) == false) {
                response.setStatus(400);
                result.put("statusCode", 1);
                result.put("errorDesc", "已经存在该项目，添加失败");
            }
            return result;
        } catch (Exception e) {

            response.setStatus(400);
            result.put("statusCode", 1);
            result.put("errorDesc", e.getMessage());
            return result;
        }
    }
    @RequestMapping(value = "/deleteProjects", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteProjects(@RequestBody Map<String, Object> out,
                                   @PathVariable("userId") int userId,
                                   HttpServletResponse response) {
        List <Integer> ids = (List<Integer>) out.get("projectsIds");
        response.setStatus(200);
        Map result = new HashMap<String, Object>();
        try {
            if(projectsService.deleteProjects(ids, userId) == false) {
                response.setStatus(400);
                result.put("statusCode", 1);
                result.put("errorDesc", "不存在该项目，删除失败");
            }
            return result;
        } catch (Exception e) {
            response.setStatus(400);
            result.put("statusCode", 1);
            result.put("errorDesc", e.getMessage());
            return result;
        }
    }
}
