package com.ouyeel.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
