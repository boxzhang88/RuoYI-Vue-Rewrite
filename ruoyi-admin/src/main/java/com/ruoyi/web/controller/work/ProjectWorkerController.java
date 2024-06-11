package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.domain.ProjectWorker;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.DepartmentMapper;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.service.IProjectWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目与人工信息Controller
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/system/worker")
public class ProjectWorkerController extends BaseController {
    @Autowired
    private IProjectWorkerService projectWorkerService;

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 查询项目与人工信息列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ProjectWorker projectWorker) {
        startPage();
        List<ProjectWorker> list = projectWorkerService.getProjectWorkerList(projectWorker);
        return getDataTable(list);
    }

    @PostMapping("/importData")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importData(MultipartFile file, Long deptId, Long taskId) throws Exception {
        ExcelUtil<ProjectWorker> util = new ExcelUtil<>(ProjectWorker.class);
        List<ProjectWorker> projectWorkers = util.importExcel(file.getInputStream());
        final Task task = taskMapper.selectById(taskId);
        final Department department = departmentMapper.selectById(deptId);
        for (ProjectWorker projectWorker : projectWorkers) {
            projectWorker.setDepartmentId(deptId);
            projectWorker.setTaskId(taskId);
            projectWorker.setTaskName(task.getTaskName());
            projectWorker.setDepartmentName(department.getDeptName());
        }
        projectWorkerService.saveBatch(projectWorkers);
        return success(projectWorkers.size());
    }

    @PostMapping("/delProjectWorker")
    public AjaxResult delProjectWorker(ProjectWorker projectWorker) {
        final boolean remove = projectWorkerService.remove(new LambdaQueryWrapper<>());
        return AjaxResult.success(remove);
    }
}
