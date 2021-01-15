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
 * Date: 2020/9/8
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
public class Employee {

    private String id;

    private String firstName;

    private String lastName;

    private String age;

    private String[] interests;

    private String parentVersion;

    private String version;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctreaTime;

    /**
     * 根据父版本衍生出新版本
     *
     * @param maxVersion 最大版本号
     * @return String
     */
    public String generateVersion(String maxVersion) {
        if (!"".equals(maxVersion)) {
            int versionNum = Integer.valueOf(maxVersion.replace("V", "")) + 1;
            return "V" + String.format("%04d", versionNum);
        }
        return "V0001";
    }
}
