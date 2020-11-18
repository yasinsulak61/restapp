package com.ysn.restapp.service;

import com.ysn.restapp.Authentication.model.User;
import com.ysn.restapp.dao.UsersDao;
import com.ysn.restapp.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService extends Helper {

    @Autowired
    UsersDao usersDao;

    @PostConstruct
    public void init() {
        log.info("[OK]  UserService");
    }

    public Map<String, Object> getUsersList() {
        Map<String, Object> result = new HashMap<>();
        List<User> userList = usersDao.findAll();
        List<User> allCityList = usersDao.findAll();
        result.put("data", userList);
        result.put("success", true);
        result.put("total", allCityList.size());
        return result;
    }


}
