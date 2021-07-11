package com.calendar.letitgobaby.vo.command;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.json.simple.JSONArray;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarCommand {

  @NotNull @Min(1)
  private int year;

  @NotNull
  private boolean isHoliday;

  @Min(1) @Max(value=12, message="month는 1 ~ 12 사이의 숫자를 입력하세요.")
  private int month;

  private JSONArray specificDay;
  
}
