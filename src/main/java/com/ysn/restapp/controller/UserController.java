package com.ysn.restapp.controller;


import com.ysn.restapp.helper.Helper;
import com.ysn.restapp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController extends Helper {

    @Autowired
    UsersService usersService;

    @PostConstruct
    public void init() {
        log.info("[OK]  UserController");
    }

    @RequestMapping(value = "/getUsers.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUsersList(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        return usersService.getUsersList();
    }
}
