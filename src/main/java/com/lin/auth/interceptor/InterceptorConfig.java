package com.lin.auth.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lin.auth.utils.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class InterceptorConfig implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取参数
        String queryString = request.getQueryString();
        logger.info("请求参数：{}",queryString);

        // 获取请求body
        byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
        String body = new String(bodyBytes, request.getCharacterEncoding());
        logger.info("请求体：{}",body);

        Map<String,Object> map = JSONObject.parseObject(body, Map.class);
        String signStr = String.valueOf(map.get("sign"));
        byte[] sign = Base64.getDecoder().decode(signStr);
        map.remove("sign");

        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        TreeMap<Object, Object> tm = new TreeMap<>();
        while (iterator.hasNext()){
            String key = String.valueOf(iterator.next());
            tm.put(key,map.get(key));
        }
        Map<String, Object> keyMap = RSAUtils.initKey();
        String publicKey = RSAUtils.publicKey;
        String mapSorStr = JSONObject.toJSONString(tm);
        logger.info("传过来的字符串：mapSorStr：{}",mapSorStr);

        // 公钥验证
        boolean flagB = RSAUtils.verify(mapSorStr.getBytes(StandardCharsets.UTF_8), sign, publicKey);
        if(flagB){
            return true;
        }else {
            return false;
        }
    }
}
