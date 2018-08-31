package com.cathy.cathyblog.repository;
import com.cathy.cathyblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUserRole(String userRole);
    List<User> findAllByUserEmail(String email);
}
