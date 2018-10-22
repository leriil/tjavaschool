package com.tsystems.tshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")

public class MainController {



    private static final Logger log=Logger.getLogger("Log");

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
        return "redirect:/product/all";
    }

}