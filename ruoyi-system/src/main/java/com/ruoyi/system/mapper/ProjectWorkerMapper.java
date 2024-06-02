package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.ProjectWorker;

import java.util.List;

/**
 * 项目与人工信息Mapper接口
 *
 * @author ZhangC
 * @date 2024-06-01
 */
public interface ProjectWorkerMapper extends BaseMapper<ProjectWorker> {

    List<ProjectWorker> selectProjectWorkerList(ProjectWorker projectWorker);
}
