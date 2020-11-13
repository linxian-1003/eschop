package com.neu.order_service.dao;

import com.neu.mapper.BaseMapper;
import com.neu.order_service.domain.Book;
import com.neu.order_service.query.BookQuery;
import com.neu.product_service.domain.Product;
import com.neu.product_service.query.ProductQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDao{
   int order (Book book);
   List<Book> findByQuery(BookQuery bookQuery);

    int update(Book book);
}
