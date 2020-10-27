package com.hw.service.service;

import com.hw.service.entity.DataSource;
import com.hw.service.util.AsyncCheckName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Date: 2020/10/27
 * @author WX964987
 */
@Service
public class EsServiceImpl implements EsService{


    @Override
    public DataSource searchDataSource() throws InterruptedException {
        AsyncCheckName asyncCheckName = new AsyncCheckName();
        asyncCheckName.checkName(new ArrayList<DataSource>());
        return null;
    }

}
