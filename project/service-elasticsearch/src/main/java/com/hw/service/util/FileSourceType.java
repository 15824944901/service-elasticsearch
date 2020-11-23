/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.hw.service.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据文件来源类型
 * Date: 2020/9/24
 * @author WX964987
 */
public class FileSourceType {
    private static final int MAP_SIZE = 6;

    private static Map<String, String> fileMap = new HashMap<>(MAP_SIZE);

    private FileSourceType() {

    }

    static {
        fileMap.put("dataset", "数据中心-数据集");
        fileMap.put("develop", "开发任务");
        fileMap.put("algorithm", "数据中心-算法");
        fileMap.put("model", "数据中心-模型");
        fileMap.put("training", "数据中心-训练任务");
        fileMap.put("skill", "数据中心-技能");
    }

    /**
     * 根据文件后缀匹配类型
     *
     * @param fileSource 文件类型
     * @return 对应类型
     */
    public static String retrieveFileSource(String fileSource) {
        if (fileMap.containsKey(fileSource)) {
            return fileMap.get(fileSource);
        }
        return "";
    }
}
