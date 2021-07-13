package com.calendar.letitgobaby.controller;

import java.util.ArrayList;

import com.calendar.letitgobaby.service.HolidayBuilderService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class HolidayController {

  private final HolidayBuilderService holidayService;

  @GetMapping("/holiday")
  public ResponseEntity test() {
    ArrayList results = holidayService.getHoliday();
    return ResponseEntity.ok().body(results);
  }

}
