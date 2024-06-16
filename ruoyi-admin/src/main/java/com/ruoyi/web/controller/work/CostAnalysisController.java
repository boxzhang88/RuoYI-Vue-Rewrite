package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CheckUtil;
import com.ruoyi.common.utils.NumberRandomUtil;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.AnalysisSummary;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.domain.Project;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.AnalysisSummaryMapper;
import com.ruoyi.system.mapper.DepartmentMapper;
import com.ruoyi.system.mapper.ProjectMapper;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.service.IAnalysisSummaryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangC
 * date 2024/6/16 11:21
 * IntelliJ IDEA
 * 差异分析
 */
@RestController
@RequestMapping("/analysis")
public class CostAnalysisController extends BaseController {

    @Resource
    private AnalysisSummaryMapper analysisSummaryMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private IAnalysisSummaryService iAnalysisSummaryService;


    @RequestMapping("/list")
    public TableDataInfo list(@RequestBody AnalysisSummary analysisSummary) {
        startPage();
        LambdaQueryWrapper<AnalysisSummary> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(AnalysisSummary::getCreateTime);
        final List<AnalysisSummary> analysisSummaries = analysisSummaryMapper.selectList(lam);
        return getDataTable(analysisSummaries);
    }

    @RequestMapping("/make")
    public AjaxResult make(@RequestBody AnalysisSummary analysisSummary) {
        if (CheckUtil.isNulls(analysisSummary.getTaskId(), analysisSummary.getDepartmentId())) {
            throw new ServiceException("taskId,departmentId不能为空");
        }
        final Task task = taskMapper.selectById(analysisSummary.getTaskId());
        final Department department = departmentMapper.selectById(analysisSummary.getDepartmentId());
        List<AnalysisSummary> list = new ArrayList<>();
        final List<Project> projects = projectMapper.selectList(new LambdaQueryWrapper<>());
        for (Project project : projects) {
            AnalysisSummary back = new AnalysisSummary();
            BeanUtils.copyProperties(analysisSummary, back);
            back.setTaskName(task.getTaskName());
            back.setDepartmentName(department.getDeptName());
            back.setCreateBy(getUsername());
            back.setCreateTime(new Date());
            back.setCurrentCost(NumberRandomUtil.getRandomNumber());
            back.setExpectedCost(NumberRandomUtil.getRandomNumber());
            back.setProjectName(project.getProjectName());
            list.add(back);
        }
        return AjaxResult.success(iAnalysisSummaryService.saveBatch(list));
    }

    @RequestMapping("/del")
    public AjaxResult del(@RequestBody AnalysisSummary analysisSummary) {
        return AjaxResult.success(analysisSummaryMapper.delete(new LambdaQueryWrapper<>()));
    }

}
