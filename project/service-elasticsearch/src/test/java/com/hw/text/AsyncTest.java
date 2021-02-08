package com.hw.text;

import com.alibaba.fastjson.JSONObject;
import com.hw.service.ServiceElasticsearchApplication;
import com.hw.service.chain.config.CopyCourseChainManager;
import com.hw.service.util.FileDataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Date: 2020/12/15
 * @author WX964987
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceElasticsearchApplication.class)
public class AsyncTest {

    @Resource
    private FileDataType fileDataType;

    @Resource
    private CopyCourseChainManager copyCourseChainManager;


    @Test
    public void getSub() {
        String str = "img/index.html";
        System.out.println(str.substring(str.lastIndexOf(".")));
    }

    @Test
    public void copyZhiShi() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("tag", "skill zhishi");

        try {
            boolean handle = copyCourseChainManager.handle(jsonObject);
        } catch (Exception e) {
            System.out.println("【剽窃失败");
        }
    }


    public void test1() {
        String dataType = "";
        try {
            dataType = "娃哈哈";
        } catch (Exception e) {
            System.out.println("出事了!");
        } finally {
            String dataType1 = fileDataType.retrieveFileType("txt");
            System.out.println(dataType1);
        }
        System.out.println(dataType);
    }

    @Test
    public void test2() {
        AsyncTest asyncTest = new AsyncTest();
        asyncTest.test1();
    }

    @Test
    public void test3() {
        String filePage = test4("");
        assert filePage != null;
        System.out.println("411");
    }

    public String test4(String filePage) {
        return null;
    }


}
