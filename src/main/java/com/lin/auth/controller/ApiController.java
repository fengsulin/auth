package com.lin.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.lin.auth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/sign")
    public void testSign(@RequestBody User user, HttpServletRequest request){
        logger.info(JSONObject.toJSONString(user));
    }
}
