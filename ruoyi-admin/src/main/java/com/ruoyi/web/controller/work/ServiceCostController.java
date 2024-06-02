package com.ruoyi.web.controller.work;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ServiceCost;
import com.ruoyi.system.service.IServiceCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 查询服务均次成本列表
     */
    @PostMapping("/list")
    public TableDataInfo list(ServiceCost serviceCost) {
        startPage();
        List<ServiceCost> list = serviceCostService.list();
        return getDataTable(list);
    }


    @PostMapping("/importData")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importData(MultipartFile file, Long departmentId, Long taskId) throws Exception {
        ExcelUtil<ServiceCost> util = new ExcelUtil<>(ServiceCost.class);
        List<ServiceCost> projectWorkers = util.importExcel(file.getInputStream());
        for (ServiceCost serviceCost : projectWorkers) {
            serviceCost.setDepartmentId(departmentId);
            serviceCost.setTaskId(taskId);
        }
        serviceCostService.saveBatch(projectWorkers);
        return success(projectWorkers.size());
    }
}
