package com.hw.service.controller;

import com.hw.service.entity.Student;
import com.hw.service.util.FileDownloadUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理
 * Date: 2020/10/29
 * @author WX964987
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @SneakyThrows
    @PostMapping("/dl")
    public void fileDownLoad(HttpServletResponse response, String fileName, String filePath) {
        System.out.println("DownLoad :" +  filePath + fileName);
        FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();
        fileDownloadUtil.downloadLocal(response);
    }

    public void filell(HttpServletResponse response) {
        List<Student> studentList = new ArrayList<>();

        Student student = new Student();
        student.setId(1L);
        student.setName("十");
        student.setLevel(1);
        student.setParentId(1L);
        studentList.add(student);
    }
}
