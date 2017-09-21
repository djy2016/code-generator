package com.ouyeel.generator.controller;

import com.ouyeel.generator.GeneratorProperties;
import com.ouyeel.generator.Main;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 代码生成器控制类
 *
 * @author dujiayong
 * @create 2017-08-28 17:49
 */
@Controller
@RequestMapping(value = "/codeGenerator")
public class CodeGeneratorController {

    @RequestMapping(method = RequestMethod.GET)
    public String showCodeGenerator() {
        return "codeGenerator";
    }

    @RequestMapping(value = "/generate",method = RequestMethod.POST)
    public String generate(HttpServletRequest request){
        String projectPackage = request.getParameter("projectPackage");
        String url = request.getParameter("url");
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        String tables = request.getParameter("tables");
        String schema = request.getParameter("schema");
        String outputPath = request.getParameter("outputPath");
        //更新generator.properties属性文件设置
        GeneratorProperties.update(url,user,pwd,schema);
        try {
            if(StringUtils.isEmpty(outputPath)){
                outputPath = "D:/temp/";
            }else{
                if(!outputPath.endsWith("/")){
                    outputPath = outputPath + "/";
                }
            }
            Main.run(projectPackage,tables,outputPath);
            //windows的dos模式执行的命令
            String command = "cmd /c start " + outputPath;
            //调用执行cmd指令
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "codeGenerator";
    }
}
