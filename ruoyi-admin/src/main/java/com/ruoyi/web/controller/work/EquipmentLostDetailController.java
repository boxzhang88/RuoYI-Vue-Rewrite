package com.ruoyi.web.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Department;
import com.ruoyi.system.domain.EquipmentLostDetail;
import com.ruoyi.system.domain.Task;
import com.ruoyi.system.mapper.DepartmentMapper;
import com.ruoyi.system.mapper.TaskMapper;
import com.ruoyi.system.service.IEquipmentLostDetailService;
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

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DepartmentMapper departmentMapper;

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
    public AjaxResult importData(MultipartFile file, Long deptId, Long taskId) throws Exception {
        ExcelUtil<EquipmentLostDetail> util = new ExcelUtil<>(EquipmentLostDetail.class);
        List<EquipmentLostDetail> projectWorkers = util.importExcel(file.getInputStream());
        final Task task = taskMapper.selectById(taskId);
        final Department department = departmentMapper.selectById(deptId);
        for (EquipmentLostDetail equipmentLostDetail : projectWorkers) {
            equipmentLostDetail.setDepartmentId(deptId);
            equipmentLostDetail.setTaskId(taskId);
            equipmentLostDetail.setTaskId(taskId);
            equipmentLostDetail.setTaskName(task.getTaskName());
            equipmentLostDetail.setDepartmentName(department.getDeptName());
        }
        equipmentLostDetailService.saveBatch(projectWorkers);
        return success(projectWorkers.size());
    }

    @RequestMapping("/delEquipDetail")
    public AjaxResult delEquipDetail(@RequestBody EquipmentLostDetail equipmentLostDetail) {
        return AjaxResult.success(equipmentLostDetailService.remove(new LambdaQueryWrapper<>()));
    }
}
