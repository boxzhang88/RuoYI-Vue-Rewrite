package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.ProjectWorker;
import com.ruoyi.system.mapper.ProjectWorkerMapper;
import com.ruoyi.system.service.IProjectWorkerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目与人工信息Service业务层处理
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@Service
public class ProjectWorkerServiceImpl extends ServiceImpl<ProjectWorkerMapper, ProjectWorker> implements IProjectWorkerService {
    @Resource
    private ProjectWorkerMapper projectWorkerMapper;

    @Override
    public List<ProjectWorker> getProjectWorkerList(ProjectWorker projectWorker) {
        return projectWorkerMapper.selectProjectWorkerList(projectWorker);
    }
}
