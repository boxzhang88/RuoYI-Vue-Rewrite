package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.TaskMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangC
 * date 2024/6/8 19:26
 * IntelliJ IDEA
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Resource
    private TaskMapper taskMapper;

    @RequestMapping("/add")
    public AjaxResult addTask(@RequestBody Task task) {
        return AjaxResult.success(taskMapper.insert(task));
    }

    @RequestMapping("/list")
    public TableDataInfo list(Task task) {
        startPage();
        final LambdaQueryWrapper<Task> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Task::getCreateTime);
        final List<Task> tasks = taskMapper.selectList(lambdaQueryWrapper);
        return getDataTable(tasks);
    }

    @RequestMapping("/menu")
    public AjaxResult menu(Task task) {
        final LambdaQueryWrapper<Task> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Task::getCreateTime);
        final List<Task> tasks = taskMapper.selectList(lambdaQueryWrapper);
        return AjaxResult.success(tasks);
    }

    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody Task task) {
        final int i = taskMapper.deleteById(task.getTaskId());
        return AjaxResult.success(i);
    }

    @PostMapping("/listMenu")
    public AjaxResult listMenu(Task task) {
        LambdaQueryWrapper<Task> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(Task::getCreateTime);
        List<Task> list = taskMapper.selectList(lam);
        return AjaxResult.success(list);
    }
}
