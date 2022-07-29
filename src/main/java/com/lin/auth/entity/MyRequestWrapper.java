package com.lin.auth.entity;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

public class MyRequestWrapper extends HttpServletRequestWrapper {
    private final String body;

    public MyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = this.getBodyString(request);
    }

    public String getBody() {
        return this.body;
    }

    public String getBodyString(HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        String bodyString = "";
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(contentType) || !contentType.contains("multipart/form-data") && !contentType.contains("x-www-form-urlencoded")) {
            return IOUtils.toString(request.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
        } else {
            Map<String, String[]> parameterMap = request.getParameterMap();

            Entry next;
            String value;
            for(Iterator var6 = parameterMap.entrySet().iterator(); var6.hasNext(); sb.append((String)next.getKey()).append("=").append(value).append("&")) {
                next = (Entry)var6.next();
                String[] values = (String[])next.getValue();
                value = null;
                if (values != null && values.length == 1) {
                    value = values[0];
                } else {
                    value = Arrays.toString(values);
                }
            }

            if (sb.length() > 0) {
                bodyString = sb.toString().substring(0, sb.toString().length() - 1);
            }

            return bodyString;
        }
    }

    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(this.body.getBytes());
        return new ServletInputStream() {
            public boolean isFinished() {
                return false;
            }

            public boolean isReady() {
                return false;
            }

            public void setReadListener(ReadListener readListener) {
            }

            public int read() {
                return bais.read();
            }
        };
    }

    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}

