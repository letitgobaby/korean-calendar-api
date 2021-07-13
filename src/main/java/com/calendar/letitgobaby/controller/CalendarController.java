package com.calendar.letitgobaby.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import com.calendar.letitgobaby.service.CalendarBuilderService;
import com.calendar.letitgobaby.vo.command.CalendarCommand;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalendarController {
  
  private final	CalendarBuilderService calendarBuilder;

  @PostMapping(value = "/calendar")
  public ResponseEntity getCalendar(@RequestBody @Valid CalendarCommand command, BindingResult bindingResult) {
    ArrayList calendarList;
    // if (command.getMonth() ) {
      calendarList = calendarBuilder.getMonthCalendar(command.getYear(), command.getMonth());
    // }
    
    System.out.println("#####  " + command.getMonth() );

    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest()
        .body(bindingResult.getFieldError().getDefaultMessage());
    } 
    // System.out.println("#####  " + command.getSpecificDay().get(0).getClass().getName() + "  --  " + command.getSpecificDay().get(0) );
    return ResponseEntity.ok().body(calendarList);
  }

  @PostMapping(value = "/calendar/{year}/{month}")
  public ResponseEntity getMonthCalendar(@PathVariable int year, @PathVariable int month) {
    ArrayList calendarList = calendarBuilder.getMonthCalendar(year, month);

    return ResponseEntity.ok().body(calendarList);
  }

  @GetMapping(value = "/calendar/{year}")
  public ResponseEntity getYearCalendar(@PathVariable int year) {
    ArrayList yearArr = new ArrayList();

    for (int i = 1; i < 13; i++) {
      ArrayList calendarList = calendarBuilder.getMonthCalendar(year, i);
      yearArr.add(calendarList);
    }

    return ResponseEntity.ok().body(yearArr);
  }

}
