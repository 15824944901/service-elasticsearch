package com.hw.text;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hw.service.common.DataExcetptionEnum;
import com.hw.service.entity.DataSource;
import com.hw.service.entity.Student;
import com.hw.service.enums.DataTypeEnum;
import com.hw.service.exception.ServiceException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Date: 2020/8/28 11:58
 */

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = ServiceElasticsearchApplication.class)
@Slf4j
public class TestIndex {
    private static final Logger logger = LoggerFactory.getLogger(TestIndex.class);

    private static final String SEPARATOR = File.separator;

    @Resource
    private RestTemplate restTemplate;

    public static final int FILE_SIZE = 1024;

    private final String regex = "^[a-zA-Z0-9-_./(/)]+$";

    private static final Map<String, Object> fileMap = new HashMap() {{
        put("test", "测试");
        put("test1", "测试1");
        put("test2", "测试2");
    }};


    @Test
    public void getete() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        System.out.println(currentTime_2);
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
    }

    /**
     * 字符串转数组
     */
    @Test
    public void toArray() {
        String ids = "[804675310699757568,804756206379466752,806112677012918272]";
        System.out.println(Arrays.asList(ids.split(",")));
        List<Long> longs = JSON.parseArray(ids, Long.class);
        System.out.println(longs);
    }

    /**
     * 数组转字符串
     */
    @Test
    public void getArras() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        String jsonString = JSONObject.toJSONString(list);
        System.out.println(jsonString);
    }


    @Test
    public void getParent() {
        List<Student> studentList = new ArrayList<>();

        Student student = new Student();
        student.setId(1L);
        student.setName("十");
        student.setLevel(1);
        student.setParentId(1L);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("十一");
        student2.setLevel(2);
        student2.setParentId(1L);

        Student student3 = new Student();
        student3.setId(3L);
        student3.setName("二十");
        student3.setLevel(1);
        student3.setParentId(3L);

        Student student4 = new Student();
        student4.setId(4L);
        student4.setName("二十一");
        student4.setLevel(2);
        student4.setParentId(3L);

        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        Map<Long, List<Student>> collect = studentList.stream().filter(student1 -> student1.getLevel() != 1).collect(Collectors.groupingBy(Student::getParentId));

        System.out.println(collect);
    }

    /**
     * 原子类操作
     */
    @Test
    public void getAtom() {
        AtomicInteger count = new AtomicInteger();
        System.out.println(count.addAndGet(1)); // ++i
        System.out.println(count.getAndAdd(1)); // i++
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void getEnum() {
        int code = DataTypeEnum.LOCAL_DATA.getCode();
        System.out.println(code);
        System.out.println(DataTypeEnum.LOCAL_DATA);
    }


    @Test
    public void isObject() {
        DataSource dataSource = new DataSource();
        dataSource.setFirstName("123");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dataSource);
        Object firstName = jsonObject.get("firstName");
        System.out.println(firstName);
    }

    @Test
    public void getIsEnmg() {
        int fileNumbers = 12;
        int fullNum = 10;
        int surplusNum = fileNumbers % fullNum;
        if (fileNumbers > 100) {
            fullNum = fileNumbers / fullNum;
            surplusNum = fileNumbers - fullNum * 10;
        }

        if (fullNum == 2) {
            System.out.println("1");
        } else if (surplusNum != 0 && 0 == fileNumbers - 12) {
            System.out.println("2");
        }
        System.out.println("fullNum: " + fullNum);
        System.out.println("surplusNum: " + surplusNum);

    }

    @Test
    public void getReplace() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("4");
        list.add("3");

        List<String> arrayList = new ArrayList<>();
        for (String str : list) {
            if (str.contains("1")) {
                str = str.replace("1", "\\1");
            }
            arrayList.add(str);
        }
        System.out.println(arrayList);
    }


    @Test
    public void zzeng() {
        String test = "^[a-zA-Z0-9]{4,5}";
        String str = "12345";

        Pattern compile = Pattern.compile(test);
        Matcher matcher = compile.matcher(str);
        boolean matches = matcher.matches();
        System.out.println(matches);
    }

    @Test
    public void getList() {
        List<Object> list = new ArrayList<>();
        list = null;
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("isEmpty");
        } else {
            System.out.println("NotEmpty");
        }

        String url = "www.%s.com";
        String StrFormat = String.format(url, "baidu%s");
        System.out.println(StrFormat);

        String  count = "%d";
        String intFormat = String.format(count, 1);
        System.out.println(intFormat);

    }


    @Test
    public void testMap() {
        Map<String, Object> maps = new HashMap<>();
        maps.put("1", "十一");
        maps.put("2", "十二");
        maps.put("3", "十三");

        System.out.println("1--->" + maps);

        maps.forEach((key, value) -> {
            if (key.equals("2")) {
                maps.put("4", value);
                System.out.println("qqq" + value);
            }
        });
        maps.remove("2");
        System.out.println("2--->" + maps);
    }


    @Test
    public void getException() {
        String str = "2";
        String check = "1";
        if (check.equals(str)) {
            throw new ServiceException(DataExcetptionEnum.DATA_IS_EXITS);
        }
    }

    @Test
    public void checkMapForeach() {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("1", 1);
            put("2", 1);
        }};

        setSep(map);

        map.forEach((key, value) -> {
            System.out.println("sdawda :" + value);
        });

        if (org.apache.commons.lang.StringUtils.isEmpty(setLogg("meta"))) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public String setLogg(String dataType) {
        if (dataType.contains(".ckpt.data") || dataType.contains(".data") || dataType.startsWith("data")) {
            return "代码";
        } else if ("checkpoint".equals(dataType) || "meta".equals(dataType) || dataType.equals("data")
            || "index".equals(dataType)) {
            return "代码";
        }
        return "11";
    }

    public void setSep(Map<String, Object> map) {
        map.put("3", "数");
        map.put("4", "七");
    }


    @Test
    public void getVal() {
        val str = "01,02";
        System.out.println(str.substring(0, str.indexOf(",")));
        System.out.println(str.substring(str.indexOf(",")));
        /*String[] split = str.split(",");
        System.out.println(split[1]);*/
    }

    @Test
    public void windFileNameLenght() {
        boolean dbChanged = false;
        String dataSetIndustry = "data";
        String sourceIndustry = null;
        DataSource dataSource = new DataSource();
        dataSource.setFilePath("data/data");
        DataSource dataSource2 = new DataSource();
        if (org.apache.commons.lang.StringUtils.isNotBlank(dataSetIndustry) && org.apache.commons.lang.StringUtils.isNotBlank(sourceIndustry)) {
            if (!dataSetIndustry.equals(sourceIndustry)) {
                dbChanged = true;
            }
        }
        if (!dataSource.getFilePath().equals(dataSource2.getFilePath())) {
            System.out.println("1");
        }
        System.out.println(dbChanged);
    }

    @Test
    public void testTime() {
        DataSource dataSource = new DataSource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dataSource.setUpdateTime(timestamp);
        System.out.println(dataSource.getUpdateTime());
        System.out.println(new Random().nextInt());
    }

    @Test
    public void listRetiall() {
        List<DataSource> list = new ArrayList<>();
        DataSource dataSource = new DataSource();
        dataSource.setId("1");
        dataSource.setName("十一");
        dataSource.setSize(1.1);
        DataSource dataSource2 = new DataSource();
        dataSource2.setId("2");
        dataSource2.setName("十二");
        dataSource2.setSize(2.2);
        DataSource dataSource3 = new DataSource();
        dataSource3.setId("3");
        dataSource3.setName("十二");
        dataSource3.setSize(2.2);
        list.add(dataSource);
        list.add(dataSource2);
        list.add(dataSource3);


        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("3");
        list1.add("2");

        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("1");
        list2.add("2");
        List<String> collect = null;
        boolean status = false;
        /*for (String str : list1) {
            status = list.stream().noneMatch(dataSource1 -> dataSource1.getId().equals(str));
        }*/
        if (list1.size() == list2.size()) {
            status = list2.removeAll(list1);
        }
        System.out.println(status);
        System.out.println(list2.size() + "---");
        /*for (String str : list1) {
            if (!list.isEmpty()) {
                list.removeIf(filesBindingBy -> str.equals(filesBindingBy.getId()));
            }
        }*/
        //System.out.println(Objects.requireNonNull(collect).size());
        /*if (Objects.requireNonNull(collect).size() != 0) {
            System.out.println(collect.get(0));
            System.out.println("true");
        }*/
    }


    @Test
    public void listStreamStr() {
        List<DataSource> list = new ArrayList<>();
        DataSource dataSource2 = new DataSource();
        dataSource2.setName("十一");
        dataSource2.setSize(0);
        dataSource2.setFilePath("D://data");
        dataSource2.setFirstName("1");

        DataSource dataSource3 = new DataSource();
        dataSource3.setName("十二");
        dataSource3.setSize(1);
        dataSource3.setFilePath("D://data");

        list.add(dataSource2);
        list.add(dataSource3);

        List<DataSource> collect = list.stream().filter(dataSource -> "1".equals(dataSource.getFirstName())).collect(Collectors.toList());
        System.out.println(collect);

        DataSource dataSource4 = new DataSource();
        dataSource4.setName("十二");
        dataSource4.setSize(1);
        dataSource4.setFilePath("D://data");

        Map<String, Object> map = new HashMap<>();

        map.put("data", "2012");

        System.out.println(String.valueOf(map.get("date")));

        /*List<DataSource> v01 = list.stream().peek(dataSource -> {
            if (dataSource.getSize() == 0) {
                dataSource.setVersion("V01");
            }
        }).collect(Collectors.toList());
        System.out.println(v01);*/
        /*List<DataSource> changeList = new ArrayList<>();
        changeList.add(dataSource2);
        DataSource dataSource4 = new DataSource();
        dataSource4.setName("十二");
        dataSource4.setSize(2);
        dataSource4.setFilePath("D://data");
        changeList.add(dataSource3);
        List<DataSource> collect = list.stream().collect(Collectors.toList());
        collect.removeAll(changeList);
        changeList.removeAll(collect);
        if (list.size() == changeList.size() && collect.isEmpty()) {
            System.out.println(Boolean.TRUE);
        } else {
            System.out.println(Boolean.FALSE);
        }*/

    }


    @Test
    public void tttttttttt() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Object> httpEntity = new HttpEntity<>(null, httpHeaders);
        String url = "https://www.baidu.com";
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(exchange);
    }


    @Test
    public void getFileMap() {
        if (fileMap.containsKey("test1")) {
            System.out.println(fileMap.get("test1"));
        }
    }

    @Test
    public void testSub() {
        double size = (double) 1541406720 / 1024.0 / 1024;
        double v = BigDecimal.valueOf(size).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        System.out.println(v);

        if (Boolean.TRUE) {

        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        // 定时调度，每个调度任务会至少等待`period`的时间，
        // 如果任务执行的时间超过`period`，则等待的时间为任务执行的时间
        executor.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("1----" + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);

        // 定时调度，第二个任务执行的时间 = 第一个任务执行时间 + `delay`
        executor.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("2----" + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);

        // 定时调度，延迟`delay`后执行，且只执行一次
        executor.schedule(() -> System.out.println("5 秒之后执行 schedule"), 5, TimeUnit.SECONDS);
    }


    @Test
    public void sssssss() {
        String str = "1.1";
        boolean contains = str.contains(".");
    }

    @Test
    public void listStream() {
        List<String> list = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();

        list.add("1");
        list.add("3");
        list.add("2");
        list.add("4");

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        map.put("age", 24);
        map.put("name", "1");
        map2.put("age", 23);
        map3.put("age", 25);
        map4.put("age", 234);
        map4.put("name", "4");
        map5.put("age", 1);
        list2.add(map);
        list2.add(map2);
        list2.add(map3);
        list2.add(map4);
        list2.add(map5);
        Collections.sort(list2, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> front, Map<String, Object> later) {
                Integer frontIndex = Integer.valueOf(front.get("age").toString());
                Integer laterIndex = Integer.valueOf(later.get("age").toString());
                // 顺序
                if (false) {
                    return laterIndex.compareTo(frontIndex);
                } else {
                    return frontIndex.compareTo(laterIndex);
                }
            }
        });
        System.out.println(list2);
    }

    @Test
    public void testDifferenceSet() {
        int page = 2;
        int pageSize = 2;
        List<String> resultList = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        resultList.add("a");
        resultList.add("b");
        resultList.add("d");
        resultList.add("c");
        AtomicReference atomicReference = new AtomicReference();
        atomicReference.set(resultList);
        //boolean a = resultList.stream().anyMatch(str -> "w".equals(str));
        System.out.println(atomicReference);

        list2.add("c");
        list2.add("d");
        list2.add("e");
        list2.add("f");

        /*int startPage = page - 1;
        if ((startPage / pageSize) * pageSize < resultList.size()) {
            resultList = resultList.subList(((page == 0) ? 0 : startPage) * pageSize, ((startPage + 1)
                * pageSize) < resultList.size() ? (startPage + 1) * pageSize : resultList.size());
        }*/
        System.out.println(resultList);
    }

    private List listSortByFilSizeBy(String sortOrder, List<Map<String, Object>> recordList) {
        return org.apache.commons.lang.StringUtils.isBlank(sortOrder) || "desc".equals(sortOrder) ? recordList.stream()
            .sorted(Comparator.comparing((Map<String, Object> m) -> (double) m.get("fileSize")).reversed())
            .collect(Collectors.toList()) : recordList.stream()
            .sorted(Comparator.comparing((Map<String, Object> m) -> (double) m.get("fileSize")))
            .collect(Collectors.toList());
    }

    @Test
    public void listBySort() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 1);
        map.put("fileSize", 1.04);
        map.put("updateTime", "2020-11-11T08:19:30.230Z");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("age", 0);
        map2.put("fileSize", 1.03);
        map2.put("updateTime", "2020-11-11T08:19:32.230Z");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("age", 3);
        map3.put("fileSize", 1.02);
        map3.put("updateTime", "2020-11-11T08:19:31.230Z");
        List<Map<String, Object>> recordList = new ArrayList<>();
        recordList.add(map);
        recordList.add(map2);
        recordList.add(map3);
        recordList = listSortByFilSizeBy("desc", recordList);
        /*for (int i = 0;i < recordList.size() - 1;i++) {
            for (int j = 0;j < recordList.size() - 1 - i;j++) {
                Double fileSize = Double.valueOf(recordList.get(j).get("fileSize").toString());
                Double fileSizej = Double.valueOf(recordList.get(j + 1).get("fileSize").toString());
                if(fileSize < fileSizej) {
                    Map<String, Object> recordMap = recordList.get(j);
                    recordList.set(j, recordList.get(j + 1));
                    recordList.set(j + 1, recordMap);
                }
            }
        }*/
        //listSortByUpdataTime(recordList);
        System.out.println(recordList);
    }

    //  list列表根据元素中的时间字段进行排序
    public List listSortByUpdataTime(List<Map<String, Object>> initList) {
        List<Map<String, Object>> list = initList;
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @SneakyThrows
            @Override
            public int compare(Map<String, Object> map, Map<String, Object> map2) {
                String dateTime = map.get("updateTime").toString();
                String dateTime2 = map2.get("updateTime").toString();
                dateTime = dateTime.replace("Z", " UTC");
                dateTime2 = dateTime2.replace("Z", " UTC");
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z"); // 转换时区格式
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化时间
                // 将Z时间格式转换成Date类型格式或换成毫秒
                Date date = format1.parse(dateTime);
                Date date2 = format1.parse(dateTime2);
                String time = format2.format(date);
                String time2 = format2.format(date2);
                return time.compareTo(time2);
            }
        });
        Collections.reverse(list);
        return list;
    }

    /**
     * 文件大小排序
     *
     * @param sortOrder  排序顺序
     * @param recordList 排序集合
     * @return 排序后的集合
     */
    public List listSortByFilSize(String sortOrder, List<Map<String, Object>> recordList) {
        Collections.sort(recordList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> front, Map<String, Object> later) {
                Integer frontIndex = Integer.valueOf(front.get("fileSize").toString());
                Integer laterIndex = Integer.valueOf(later.get("fileSize").toString());
                if (StringUtils.isBlank(sortOrder) || "desc".equals(sortOrder)) {
                    return laterIndex.compareTo(frontIndex);
                } else {
                    return frontIndex.compareTo(laterIndex);
                }
            }
        });
        return recordList;
    }

    @Test
    public void sssss() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        DataSource dataSource = new DataSource();
        dataSource.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(dataSource.getUpdateTime());
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
        FileUtils.copyDirectory(file, file1);
    }

    @Test
    public void sssFile() {
        String filePath = "data/kls/2926/dataset/1070/aa/bb";
        String[] split1 = filePath.split("/");
        String id = "1070";
        String directory = split1[0] + "/" + split1[1] + "/" + split1[2] + "/" + id;
        for (int i = 5; i < split1.length; i++) {
            directory += "/" + split1[i];
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
    public void getMap() {
        DataSource dataSource = new DataSource();
        dataSource.setName("十一");
        dataSource.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        Map<String, Object> data = new HashMap<>();
        BeanMap beanMap = BeanMap.create(dataSource);
        for (Object key : beanMap.keySet()) {
            data.put(key + "", beanMap.get(key));
        }
        System.out.println(data);
    }

    @Test
    public void insertFiles() {
        String fileName = "1.ckpt.data-100";
        String dataType = "";
        int indexOf = fileName.indexOf(".");
        if (indexOf == -1) {
            //dataType = FileDataType.retrieveFileType(fileName);
        } else {
            if (indexOf == 0) {
                //dataType = FileDataType.retrieveFileType(fileName);
            } else {
                //dataType = FileDataType.retrieveFileType(fileName.substring(indexOf + 1));
            }
        }
        logger.info("dataType: {}", dataType);
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
        System.out.println(split[0] + "     " + split[1]);
        if (ss.contains(".0") && 3 == ss.split("\\.")[1].length()) {
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
                System.out.println("文件夹:" + fileName + "  " + moduleType + "  " + directory + "  " + dataType);
                // 递归调用
                fileRecursiveCall(moduleType, file);
            }
            // 若是文件
            if (file.isFile()) {
                // 文件类型
                String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
                System.out.println("type:" + substring.toLowerCase(Locale.ENGLISH));
                System.out.println("文件:" + fileName + "  " + moduleType + "  " + directory);
            }
        }
    }

    @Test
    public void getBlSize() {
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

    private static void func(File filePath) {
        File[] files = filePath.listFiles();
        for (File file : files) {
            //若是目录，则递归打印该目录下的文件
            if (file.isDirectory()) {
                String absolutePath = file.getAbsolutePath();
                System.out.println("文件夹:" + file.getName());
                func(file);
            }
            //若是文件，直接打印
            if (file.isFile()) {
                System.out.println("文件:" + file.getName());
            }
        }
    }

    // 字符串为空判断
    @Test
    public void getIsBlank() {
        String str = "1,2,3,4,5";
        if (StringUtils.isNotBlank(str)) {
            System.out.println("不为空");
        } else if (StringUtils.isBlank(str)) {
            System.out.println("为空");
        }
    }

    // 字符串格式化
    @Test
    public void setFormat() {
        int versionNum = Integer.valueOf("V0001".replace("V", "")) + 1;
        System.out.println("V" + String.format("%04d", versionNum));
    }

    // 字符串截取
    @Test
    public void str() {
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
        while ((cr = fis.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, cr));
        }
    }

    @Test
    public void getUUid() {
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
    public void getDate() {
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
    public void split() {
        String[] filePath = {"<span style='color:red' >sstring</span>", "<span style='color:red' >adastrdsg</span>"};
        for (int i = 0; i < filePath.length; i++) {
            String index = "str";
            int newPath = filePath[i].indexOf(index);
            String startStr = filePath[i].substring(filePath[i].indexOf(">") + 1, newPath);
            String endStr = filePath[i].substring(newPath + index.length(), filePath[i].lastIndexOf("<"));
            String fullPath = startStr + filePath[i].substring(0, filePath[i].indexOf(">") + 1) + index + filePath[i].substring(filePath[i].lastIndexOf("<")) + endStr;
            System.out.println(startStr + " " + endStr);
            System.out.println(fullPath);
        }

    }

    @Test
    public void getIfBl() {
        String filePath = "D:\\img ";
        File file = new File(filePath);

        if (file.isFile()) {
            System.out.println(Boolean.TRUE);
        } else {
            System.out.println(Boolean.FALSE);
        }


    }

    @Test
    public void getss() {
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

    @Test
    public void getssa() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("学习文档-(live).txt");
        boolean equals = matcher.matches();
        System.out.println(equals);
    }

    // 文件夹层级记录
    @Test
    public void setSs() {
        String webkitRelativePath = "/data/aa.txt";
        String[] split = webkitRelativePath.split("/");
        for (int i = 1; i < split.length; i++) {
            if (i != 1) {
                System.out.println("父目录:/data/dataset/1/" + split[i - 1] + "     文件名称:" + split[i]);
                continue;
            }
            System.out.println("父目录:/data/dataset/1" + "     文件名称:" + split[i]);
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
    public static String GetFileSize(File file) {
        String size = "";
        if (file.exists() && file.isFile()) {
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < FILE_SIZE) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / FILE_SIZE) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) + "GB";
            }
        } else if (file.exists() && file.isDirectory()) {
            size = "0";
        } else {
            size = "0BT";
        }
        return size;
    }
}
