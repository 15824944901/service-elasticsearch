package com.hw.service.service;

import com.hw.service.entity.DataSource;

/**
 * Date: 2020/10/27
 * @author WX964987
 */
public interface EsService {

    DataSource searchDataSource() throws InterruptedException;
}
