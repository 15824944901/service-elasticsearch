/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.hw.service.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据文件类型
 * Date: 2020/9/24
 * @author WX964987
 */
@Service
public class FileDataType {
    private static final int MAP_SIZE = 128;

    private static Map<String, String> fileMap = new HashMap<>(MAP_SIZE);

    public FileDataType() {

    }

    static {
        fileMap.put("jpg", "图片");
        fileMap.put("png", "图片");
        fileMap.put("jpeg", "图片");
        fileMap.put("tif", "图片");
        fileMap.put("gif", "图片");
        fileMap.put("bmp", "图片");
        fileMap.put("raw", "图片");
        fileMap.put("tga", "图片");
        fileMap.put("svg", "图片");
        fileMap.put("pcd", "图片");
        fileMap.put("psd", "图片");
        fileMap.put("xt", "文本");
        fileMap.put("doc", "文本");
        fileMap.put("docx", "文本");
        fileMap.put("csv", "文本,结构化数据");
        fileMap.put("xls", "文本,结构化数据");
        fileMap.put("xlsx", "文本,结构化数据");
        fileMap.put("pdf", "文本");
        fileMap.put("wps", "文本");
        fileMap.put("ppt", "文本");
        fileMap.put("pptx", "文本");
        fileMap.put("tex", "文本");
        fileMap.put("ltx", "文本");
        fileMap.put("rtf", "文本");
        fileMap.put("html", "文本");
        fileMap.put("chm", "文本");
        fileMap.put("htm", "文本");
        fileMap.put("shtml", "文本");
        fileMap.put("xhtml", "文本");
        fileMap.put("wma", "音频");
        fileMap.put("midi", "音频");
        fileMap.put("msf", "音频");
        fileMap.put("mp2", "音频");
        fileMap.put("mp3", "音频");
        fileMap.put("ra", "音频");
        fileMap.put("speex", "音频");
        fileMap.put("aac", "音频");
        fileMap.put("mpc", "音频");
        fileMap.put("m4a", "音频");
        fileMap.put("m4p", "音频");
        fileMap.put("Vorbis", "音频");
        fileMap.put("tta", "音频");
        fileMap.put("tak", "音频");
        fileMap.put("wv", "音频");
        fileMap.put("flac", "音频");
        fileMap.put("aaf", "视频");
        fileMap.put("ani", "视频");
        fileMap.put("asf", "视频");
        fileMap.put("dat", "视频");
        fileMap.put("swf", "视频");
        fileMap.put("mng", "视频");
        fileMap.put("mov", "视频");
        fileMap.put("qt", "视频");
        fileMap.put("qtx", "视频");
        fileMap.put("mxf", "视频");
        fileMap.put("ogm", "视频");
        fileMap.put("avi", "视频");
        fileMap.put("mpeg", "视频");
        fileMap.put("mp4", "视频");
        fileMap.put("mkv", "视频");
        fileMap.put("rmvb", "视频");
        fileMap.put("rm", "视频");
        fileMap.put("rar", "压缩包");
        fileMap.put("zip", "压缩包");
        fileMap.put("tar", "压缩包");
        fileMap.put("gz", "压缩包");
        fileMap.put("bz2", "压缩包");
        fileMap.put("gzip", "压缩包");
        fileMap.put("py", "代码");
        fileMap.put("sh", "代码");
        fileMap.put("ipynb", "代码");
        fileMap.put("index", "代码");
        fileMap.put("meta", "代码");
        fileMap.put("cfg", "配置文件");
        fileMap.put("config", "配置文件");
        fileMap.put("txt", "文本");
        fileMap.put("pb", "模型文件");
        fileMap.put("pbtxt", "模型文件");
        fileMap.put("onnx", "模型文件");
        fileMap.put("keras", "模型文件");
        fileMap.put("om", "模型文件");
        fileMap.put("ckpt", "模型文件");
        fileMap.put("h5", "模型文件");
        fileMap.put("pkl", "模型文件");
        fileMap.put("t7", "模型文件");
        fileMap.put("mlmodel", "模型文件");
        fileMap.put("model", "模型文件");
        fileMap.put("json", "文本,结构化数据");
        fileMap.put("tflite", "模型文件");
        fileMap.put("caffemodel", "模型文件");
        fileMap.put("prototxt", "模型文件");
        fileMap.put("pt", "模型文件");
        fileMap.put("pth", "模型文件");
        fileMap.put("cntk", "模型文件");
        fileMap.put("np", "模型文件");
        fileMap.put("npz", "模型文件");
    }

    /**
     * 根据文件后缀匹配类型
     *
     * @param dataType 数据类型
     * @return 对应类型
     */
    @Async
    public String retrieveFileType(String dataType) {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (fileMap.containsKey(dataType)) {
            return fileMap.get(dataType);
        } else {
            StringBuffer sb = new StringBuffer(dataType);
            if (dataType.startsWith("ckpt.data") || dataType.startsWith("data")) {
                return "代码";
            } else if ("checkpoint".equals(dataType) || "meta".equals(dataType) || "index".equals(dataType)) {
                return "代码";
            } else {
                return "未知";
            }
        }
    }
}
