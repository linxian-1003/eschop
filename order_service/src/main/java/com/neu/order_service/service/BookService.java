package com.neu.order_service.service;

import com.github.pagehelper.PageInfo;
import com.neu.order_service.domain.Book;
import com.neu.order_service.query.BookQuery;

import com.neu.service.BaseService;


public interface BookService{
    int order(Book book);
    PageInfo<Book> findByQuery(BookQuery bookQuery);

    boolean update(Book book);
}
