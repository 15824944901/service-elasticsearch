package com.hw.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Date: 2020/8/31 9:59
 */
// 有参构造
@AllArgsConstructor
// 无参构造
@NoArgsConstructor
// 构建
@Builder
// get方法
@Getter
// set方法
@Setter
@Data
public class DataSource {

    /**
     * 单数据ID
     */
    private String id;

    /**
     * 数据集名称
     */
    private String name;

    /**
     * 版本号
     */
    private String version;

    /**
     * 文件父目录ID
     */
    private String parentId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 更新时间（包括创建时间）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 待扩展
     */
    private String firstName;

    /**
     * 待扩展
     */
    private String lastName;

}
