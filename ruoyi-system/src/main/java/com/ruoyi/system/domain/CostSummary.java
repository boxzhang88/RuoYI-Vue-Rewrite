package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangC
 * date 2024/6/9 21:36
 * IntelliJ IDEA
 */
@Data
public class CostSummary extends BaseEntity {
    private Long id; // 唯一标识
    private String projectName; // 项目名称
    private BigDecimal directLabor; // 直接人工费用
    private BigDecimal medicalConsumables; // 卫生耗材费用
    private BigDecimal specialEquipment; // 专用设备费用
    private BigDecimal otherCostAllocation; // 其他费用分配
    private BigDecimal indirectDepreciationAllocation; // 间接折旧分配
    private BigDecimal auxiliaryMedicalExpenses; // 医辅费用分摊
    private BigDecimal administrativeExpenses; // 行政管理费用
    private BigDecimal totalStandardCost; // 总标准成本
    private Long taskId;
    private String taskName;
    private Long departmentId;
    private String departmentName;
}
