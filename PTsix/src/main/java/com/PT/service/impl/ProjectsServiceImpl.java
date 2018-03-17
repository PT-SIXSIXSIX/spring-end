package com.PT.service.impl;

import com.PT.dao.ProjectMapper;
import com.PT.entity.Project;
import com.PT.entity.ProjectExample;
import com.PT.service.ProjectsService;
import com.PT.tools.YkatConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired
    ProjectMapper projectMapper;
    @Override
    public Boolean addProjects(Project project, int userId) {
        try {
            ProjectExample example = new ProjectExample();
            example.createCriteria().andUserIdEqualTo(userId)
                    .andTypeEqualTo(project.getType()).andDescpEqualTo(project.getDescp());
            List list = projectMapper.selectByExample(example);
            if(list.size() > 0) {
                //存在该项目
                Project projectTemp = (Project) list.get(0);
                if(projectTemp.getStatus() == 1) {
                    projectTemp.setStatus(0);
                    projectMapper.updateByPrimaryKey(projectTemp);
                } else {
                    //已经存在，添加失败
                    return false;
                }
            } else {
                //不存在，插入项目
                project.setUserId(userId);
                project.setCreatedAt(new Date());
                project.setStatus(YkatConstant.PROJECTS_LIVE);
                projectMapper.insert(project);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean deleteProjects(List<Integer> ids, int userId) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andUserIdEqualTo(userId).
                andStatusEqualTo(YkatConstant.PROJECTS_LIVE).andIdIn(ids);
        Project project = new Project();
        project.setStatus(1);
        try {
            if(projectMapper.updateByExampleSelective(project, example) == 0)
                return false;
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Project> getByUserIdAndType(int userId, String type) {
        ProjectExample example = new ProjectExample();
        try {
            ProjectExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(YkatConstant.PROJECTS_LIVE).andUserIdEqualTo(userId);
            example.setOrderByClause("type");
            if(null == type || "".equals(type)) {
            } else {
                criteria.andTypeEqualTo(type);
            }
            List list = projectMapper.selectByExample(example);
            return list;
        } catch (Exception e) {
            throw e;
        }
    }
}
