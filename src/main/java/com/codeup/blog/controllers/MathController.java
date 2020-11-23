package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{valOne}/and/{valTwo}")
    @ResponseBody
    public double add (@PathVariable double valOne, @PathVariable double valTwo){
        return valOne + valTwo;
    }
    @GetMapping("/subtract/{valOne}/from/{valTwo}")
    @ResponseBody
    public double subtract (@PathVariable double valOne, @PathVariable double valTwo){
        return valTwo - valOne;
    }
    @GetMapping("/multiply/{valOne}/and/{valTwo}")
    @ResponseBody
    public double multiply (@PathVariable double valOne, @PathVariable double valTwo){
        return valOne * valTwo;
    }
    @GetMapping("/divide/{valOne}/by/{valTwo}")
    @ResponseBody
    public double divide (@PathVariable double valOne, @PathVariable double valTwo){
        return valOne / valTwo;
    }
}
