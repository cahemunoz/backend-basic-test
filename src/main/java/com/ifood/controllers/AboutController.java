package com.ifood.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/about")
public class AboutController {

	@GetMapping
    public ResponseEntity<String> getAbout() {
    	return ResponseEntity.ok("O Serviço está no ar!");
    }
}