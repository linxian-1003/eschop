package com.neu.order_service.service.impl;

import com.netflix.discovery.converters.Auto;
import com.neu.order_service.dao.BookDetailDao;
import com.neu.order_service.domain.BookDetail;
import com.neu.order_service.service.BookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDetailServiceImpl implements BookDetailService {
    @Autowired
    BookDetailDao bookDetailDao;
    @Override
    public int add(BookDetail bookDetail) {
        return bookDetailDao.add(bookDetail);
    }
}
