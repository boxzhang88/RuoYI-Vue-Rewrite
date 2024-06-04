package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.DepartmentCost;
import com.ruoyi.system.service.IDepartmentCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 医院业务科室全成本汇总Controller
 *
 * @author ZhangC
 * @date 2024-06-02
 */
@RestController
@RequestMapping("/system/deptCost")
public class DepartmentCostController extends BaseController {
    @Autowired
    private IDepartmentCostService departmentCostService;

    /**
     * 查询医院业务科室全成本汇总列表
     */
    @PostMapping("/list")
    public TableDataInfo list(DepartmentCost departmentCost) {
        startPage();
        LambdaQueryWrapper<DepartmentCost> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(DepartmentCost::getCreateTime);
        List<DepartmentCost> list = departmentCostService.list(lam);
        return getDataTable(list);
    }

    @PostMapping("/importData")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importData(MultipartFile file, Long departmentId, Long taskId) throws Exception {
        ExcelUtil<DepartmentCost> util = new ExcelUtil<>(DepartmentCost.class);
        List<DepartmentCost> departmentWages = util.importExcel(file.getInputStream());
        for (DepartmentCost departmentCost : departmentWages) {
            departmentCost.setDepartmentId(departmentId);
            departmentCost.setTaskId(taskId);
        }
        departmentCostService.saveBatch(departmentWages);
        return success(departmentWages.size());
    }

}
