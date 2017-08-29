package com.ouyeel.generator;

import java.util.Map;

/**
 * 配置类：获取generator.properties中属性值
 *
 * @author dujiayong
 * @create 2017-08-29 10:22
 */
public class Config {
    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "pwd";
    public static final String SCHEMA = "schema";
    public static Map<String,String> properties = null;

    public String getDatabaseSchema(){
        return Config.properties.get(SCHEMA);
    }

    public String getDatabaseDriver(){
        return Config.properties.get(DRIVER);
    }

    public String getDatabaseUrl(){
        return Config.properties.get(URL);
    }


}
