package com.neu.order_service.dao;

import com.neu.order_service.domain.BookDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDetailDao {
    int add(BookDetail bookDetail);
    List<BookDetail> findByBookId(Integer bookId);
}
