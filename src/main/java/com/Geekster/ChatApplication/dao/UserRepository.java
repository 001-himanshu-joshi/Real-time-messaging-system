package com.Geekster.ChatApplication.dao;

import com.Geekster.ChatApplication.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "Select * from user_table where username = :name and statusid= 1", nativeQuery = true)
    public List<User> findByUsername(String name);

    @Query(value = "select * from user_table where id = :userId and statusid=1", nativeQuery = true)
    public List<User> getAllUserByUserId(Integer userId);

    @Query(value = "select * from user_table where statusid=1", nativeQuery = true)
    public List<User> getAllUser();

    @Modifying
    @Transactional
    @Query(value = "Update user_table set statusid = 2 where id = :userId", countQuery = "SELECT count(*) FROM user_table", nativeQuery = true)
    public void deleteUserByUserId(@Param("userId") int userId);

}
