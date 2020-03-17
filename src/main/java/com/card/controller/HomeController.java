package com.card.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tenece on 16/03/2020.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "/index.html";
    }
}
