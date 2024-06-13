package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 设备折旧明细对象 equipment_lost_detail
 *
 * @author ZhangC
 * @date 2024-06-02
 */
@Data
public class EquipmentLostDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识符，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 移动护理推车
     */
    @Excel(name = "移动护理推车")
    private Integer mobileNursingCart;

    /**
     * 智能输液监控器
     */
    @Excel(name = "智能输液监控器")
    private Integer smartInfusionMonitor;

    /**
     * 智能输液监控系统
     */
    @Excel(name = "智能输液监控系统")
    private Integer smartInfusionSystem;

    /**
     * 血糖仪
     */
    @Excel(name = "血糖仪")
    private Integer bloodGlucoseMeter;

    /**
     * 生命体征检测仪
     */
    @Excel(name = "生命体征检测仪")
    private Integer vitalSignsMonitor;

    /**
     * 微量注射泵
     */
    @Excel(name = "微量注射泵")
    private Integer microInjectionPump;

    /**
     * 床单位消毒机
     */
    @Excel(name = "床单位消毒机")
    private Integer bedUnitDisinfector;

    /**
     * 化疗注药泵
     */
    @Excel(name = "化疗注药泵")
    private Integer chemotherapyPump;

    /**
     * 空气消毒机
     */
    @Excel(name = "空气消毒机")
    private Integer airDisinfector;

    /**
     * 专用设备分摊金额
     */
    @Excel(name = "专用设备分摊金额")
    private BigDecimal specialEquipmentCost;

    /**
     * 小时单价
     */
    @Excel(name = "小时单价")
    private BigDecimal hourlyRate;

    private Long taskId;
    private String taskName;

    private Long departmentId;
    private String departmentName;
}
