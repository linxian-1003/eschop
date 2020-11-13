package com.neu.adv_service.service;

import com.neu.adv_service.domain.Adv;

import java.util.List;

public interface AdvService {
    List<Adv> findByType(int type, int size);
}
