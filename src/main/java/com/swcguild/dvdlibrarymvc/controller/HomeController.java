/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {
    
    // A Spring controller method should be public
    // return a string (aka the view name) have a good descriptive name
    // and then take in any parameters it needs (aka HttpServletRequest, Model etc)
    // but if it doesn't need them, it doesn't have to!!
    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String displayHomePage(){
        return "home"; // I don't want to do anything except go home.
    }
    
}
