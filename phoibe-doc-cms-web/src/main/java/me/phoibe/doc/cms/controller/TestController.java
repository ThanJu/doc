package me.phoibe.doc.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test
 */
@RestController
@RequestMapping("phoibe/test")
public class TestController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

}
