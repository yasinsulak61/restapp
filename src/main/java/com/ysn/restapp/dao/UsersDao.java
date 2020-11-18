package com.ysn.restapp.dao;

import com.ysn.restapp.Authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao extends JpaRepository<User, Long> {

    public List<User> findAll();

}
