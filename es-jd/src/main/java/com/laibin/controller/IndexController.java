package com.laibin.controller;

import com.laibin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }

}
