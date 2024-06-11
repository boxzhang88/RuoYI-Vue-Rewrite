package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 项目与人工信息对象 project_worker
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@Data
public class ProjectWorker extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 项目主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 标准护士人数
     */
    @Excel(name = "标准护士人数")
    private BigDecimal standardNurseCount;

    /**
     * 标准医生人数
     */
    @Excel(name = "标准医生人数")
    private BigDecimal standardDoctorCount;

    /**
     * 标准工时（单位：小时）
     */
    @Excel(name = "标准工时")
    private BigDecimal standardWorkingHours;

    /**
     * 标准医生时间消耗系数
     */
    @Excel(name = "标准医生时间消耗系数")
    private BigDecimal standardDoctorTimeConsumptionCoefficient;

    /**
     * 标准护士时间消耗系数
     */
    @Excel(name = "标准护士时间消耗系数")
    private BigDecimal standardNurseTimeConsumptionCoefficient;

    /**
     * 低值易耗品系数
     */
    @Excel(name = "低值易耗品系数")
    private Long lowValueConsumableCoefficient;

    /**
     * 关联任务id
     */
    private Long taskId;
    private String taskName;


    /**
     * 关联部门id
     */
    private Long departmentId;
    private String departmentName;

}
