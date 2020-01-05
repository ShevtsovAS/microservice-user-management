package com.sha.repository;

import com.sha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("select u.name from User u where u.id in (:ids)")
    List<String> findUserNames(@Param("ids") List<Long> idList);
}
