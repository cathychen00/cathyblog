package com.cathy.cathyblog.repository;
import com.cathy.cathyblog.domain.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {
    List<User> findAllByUserRole(String userRole);
    void save(User user);
}
