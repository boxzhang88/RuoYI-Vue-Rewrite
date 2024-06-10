package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ServiceCost;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.service.IServiceCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务均次成本Controller
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/system/serviceCost")
public class ServiceCostController extends BaseController {
    @Autowired
    private IServiceCostService serviceCostService;

    @Resource
    private TaskMapper taskMapper;

    /**
     * 查询服务均次成本列表
     */
    @PostMapping("/list")
    public TableDataInfo list(ServiceCost serviceCost) {
        startPage();
        LambdaQueryWrapper<ServiceCost> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(ServiceCost::getCreateTime);
        List<ServiceCost> list = serviceCostService.list(lam);
        return getDataTable(list);
    }


    @PostMapping("/importData")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importData(MultipartFile file, Long departmentId, Long taskId) throws Exception {
        ExcelUtil<ServiceCost> util = new ExcelUtil<>(ServiceCost.class);
        List<ServiceCost> projectWorkers = util.importExcel(file.getInputStream());
        final Task task = taskMapper.selectById(taskId);
        for (ServiceCost serviceCost : projectWorkers) {
            serviceCost.setDepartmentId(departmentId);
            serviceCost.setTaskId(taskId);
            serviceCost.setTaskId(taskId);
            serviceCost.setTaskName(task.getTaskName());
        }
        serviceCostService.saveBatch(projectWorkers);
        return success(projectWorkers.size());
    }
}
