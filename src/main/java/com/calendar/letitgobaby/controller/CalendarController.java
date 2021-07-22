package com.calendar.letitgobaby.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import com.calendar.letitgobaby.service.CalendarBuilderService;
import com.calendar.letitgobaby.vo.command.CalendarCommand;
import com.calendar.letitgobaby.vo.payload.CalendarPayload;

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

  /**
   * @param {int} year, ?month, ?specificDay
   * @return {JSONArray} - 1년 달력 데이터 or 한달 달력 데이터
   * @throws error int가 아닌 값이 들어올때, 필수값인 year이 없을 때
   */
  @PostMapping(value = "/calendar")
  public ResponseEntity getCalendar(@RequestBody @Valid CalendarCommand command) {
    ArrayList calendarList = new ArrayList();
    if (command.getMonth() < 1) {
      for (int i = 1; i < 13; i++) {
        ArrayList monthList = calendarBuilder.getMonthCalendar(command.getYear(), i, command.getSpecificDay());
        calendarList.add(monthList);
      }
    } else {
      calendarList = calendarBuilder.getMonthCalendar(command.getYear(), command.getMonth(), command.getSpecificDay());
    }

    return ResponseEntity.ok(new CalendarPayload(calendarList));
  }

}
