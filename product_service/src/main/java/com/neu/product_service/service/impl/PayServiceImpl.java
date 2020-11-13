package com.neu.product_service.service.impl;
import com.neu.product_service.dao.PayDao;
import com.neu.product_service.domain.Pay;
import com.neu.product_service.query.PayQuery;
import com.neu.product_service.service.PayService;
import com.neu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl extends BaseServiceImpl<Pay, PayQuery> implements PayService {

    @Autowired
    public void setPayDao(PayDao PayDao){
        this.baseMapper = PayDao;
    }


}
