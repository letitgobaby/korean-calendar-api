package com.calendar.letitgobaby.controller;

import javax.validation.Valid;

import com.calendar.letitgobaby.service.CalendarBuilderService;
import com.calendar.letitgobaby.vo.command.CalendarCommand;

import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalendarController {
  
  private final	CalendarBuilderService builder;

  @PostMapping(value = "/calendar")
  public ResponseEntity getCalendar(@RequestBody @Valid CalendarCommand command, BindingResult bindingResult) {
    ResponseEntity result;

    JSONArray cc = builder.getYearCalendar(command.getYear(), command.getMonth());
    result = ResponseEntity.ok().body(cc);

    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest()
        .body(bindingResult.getFieldError().getDefaultMessage());
    }

    System.out.println("\n\n" + command.toString() + "\n\n");

    return result;
  }

}
