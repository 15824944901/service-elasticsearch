package com.hw.service.util;

import com.hw.service.entity.DataSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Date: 2020/10/27
 *
 * @author WX964987
 */
public class AsyncCheckName {

    @Async
    public Future<List<DataSource>> checkName(List<DataSource> dataSourceList) throws InterruptedException {
        System.out.println("async1:" + LocalDateTime.now() +
            ",id:" + Thread.currentThread().getId());
        /*for (DataSource dataSource : dataSourceList) {
            dataSource.setName("十一");
        }*/
        Thread.sleep(5000);
        return  new AsyncResult<>(dataSourceList);
    }

}
