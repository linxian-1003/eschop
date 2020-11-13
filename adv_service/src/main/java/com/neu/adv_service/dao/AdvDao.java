package com.neu.adv_service.dao;

import com.neu.adv_service.domain.Adv;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvDao {
    List<Adv> findByType(int type, int size);
}
