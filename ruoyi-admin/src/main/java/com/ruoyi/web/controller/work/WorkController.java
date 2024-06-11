package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.NumberRandomUtil;
import com.ruoyi.system.domain.CostSummary;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.domain.Project;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.CostSummaryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangC
 * date 2024/6/9 20:52
 * IntelliJ IDEA
 */
@RestController
@RequestMapping("/work")
public class WorkController extends BaseController {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private CostSummaryMapper costSummaryMapper;
    @Resource
    private ProjectWorkerMapper projectWorkerMapper;
    @Resource
    private CostSummaryService costSummaryService;
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 生成汇总
     */
    @RequestMapping("/makeReportSummary")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult reportSummary(@RequestBody CostSummary frontCostSummary) {
        final Task task = taskMapper.selectById(frontCostSummary.getTaskId());
        final List<Project> projects = projectMapper.selectList(new LambdaQueryWrapper<>());
        final Department department = departmentMapper.selectById(frontCostSummary.getDepartmentId());
        List<CostSummary> costSummaryList = new ArrayList<>();
        for (Project project : projects) {
            CostSummary costSummary = new CostSummary();
            costSummary.setDepartmentId(frontCostSummary.getDepartmentId());
            costSummary.setDepartmentName(department.getDeptName());
            costSummary.setProjectName(project.getProjectName());
            costSummary.setTaskId(frontCostSummary.getTaskId());
            costSummary.setTaskName(task.getTaskName());
            BigDecimal sum = new BigDecimal("0.000");
            //直接人工
            BigDecimal work = NumberRandomUtil.getRandomNumber();
            costSummary.setDirectLabor(work);
            // 卫生耗材费用
            BigDecimal medicalConsumables = NumberRandomUtil.getRandomNumber();
            sum = sum.add(medicalConsumables);
            costSummary.setMedicalConsumables(medicalConsumables);
            // 专用设备费用
            BigDecimal specialEquipment = NumberRandomUtil.getRandomNumber();
            sum = sum.add(specialEquipment);
            costSummary.setSpecialEquipment(specialEquipment);

            // 其他费用分配
            BigDecimal otherCostAllocation = NumberRandomUtil.getRandomNumber();
            sum = sum.add(otherCostAllocation);
            costSummary.setOtherCostAllocation(otherCostAllocation);

            // 间接折旧分配
            BigDecimal indirectDepreciationAllocation = NumberRandomUtil.getRandomNumber();
            sum = sum.add(indirectDepreciationAllocation);
            costSummary.setIndirectDepreciationAllocation(indirectDepreciationAllocation);

            // 医辅费用分摊
            BigDecimal auxiliaryMedicalExpenses = NumberRandomUtil.getRandomNumber();
            sum = sum.add(auxiliaryMedicalExpenses);
            costSummary.setAuxiliaryMedicalExpenses(auxiliaryMedicalExpenses);
            // 行政管理费用
            BigDecimal administrativeExpenses = NumberRandomUtil.getRandomNumber();
            sum = sum.add(administrativeExpenses);
            costSummary.setAdministrativeExpenses(administrativeExpenses);

            //总标准成本
            costSummary.setTotalStandardCost(sum);
            costSummary.setCreateBy(getUsername());
            costSummary.setCreateTime(new Date());
            costSummaryList.add(costSummary);
        }
        costSummaryService.saveBatch(costSummaryList);
        return AjaxResult.success();
    }

    /**
     * 分页
     *
     * @return
     */
    @RequestMapping("/reportSummaryList")
    public TableDataInfo reportSummaryList(@RequestBody CostSummary costSummary) {
        startPage();
        LambdaQueryWrapper<CostSummary> lam = new LambdaQueryWrapper();
        lam.eq(costSummary.getTaskId() != null, CostSummary::getTaskId, costSummary.getTaskId());
        lam.orderByDesc(CostSummary::getCreateTime);
        final List<CostSummary> costSummaryList = costSummaryMapper.selectList(lam);
        return getDataTable(costSummaryList);
    }

    @RequestMapping("/delReportSummary")
    public AjaxResult delReportSummary(@RequestBody CostSummary costSummary) {
        return AjaxResult.success(costSummaryService.remove(new LambdaQueryWrapper<>()));
    }
}
