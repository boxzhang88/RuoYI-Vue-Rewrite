package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 岗位工资对象 department_wage
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@Data
public class DepartmentWage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 科室代码
     */
    @Excel(name = "科室代码")
    private Long departmentCode;

    /**
     * 科室名称
     */
    @Excel(name = "科室名称")
    private String departmentName;

    /**
     * 成本项目代码
     */
    @Excel(name = "成本项目代码")
    private String costItemCode;

    /**
     * 成本项目名称
     */
    @Excel(name = "成本项目名称")
    private String costItemName;


    /**
     * 金额
     */
    @Excel(name = "金额")
    private BigDecimal amount;

    /**
     * 医生护士判别（医生或护士）
     */
    @Excel(name = "医生护士判别")
    private String doctorNurse;

    /**
     * 成本来源（如医疗、行政等）
     */
    @Excel(name = "成本来源")
    private String costSource;
    private Long taskId;
    private Long departmentId;

}
