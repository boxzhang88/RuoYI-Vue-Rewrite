package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangC
 * date 2024/6/13 21:58
 * IntelliJ IDEA
 * id
 * project_name
 * create_by
 * create_time
 * update_by
 * update_time
 * remark
 * task_id
 * task_name
 * department_id
 * department_name
 * type
 */
@Data
public class Money extends BaseEntity {
    private Long id;
    private String projectName;
    private String taskId;
    private String taskName;
    private Long departmentId;
    private String departmentName;
    private Integer type;

    private BigDecimal cost;
}
