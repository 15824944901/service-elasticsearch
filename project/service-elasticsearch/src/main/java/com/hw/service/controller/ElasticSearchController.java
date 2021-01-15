package com.hw.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.hw.service.entity.DataSource;
import com.hw.service.util.ElasticSearchPage;
import com.hw.service.util.ElasticsearchUtil;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Date: 2020/9/8
 */
@RestController
public class ElasticSearchController {

    /**
     * 测试索引
     */
    private String indexName = "megacorp";

    // private static final String indexName = "data-center";

    // private static final String esType = "file";

    /**
     * 类型
     */
    private String esType = "employee";

    /**
     * 创建索引
     *
     * http://127.0.0.1:8124/createIndex
     * @return String
     */
    @RequestMapping("/createIndex")
    public String createIndex() {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    /**
     * 插入记录
     *
     * @return String
     */
    @RequestMapping("/insertJson")
    public String insertJson(DataSource dataSource) {
        System.out.println(dataSource.getName());
        if (StringUtils.isNotEmpty(dataSource.getName())) {
            System.out.println("ok");
        }
        if (StringUtil.isNullOrEmpty(dataSource.getFilePath())) {
            System.out.println("yes");
        }
        System.out.println("no");
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", DateUtil.formatDate(new Date()));
        jsonObject.put("age", 25);
        jsonObject.put("first_name", "j-" + new Random(100).nextInt());
        jsonObject.put("last_name", "cccc");
        jsonObject.put("about", "i like xiaofeng baby");
        jsonObject.put("date", new Date());
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));*/
        return "";
    }

    /**
     * 插入记录
     *
     * @return String
     */
    @RequestMapping("/insertModel")
    public String insertModel() {
        DataSource dataSource = new DataSource();
        dataSource.setId("66");
        dataSource.setFirstName("m-" + new Random(100).nextInt());
        dataSource.setUserId(new Random().nextInt());
        dataSource.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dataSource);
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }

    /**
     * 插入记录
     *
     * @return String
     */
    @RequestMapping("/insert")
    public String insertModel(DataSource dataSource) {
        String[] names = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六"};
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            DataSource dataSourceBy = new DataSource();
            dataSourceBy.setName(names[1]);
            dataSourceBy.setFilePath("shier");
            dataSourceBy.setVersion("S" + (i + 1));
            dataSourceBy.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dataSourceBy);
            list.add(jsonObject);
        }
        ElasticsearchUtil.addDataFiles(list, indexName, esType);
        return "1";
    }

    /**
     * 删除记录
     *
     * @param id   数据ID
     * @return String
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, esType, id);
            return "删除id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 批量删除记录
     *
     * @param id   数据Ids
     * @return String
     */
    @RequestMapping("/deletes")
    public String deletes(String id) {
        String [] ids = {"8B7D3229B4A24A2184C038DE32B49717"};
        if (ids != null) {
            ElasticsearchUtil.deleteData(indexName, esType, ids);
            return "删除id=" + ids;
        } else {
            return "id为空";
        }
    }

    /**
     * 更新数据
     *
     * @param id 数据ID
     * @return String
     */
    @RequestMapping("/update")
    public String update(String id) {
        if (StringUtils.isNotBlank(id)) {
            List<JSONObject> list = new ArrayList<>();
            DataSource dataSource = new DataSource();
            dataSource.setId(id);
            dataSource.setFilePath("shier");
            dataSource.setName("一一");
            dataSource.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dataSource);
            list.add(jsonObject);
            ElasticsearchUtil.addDataFiles(list, indexName, esType);
            //JSONObject jsonObject = new JSONObject();
            //ElasticsearchUtil.updateDataById(jsonObject, indexName, esType, id);
            return "id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 删除索引
     *
     * @param index 删除索引库
     * @return String
     */
    @RequestMapping("/deleteIndex")
    public String deleteIndex(String index) {
        if (!"".equals(index) & null != index) {
            boolean deleteType = ElasticsearchUtil.deleteIndex(index);
            if (deleteType != false) {
                return "删除索引=" + index;
            }
            return "索引不存在";
        } else {
            return "索引为空";
        }
    }

    /**
     * 获取数据
     * http://127.0.0.1:8080/es/getData?id=2018-04-25%2016:33:44
     *
     * @param id 数据ID
     * @return String
     */
    @RequestMapping("/getData")
    public String getData(String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, esType, id, null);
            return JSONObject.toJSONString(map);
        } else {
            return "id为空";
        }
    }

    /**
     * 模糊查询
     *
     * @param keyword 查询条件
     * @return String
     */
    @RequestMapping("/queryMatchData")
    public String queryMatchData(String keyword) {
        // 确认索引是否存在，不存在则创建
        boolean indexExist = ElasticsearchUtil.isIndexExist(indexName);
        if (!indexExist) {
            ElasticsearchUtil.createIndex(indexName);
        }
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 不进行分词搜索
        if (true) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            List<String> list = new ArrayList<>();
            list.add("一");
            list.add("二");
            for (String str : list) {
                boolQueryBuilder.should(QueryBuilders.wildcardQuery("name.keyword", str + "*"));
            }
            boolQuery.must(boolQueryBuilder);
            //boolQueryBuilder.should(QueryBuilders.wildcardQuery("name.keyword", "一" + "*"));
            //boolQueryBuilder.should(QueryBuilders.wildcardQuery("name.keyword", "二" + "*"));
        } else {
            // 分词搜索
            boolQuery.must(QueryBuilders.matchQuery("name", keyword));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.
            searchListData(indexName, esType, boolQuery,0, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     *
     * @param startPage 当前下标
     * @param pageSize  查询条数
     * @param name      查询字段
     * @param query     查询值
     * @return  String
     */
    @RequestMapping("/queryWildcardData")
    public String queryWildcardData(int startPage, int pageSize, String name, String query) {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery();
        if (name != null && query != null) {
            queryBuilder = QueryBuilders.wildcardQuery(name, "*" + query + "*");
        }
        List<Map<String, Object>> list =
            ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, startPage,pageSize, null, null, name);
        return JSONObject.toJSONString(list);
    }

    /**
     * 正则查询
     *
     * @return String
     */
    @RequestMapping("/queryRegexpData")
    public String queryRegexpData() {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("first_name.keyword", "m--[0-9]{1,11}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 0,10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数字范围数据
     *
     * @return String
     */
    @RequestMapping("/queryIntRangeData")
    public String queryIntRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from(24)
            .to(25));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 0,10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询日期范围数据
     *
     * @return String
     */
    @RequestMapping("/queryDateRangeData")
    public String queryDateRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").from("20")
            .to("50"));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery,0, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询分页
     *
     * @param startPage 第几条记录开始
     *                  从0开始
     *                  第1页 ：http://127.0.0.1:8080/es/queryPage?startPage=0&pageSize=2
     *                  第2页 ：http://127.0.0.1:8080/es/queryPage?startPage=2&pageSize=2
     * @param pageSize  每页大小
     * @return String
     */
    @RequestMapping("/queryPage")
        public String queryPage(String startPage, String pageSize) {
        if (StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.rangeQuery("age").from("20")
                .to("100"));
            ElasticSearchPage list = ElasticsearchUtil.searchDataPage(indexName, esType, Integer.parseInt(startPage), Integer.parseInt(pageSize), boolQuery, null, null, null);
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }
}
