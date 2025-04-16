package com.builder.pdfBuilder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Ok {
    @GetMapping("/")
    public String ok(){
        return  "ok";
    }
}
