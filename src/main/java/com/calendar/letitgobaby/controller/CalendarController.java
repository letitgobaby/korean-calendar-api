package com.calendar.letitgobaby.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import com.calendar.letitgobaby.dto.request.CalendarRequest;
import com.calendar.letitgobaby.dto.response.CalendarResponse;
import com.calendar.letitgobaby.service.CalendarBuilderService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalendarController {

  private final CalendarBuilderService calendarBuilder;

  @PostMapping(value = "/calendar")
  public ResponseEntity getCalendar(@RequestBody @Valid CalendarRequest calReq) {
    ArrayList calendarList = new ArrayList();
    if (calReq.getMonth() < 1) {
      for (int i = 1; i < 13; i++) {
        ArrayList monthList = calendarBuilder.getMonthCalendar(calReq.getYear(), i, calReq.getSpecificDay());
        calendarList.add(monthList);
      }
    } else {
      calendarList = calendarBuilder.getMonthCalendar(calReq.getYear(), calReq.getMonth(), calReq.getSpecificDay());
    }

    return ResponseEntity.ok(new CalendarResponse(calendarList));
  }

}
