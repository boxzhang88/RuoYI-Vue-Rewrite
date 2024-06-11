package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @author ZhangC
 * date 2024/6/11 20:06
 * IntelliJ IDEA
 */
@Data
public class Department extends BaseEntity {
    @TableId
    private Long id;
    private String deptName;
}
