package com.perfuland.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfuland.perfulandia.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUser_name(String user_name);

}
