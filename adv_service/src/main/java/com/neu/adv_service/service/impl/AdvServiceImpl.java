package com.neu.adv_service.service.impl;

import com.neu.adv_service.dao.AdvDao;
import com.neu.adv_service.domain.Adv;
import com.neu.adv_service.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvServiceImpl implements AdvService {
    @Autowired
    AdvDao advDao;

    //@Autowired
    //RestTemplate restTemplate;


    @Override
    public List<Adv> findByType(int type, int size) {
        return advDao.findByType(type,size);
    }
}
