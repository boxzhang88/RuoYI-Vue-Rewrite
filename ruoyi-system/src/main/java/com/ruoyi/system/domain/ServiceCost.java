package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务均次成本对象 service_cost
 *
 * @author ZhangC
 * @date 2024-06-01
 */
@Data
public class ServiceCost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 物价编码
     */
    @Excel(name = "物价编码")
    private String priceCode;

    /**
     * 项目代码
     */
    @Excel(name = "项目代码")
    private String projectCode;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private String projectName;

    /**
     * 人员经费
     */
    @Excel(name = "人员经费")
    private BigDecimal personnelExpenses;

    /**
     * 卫生材料费
     */
    @Excel(name = "卫生材料费")
    private BigDecimal healthMaterialExpenses;

    /**
     * 药品费
     */
    @Excel(name = "药品费")
    private BigDecimal drugExpenses;

    /**
     * 固定资产折旧费
     */
    @Excel(name = "固定资产折旧费")
    private BigDecimal fixedAssetDepreciation;

    /**
     * 无形资产摊销费
     */
    @Excel(name = "无形资产摊销费")
    private BigDecimal intangibleAssetAmortization;

    /**
     * 提取医疗风险基金
     */
    @Excel(name = "提取医疗风险基金")
    private BigDecimal medicalRiskFund;

    /**
     * 其他费用
     */
    @Excel(name = "其他费用")
    private BigDecimal otherExpenses;

    /**
     * 财政经费
     */
    @Excel(name = "财政经费")
    private BigDecimal financialFunding;

    /**
     * 非同级财政经费
     */
    @Excel(name = "非同级财政经费")
    private BigDecimal nonSameLevelFinancialFunding;

    /**
     * 科教项目
     */
    @Excel(name = "科教项目")
    private BigDecimal scientificEducationProject;

    /**
     * 其他科教费用
     */
    @Excel(name = "其他科教费用")
    private BigDecimal otherScientificEducationExpenses;

    /**
     * 辅助服务成本
     */
    @Excel(name = "辅助服务成本")
    private BigDecimal auxiliaryServiceCost;

    /**
     * 管理费用
     */
    @Excel(name = "管理费用")
    private BigDecimal managementExpenses;

    /**
     * 医技成本
     */
    @Excel(name = "医技成本")
    private BigDecimal medicalTechnologyCost;

    /**
     * 药品成本
     */
    @Excel(name = "药品成本")
    private BigDecimal drugCost;

    /**
     * 单位成本
     */
    @Excel(name = "单位成本")
    private BigDecimal unitCost;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /**
     * 例次
     */
    @Excel(name = "例次")
    private Long exampleCount;

    /**
     * 结余
     */
    @Excel(name = "结余")
    private BigDecimal balance;

    /**
     * 关联任务id
     */
    private Long taskId;

    /**
     * 关联科室部门id
     */
    private Long departmentId;

}
