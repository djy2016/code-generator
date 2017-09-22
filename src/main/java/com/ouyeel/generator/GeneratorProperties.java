package com.ouyeel.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 更新数据连接属性文件
 *
 * @author dujiayong
 * @create 2017-08-29 9:12
 */
public class GeneratorProperties {

    /**
     * 这里driver驱动=mysql数据库驱动
     */
    public static void update(String url,String user,String pwd,String schema){
        String filePath = GeneratorProperties.class.getClassLoader().getResource("generator").getPath() + "/generator.properties";
        Properties p = new Properties();
        Map<String,String> map = new HashMap<>();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            p.load(inputStream);
            p.setProperty(Config.URL,url);
            p.setProperty(Config.USERNAME,user);
            p.setProperty(Config.PASSWORD,pwd);
            if(StringUtils.isNotEmpty(schema)){
                p.setProperty(Config.SCHEMA,schema);
            }
            Enumeration e = p.propertyNames();
            while (e.hasMoreElements()){ //properties文件属性转存map
                String key = (String) e.nextElement();
                String value = (String) p.get(key);
                map.put(key,value);
            }
            Config.properties = map;
            outputStream = new FileOutputStream(filePath);
            p.store(outputStream,"Update generator.properties file");
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //关闭流
            close(inputStream,outputStream);
        }
    }

    private static void close(InputStream inputStream,OutputStream outputStream){
        try {
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
