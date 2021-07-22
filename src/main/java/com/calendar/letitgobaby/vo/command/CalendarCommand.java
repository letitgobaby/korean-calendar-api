package com.calendar.letitgobaby.vo.command;

import java.util.ArrayList;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.calendar.letitgobaby.vo.Holiday;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarCommand {

  @NotNull(message = "year은 필수로 입력해야합니다.")
  @Min(value = 1, message = "year은 년도에 맞는 숫자를 입력하세요.")
  @Max(value = 3000, message = "year은 3000년 이전만 입력하세요.")
  private int year;

  @Max(value = 12, message = "month는 1 ~ 12 사이의 숫자를 입력하세요.")
  private int month;

  private ArrayList<Holiday> specificDay;
  
}
