package com.ysn.restapp.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper{

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public SimpleDateFormat genericDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date getDate() {
        return new Date();
    }
}
