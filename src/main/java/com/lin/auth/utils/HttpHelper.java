package com.lin.auth.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * requestBody重复读工具类
 */
public class HttpHelper {
    private static final Logger log = LoggerFactory.getLogger(HttpHelper.class);

    private HttpHelper() {
    }

    public static String getBodyString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();

        try {
            InputStream inputStream = request.getInputStream();
            Throwable var3 = null;

            try {
                InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                Throwable var5 = null;

                try {
                    BufferedReader reader = new BufferedReader(isr);
                    Throwable var7 = null;

                    try {
                        String line = "";

                        while((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (Throwable var54) {
                        var7 = var54;
                        throw var54;
                    } finally {
                        if (reader != null) {
                            if (var7 != null) {
                                try {
                                    reader.close();
                                } catch (Throwable var53) {
                                    var7.addSuppressed(var53);
                                }
                            } else {
                                reader.close();
                            }
                        }

                    }
                } catch (Throwable var56) {
                    var5 = var56;
                    throw var56;
                } finally {
                    if (isr != null) {
                        if (var5 != null) {
                            try {
                                isr.close();
                            } catch (Throwable var52) {
                                var5.addSuppressed(var52);
                            }
                        } else {
                            isr.close();
                        }
                    }

                }
            } catch (Throwable var58) {
                var3 = var58;
                throw var58;
            } finally {
                if (inputStream != null) {
                    if (var3 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var51) {
                            var3.addSuppressed(var51);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }
        } catch (IOException var60) {
            log.error(var60.getMessage(), var60);
        }

        return sb.toString();
    }
}

