package com.ysn.restapp.dao;

import com.ysn.restapp.model.ERole;
import com.ysn.restapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
