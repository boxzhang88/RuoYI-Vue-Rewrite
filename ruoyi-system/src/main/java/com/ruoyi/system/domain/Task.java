package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @author ZhangC
 * date 2024/6/8 19:18
 * IntelliJ IDEA
 */
@Data
public class Task extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long taskId;
    private String taskName;
}
