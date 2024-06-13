package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ZhangC
 * date 2024/6/9 20:54
 * IntelliJ IDEA
 */
@Data
public class Project {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String projectName;
    private String projectCode;
}
