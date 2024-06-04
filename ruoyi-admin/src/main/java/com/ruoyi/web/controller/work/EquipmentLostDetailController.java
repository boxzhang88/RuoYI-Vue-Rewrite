package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.EquipmentLostDetail;
import com.ruoyi.system.service.IEquipmentLostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 设备折旧明细Controller
 *
 * @author ZhangC
 * @date 2024-06-02
 */
@RestController
@RequestMapping("/system/equip")
public class EquipmentLostDetailController extends BaseController {
    @Autowired
    private IEquipmentLostDetailService equipmentLostDetailService;

    /**
     * 查询设备折旧明细列表
     */
    @PostMapping("/list")
    public TableDataInfo list(EquipmentLostDetail equipmentLostDetail) {

        startPage();
        LambdaQueryWrapper<EquipmentLostDetail> lam = new LambdaQueryWrapper<>();
        lam.orderByDesc(EquipmentLostDetail::getCreateTime);
        List<EquipmentLostDetail> list = equipmentLostDetailService.list(lam);
        return getDataTable(list);
    }

    @PostMapping("/importData")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult importData(MultipartFile file, Long departmentId, Long taskId) throws Exception {
        ExcelUtil<EquipmentLostDetail> util = new ExcelUtil<>(EquipmentLostDetail.class);
        List<EquipmentLostDetail> projectWorkers = util.importExcel(file.getInputStream());
        for (EquipmentLostDetail equipmentLostDetail : projectWorkers) {
            equipmentLostDetail.setDepartmentId(departmentId);
            equipmentLostDetail.setTaskId(taskId);
        }
        equipmentLostDetailService.saveBatch(projectWorkers);
        return success(projectWorkers.size());
    }

}
