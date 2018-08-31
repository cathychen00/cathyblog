package com.cathy.cathyblog.service;

import com.cathy.cathyblog.domain.User;

public interface UserService {
    User getAdmin();

    void save(User admin);
    User getByEmail(String email);
}
