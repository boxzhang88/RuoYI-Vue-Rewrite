package com.ruoyi.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CheckUtil;
import com.ruoyi.common.utils.NumberRandomUtil;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.domain.Money;
import com.ruoyi.system.domain.Project;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.DepartmentMapper;
import com.ruoyi.system.mapper.MoneyMapper;
import com.ruoyi.system.mapper.ProjectMapper;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.service.IMoneyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangC
 * date 2024/6/13 21:57
 * IntelliJ IDEA
 */
@RestController
@RequestMapping("/money")
public class MoneyController extends BaseController {

    @Resource
    private MoneyMapper moneyMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private IMoneyService moneyService;
    @Resource
    private DepartmentMapper departmentMapper;

    @RequestMapping("/list")
    public TableDataInfo list(@RequestBody Money money) {
        startPage();
        final LambdaQueryWrapper<Money> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Money::getType, money.getType());
        final List<Money> monies = moneyMapper.selectList(lambdaQueryWrapper);
        return getDataTable(monies);
    }

    @RequestMapping("/del")
    public AjaxResult del(@RequestBody Money money) {
        if (CheckUtil.isNulls(money.getType())) {
            throw new ServiceException("type不能为空");
        }
        final LambdaQueryWrapper<Money> lam = new LambdaQueryWrapper<>();
        lam.eq(Money::getType, money.getType());
        return AjaxResult.success(moneyMapper.delete(lam));
    }

    @RequestMapping("/make")
    public AjaxResult make(@RequestBody Money money) {
        if (CheckUtil.isNulls(money.getType(), money.getTaskId(), money.getDepartmentId())) {
            throw new ServiceException("type,taskId,deptId不能为空");
        }
        Task task = taskMapper.selectById(money.getTaskId());
        Department department = departmentMapper.selectById(money.getDepartmentId());
        final List<Project> projects = projectMapper.selectList(new LambdaQueryWrapper<>());
        List<Money> moneyList = new ArrayList<>();
        for (Project project : projects) {
            Money back = new Money();
            BeanUtils.copyProperties(money, back);
            back.setCost(NumberRandomUtil.getRandomNumber());
            back.setCreateTime(new Date());
            back.setCreateBy(getUsername());
            back.setTaskName(task.getTaskName());
            back.setProjectName(project.getProjectName());
            back.setDepartmentName(department.getDeptName());
            moneyList.add(back);
        }
        return AjaxResult.success(moneyService.saveBatch(moneyList));
    }
}
