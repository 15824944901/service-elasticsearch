package com.hw.text;

import com.hw.service.entity.DataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 2020/8/28 11:58
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = TestIndex.class)
@Slf4j
public class TestIndex {
    private static final Logger logger = LoggerFactory.getLogger(TestIndex.class);

    public static final int FILE_SIZE = 1024;


    @Test
    public void sssss() {
        int page = 2;
        int pageSize = 2;
        List<Object> resultList = new ArrayList<>();
        resultList.add(1);
        resultList.add(2);
        resultList.add(3);
        resultList.add(4);
        if (((page / 10) - 1) * pageSize < resultList.size()) {
            resultList = resultList.subList((page == 0 ? 0 : page / 10) * pageSize, (((page / 10) + 1)
                * pageSize) < resultList.size() ? ((page / 10) + 1) * pageSize : resultList.size());
        }
        System.out.println(resultList);
    }

    // 拷贝文件夹
    @Test
    public void copyFileDir() throws IOException {
        File file = new File("D:\\ddhome\\arch\\resources\\data\\dataset\\2926\\15112.jpg");
        String fileName = file.getName();
        String[] split = fileName.split("\\.");
        System.out.println(file.getName());
        System.out.println("111" + fileName.replace("15112", "---"));
        System.out.println(split.length);
        File file1 = new File("D:\\ddhome\\arch\\resources\\data\\dataset\\2925");
        FileUtils.copyDirectory(file,file1);
    }

    @Test
    public void sssFile() {
        String filePath = "data/kls/2926/dataset/1070/aa/bb";
        String[] split1 = filePath.split("/");
        String id = "1070";
        String directory = split1[0] + "/" + split1[1] + "/" + split1[2] + "/" + id;
        for (int i = 5;i < split1.length; i++) {
            directory += "/" + split1[i] ;
        }
        System.out.println(directory);
    }

    // 拷贝文件
    @SneakyThrows
    @Test
    public void copyFile() {
        String webkitRelativePath = "aa/bb";
        String[] split = webkitRelativePath.split(",");
        for (int i = 1; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }

    @Test
    public void gets() {
        List<DataSource> dataSources = new ArrayList<>();
        // Map强转成对象后 不能遍历
        dataSources = (List<DataSource>) gg();
        Iterator<DataSource> iterator = dataSources.iterator();
        while (iterator.hasNext()) {
            DataSource next = iterator.next();
            System.out.println(next.getName());
        }
    }

    public Object gg() {
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "十一");

        maps.add(map);
        return maps;
    }

    // 对象转Map
    @Test
    public void getMap(){
        DataSource dataSource = new DataSource();
        dataSource.setName("十一");
        dataSource.setUpdateTime(new Date());

        Map<String, Object> data = new HashMap<>();
        BeanMap beanMap = BeanMap.create(dataSource);
        for (Object key : beanMap.keySet()) {
            data.put(key + "", beanMap.get(key));
        }
        System.out.println(data);
    }

    @Test
    public void insertFiles() {
        //fileRecursiveCall("dataset", new File("D:/ddhome/arch/resources/data/dataset/2926/algorithm"));
        System.out.println(machine("0.02MB"));
    }

    public String machine(String key) {
        String unit = key.substring(key.length() - 2, key.length() - 1);
        Double aDouble = Double.valueOf(key.substring(0, key.length() - 2));
        if (unit.equals("G")) {
            return aDouble * 1024 + unit + "i";
        } else {
            return aDouble + unit + "i";
        }
    }

    @Test
    public void ggg() {
        String ss = "1.0MB";
        String[] split = ss.split("\\.0");
        System.out.println(split[0] + "     " +split[1]);
        if (ss.contains(".0")  && 3 == ss.split("\\.")[1].length()) {
            ss = ss.replace(".0", "");
            System.out.println(ss);
        } else {
            System.out.println("1.01");
        }
    }

    private void fileRecursiveCall(String moduleType, File filePath) {
        File[] files = filePath.listFiles();
        if (Objects.isNull(files)) {
            return;
        }
        for (File file : files) {
            String fileName = file.getName();
            String directory = filePath.getAbsolutePath().substring("/ddhome/arch/resource/".length());
            System.out.println("filePath：" + directory + "/" + fileName);
            // 若是目录
            if (file.isDirectory()) {
                String dataType = "文件夹";
                System.out.println("文件夹:" + fileName + "  " +  moduleType + "  " + directory + "  " + dataType);
                // 递归调用
                fileRecursiveCall(moduleType, file);
            }
            // 若是文件
            if (file.isFile()) {
                // 文件类型
                String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
                System.out.println("type:" + substring.toLowerCase(Locale.ENGLISH));
                System.out.println("文件:" + fileName + "  " +  moduleType + "  " + directory);
            }
        }
    }

    @Test
    public void getBlSize(){
        double fileSize = 500;
        double size = (double) fileSize / FILE_SIZE;
        size = BigDecimal.valueOf(size).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        if (size == 0.00) {
            System.out.println(0.00);
        } else {
            System.out.println(size);
        }
    }

    // 打印文件夹下所有子文件
    @Test
    public void sss() {
        String a = "/ddhome/arch/resources/";
        System.out.println(a.length());
        File fileDir = new File("D:\\img\\新建文件夹\\新建文件夹");
        func(fileDir);
    }

    private static void func(File filePath){
        File[] files = filePath.listFiles();
        for(File file : files){
            //若是目录，则递归打印该目录下的文件
            if(file.isDirectory()) {
                String absolutePath = file.getAbsolutePath();
                System.out.println("文件夹:" + file.getName());
                func(file);
            }
            //若是文件，直接打印
            if(file.isFile()){
                System.out.println("文件:" + file.getName());
            }
        }
    }

    // 字符串为空判断
    @Test
    public void getIsBlank(){
        String str = "1,2,3,4,5";
        if (StringUtils.isNotBlank(str)) {
            System.out.println("不为空");
        } else if (StringUtils.isBlank(str)) {
            System.out.println("为空");
        }
    }

    // 字符串格式化
    @Test
    public void setFormat(){
        int versionNum = Integer.valueOf("V0001".replace("V", "")) + 1;
        System.out.println("V" + String.format("%04d", versionNum));
    }

    // 字符串截取
    @Test
    public void str () {
        String webkitRelativePath = "a/b/c/d/a.txt";
        System.out.println(webkitRelativePath.substring(0, webkitRelativePath.lastIndexOf(".")));
        System.out.println(webkitRelativePath);
    }

    // 读取文件内容
    @Test
    public void file() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\work\\checkstyle_ruleset_minimal.xml");
        byte[] bytes = new byte[FILE_SIZE];
        int cr = 0;
        while((cr = fis.read(bytes)) != -1){
            System.out.println(new String(bytes,0, cr));
        }
    }

    @Test
    public void getUUid(){
        File file = new File("D:\\Tools\\7-Zip\\7-zip32.dll");
        if (file.exists() && file.isFile()) {
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            Double aDouble = Double.valueOf(df.format((double) fileS / (FILE_SIZE * FILE_SIZE)));
            if (aDouble == 0.0) {
                System.out.println(0.001);
            } else {
                System.out.println(aDouble);
            }
        } else {
            System.out.println(0);
        }
    }

    @Test
    public void getDate(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(11, 19);
        System.out.println(min);
        String s = UUID.randomUUID().toString();
        System.out.println(s);
    }

    @Test
    public void split(){
        String[] filePath = {"<span style='color:red' >sstring</span>", "<span style='color:red' >adastrdsg</span>"};
        for (int i = 0; i<filePath.length;i++) {
            String index =  "str";
            int newPath = filePath[i].indexOf(index);
            String startStr = filePath[i].substring(filePath[i].indexOf(">") + 1, newPath);
            String endStr = filePath[i].substring(newPath + index.length(), filePath[i].lastIndexOf("<"));
            String fullPath = startStr + filePath[i].substring(0,filePath[i].indexOf(">") + 1 ) + index + filePath[i].substring(filePath[i].lastIndexOf("<")) + endStr;
            System.out.println(startStr + " " + endStr);
            System.out.println(fullPath);
        }

    }

    @Test
    public void getIfBl(){
        String filePath = "D:\\img ";
        File file = new File(filePath);

        if (file.isFile()) {
            System.out.println(Boolean.TRUE);
        } else {
            System.out.println(Boolean.FALSE);
        }


    }

    @Test
    public void getss(){
        String fileName = "aaa.ang";
        String substring = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(substring);
    }

    // 计算文件夹大小
    @Test
    public void exsitFile() {
        String filePath = "D:\\img\\新建文件夹\\新建文件夹";
        long fileSize = getDirSize(new File(filePath));
        BigDecimal bigDecimal = BigDecimal.valueOf(fileSize / 1.00 / FILE_SIZE / FILE_SIZE).setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(filePath + ": " + bigDecimal + "MB");
    }

    public static long getDirSize(File fileDir) {
        File[] files = fileDir.listFiles();
        long fileSize = 0;
        if (files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    fileSize += file.length();
                } else {
                    fileSize += getDirSize(file);
                }
            }
        }
        return fileSize;
    }

    // 正则验证
    private final String regex = "^[\\u4e00-\\u9fa5_a-zA-Z0-9.-/(/)]+$";
    @Test
    public void getssa() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("学习文档-(live).txt");
        boolean equals = matcher.matches();
        System.out.println(equals);
    }

    // 文件夹层级记录
    @Test
    public void setSs(){
        String webkitRelativePath = "/data/aa.txt";
        String[] split = webkitRelativePath.split("/");
        for (int i = 1;i < split.length;i++) {
            if (i !=1) {
                System.out.println("父目录:/data/dataset/1/" +  split[i-1]  + "     文件名称:" + split[i] );
                continue;
            }
            System.out.println("父目录:/data/dataset/1"  + "     文件名称:" + split[i]);
        }
    }

    // 两个List的值对比
    @Test
    public void getOlder() {
        boolean status = false;
        List<String> listBy = new ArrayList<>();
        listBy.add("1");
        listBy.add("3");
        listBy.add("4");
        List<String> listVo = new ArrayList<>();
        listVo.add("1");
        listVo.add("4");
        listVo.add("3");
        listVo.add("3");
        for (String list : listBy) {
            if (listBy.size() == listVo.size()) {
                for (String list1 : listVo) {
                    System.out.print("list:" + list);
                    System.out.println("  " + list1);
                    status = false;
                    if (list.equals(list1)) {
                        status = true;
                        break;
                    }
                }
                System.out.println("status:" + status);
            } else {
                System.out.println("长度不一致，flase");
            }
        }

    }

    // 随机数
    @Test
    public void getRan() {
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 5);
            logger.info("adwaf:{}" + random);
            System.out.println(random);
        }
    }

    // 数组转字符串
    @Test
    public void getJoin() {
        String str = "data/dataset/2925/index";
        List<String> list = new ArrayList<>();
        list.add(str.split("/")[0]);
        list.add(str.split("/")[1]);
        list.add(str.split("/")[2]);
        System.out.println(StringUtils.join(list, ","));
    }

    // 对象拷贝
    @Test
    public void strss() {
        DataSource dataSource = new DataSource();
        dataSource.setName("十一");
        DataSource dataSource2 = new DataSource();

        BeanUtils.copyProperties(dataSource, dataSource2);
        System.out.println(dataSource2);
    }

    // 文件大小计算单位
    public static String GetFileSize(File file){
        String size = "";
        if(file.exists() && file.isFile()){
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < FILE_SIZE) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / FILE_SIZE) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) +"GB";
            }
        }else if(file.exists() && file.isDirectory()){
            size = "0";
        }else{
            size = "0BT";
        }
        return size;
    }
}
