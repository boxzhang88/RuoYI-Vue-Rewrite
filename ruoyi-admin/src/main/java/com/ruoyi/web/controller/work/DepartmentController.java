package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.mapper.DepartmentMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangC
 * date 2024/6/11 20:04
 * IntelliJ IDEA
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController extends BaseController {

    @Resource
    private DepartmentMapper departmentMapper;

    @RequestMapping("/list")
    public AjaxResult list(@RequestBody Department department) {
        LambdaQueryWrapper<Department> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(Department::getCreateTime);
        return AjaxResult.success(departmentMapper.selectList(lam));
    }

    @RequestMapping("/page")
    public TableDataInfo page(Department department) {
        startPage();
        LambdaQueryWrapper<Department> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(Department::getCreateTime);
        final List<Department> departments = departmentMapper.selectList(lam);
        return getDataTable(departments);
    }

    @RequestMapping("/add")
    public AjaxResult add(@RequestBody Department department) {
        return AjaxResult.success(departmentMapper.insert(department));
    }

    @RequestMapping("/delete")
    public AjaxResult delete(@RequestBody Department department) {
        return AjaxResult.success(departmentMapper.deleteById(department.getId()));
    }
}
