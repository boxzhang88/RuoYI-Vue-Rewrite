package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 医院业务科室全成本汇总对象 department_cost
 *
 * @author ZhangC
 * @date 2024-06-02
 */
@Data
public class DepartmentCost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    @Excel(name = "项目")
    private String projectName;

    /**
     * 人员经费（保留到小数点后三位）
     */
    @Excel(name = "人员经费")
    private BigDecimal personnelExpenses;

    /**
     * 卫生材料费（保留到小数点后三位）
     */
    @Excel(name = "卫生材料费")
    private BigDecimal medicalMaterialExpenses;

    /**
     * 药品费（保留到小数点后三位）
     */
    @Excel(name = "药品费")
    private BigDecimal medicineExpenses;

    /**
     * 固定资产折旧费（保留到小数点后三位）
     */
    @Excel(name = "固定资产折旧费")
    private BigDecimal fixedAssetsDepreciation;

    /**
     * 无形资产摊销费（保留到小数点后三位）
     */
    @Excel(name = "无形资产摊销费")
    private BigDecimal intangibleAssetsAmortization;

    /**
     * 其他运行费用（保留到小数点后三位）
     */
    @Excel(name = "其他运行费用")
    private BigDecimal otherOperatingExpenses;

    /**
     * 辅助服务成本（保留到小数点后三位）
     */
    @Excel(name = "辅助服务成本")
    private BigDecimal auxiliaryServiceCosts;

    /**
     * 管理费用（保留到小数点后三位）
     */
    @Excel(name = "管理费用")
    private BigDecimal administrativeExpenses;

    /**
     * 总计费用（保留到小数点后三位，可根据需要计算）
     */
    @Excel(name = "总计费用")
    private BigDecimal totalExpenses;

    private Long taskId;
    private Long departmentId;
}
