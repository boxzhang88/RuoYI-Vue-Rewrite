package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangC
 * date 2024/6/16 12:15
 * IntelliJ IDEA
 */
@Data
public class AnalysisSummary extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String taskName;
    private Long taskId;
    private Long departmentId;
    private String departmentName;
    private BigDecimal currentCost;
    private BigDecimal expectedCost;
    private String projectName;
}
