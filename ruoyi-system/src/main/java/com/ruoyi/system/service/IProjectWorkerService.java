package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.ProjectWorker;

import java.util.List;

/**
 * 项目与人工信息Service接口
 *
 * @author ZhangC
 * @date 2024-06-01
 */
public interface IProjectWorkerService extends IService<ProjectWorker> {

    List<ProjectWorker> getProjectWorkerList(ProjectWorker projectWorker);
}
