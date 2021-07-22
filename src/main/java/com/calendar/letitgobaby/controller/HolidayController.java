package com.calendar.letitgobaby.controller;

import java.util.ArrayList;

import com.calendar.letitgobaby.service.HolidayBuilderService;
import com.calendar.letitgobaby.vo.payload.HolidayPayload;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class HolidayController {

  private final HolidayBuilderService holidayService;

  @GetMapping("/holiday/{targetYear}")
  public ResponseEntity getAllHoliday(@PathVariable int targetYear) {
    ArrayList results = holidayService.getHoliday(targetYear);
    // return ResponseEntity.ok().body(results);

    return ResponseEntity.ok( new HolidayPayload(results));
  }

}
