package com.ysn.restapp.Authentication.dao;

import com.ysn.restapp.Authentication.model.ERole;
import com.ysn.restapp.Authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
