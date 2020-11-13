package com.neu.auth_service.service;

import com.neu.auth_service.domain.Auth;

public interface AuthService {
    String auth(Auth auth);

    String info(String token);
}
