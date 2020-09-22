package com.ifood.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/about")
public class About {

    public About() {

    }

    @RequestMapping
    public String ok() {
        return "Serviço no ar";
    }

}
