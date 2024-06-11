package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.domain.DepartmentWage;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.DepartmentMapper;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.service.IDepartmentWageService;
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
 * 岗位工资Controller
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/system/wage")
public class DepartmentWageController extends BaseController {
    @Autowired
    private IDepartmentWageService departmentWageService;

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 查询岗位工资列表
     */
    @PostMapping("/list")
    public TableDataInfo list(DepartmentWage departmentWage) {
        startPage();
        LambdaQueryWrapper<DepartmentWage> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(DepartmentWage::getCreateTime);
        List<DepartmentWage> list = departmentWageService.list(lam);
        return getDataTable(list);
    }

    @PostMapping("/importData")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importData(MultipartFile file, Long deptId, Long taskId) throws Exception {
        ExcelUtil<DepartmentWage> util = new ExcelUtil<>(DepartmentWage.class);
        List<DepartmentWage> departmentWages = util.importExcel(file.getInputStream());
        final Task task = taskMapper.selectById(taskId);
        final Department department = departmentMapper.selectById(deptId);
        for (DepartmentWage departmentWage : departmentWages) {
            departmentWage.setDepartmentId(deptId);
            departmentWage.setTaskId(taskId);
            departmentWage.setTaskId(taskId);
            departmentWage.setDepartmentName(department.getDeptName());
            departmentWage.setTaskName(task.getTaskName());
        }
        departmentWageService.saveBatch(departmentWages);
        return success(departmentWages.size());
    }

    @RequestMapping("/delDepartmentWage")
    public AjaxResult delDepartmentWage(@RequestBody DepartmentWage departmentWage) {
        return AjaxResult.success(departmentWageService.remove(new LambdaQueryWrapper<>()));
    }
}
