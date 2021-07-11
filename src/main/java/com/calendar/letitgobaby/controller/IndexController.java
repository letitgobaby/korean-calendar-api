package com.calendar.letitgobaby.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class IndexController {

  @GetMapping("/test")
  public ResponseEntity test() {
    ResponseEntity test = ResponseEntity.ok().body(null);
    return test;
  }

}