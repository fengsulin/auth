package com.lin.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.lin.auth.entity.User;
import com.lin.auth.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/sign")
    public void testSign(@RequestBody User user,HttpServletRequest request) throws IOException {
        String ipAddress = IpUtils.getIpAddress(request);
        System.out.println(ipAddress);
        try {
            String macAddress = IpUtils.getMacAddress();
            System.out.println(macAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        logger.info(JSONObject.toJSONString(user));
    }
}
