package com.calendar.letitgobaby.service;

import java.util.ArrayList;
import java.util.List;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.util.DayCalculation;
import com.calendar.letitgobaby.util.LunarSolarConverter;
import com.calendar.letitgobaby.vo.Holiday;
import com.calendar.letitgobaby.vo.HolidayInfo;
import com.calendar.letitgobaby.vo.Solar;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayBuilderService {

  private final HolidayRepository holidayRepository;
  private final LunarSolarConverter converter;
  private final DayCalculation dayCal;

  public ArrayList getHoliday(int targetYear) {
    ArrayList holidayArr = new ArrayList();
    List<Holiday> hois = holidayRepository.findAll();

    for (int i = 0; i < hois.size(); i++) {
      Holiday holidayObj = hois.get(i);
      int year = targetYear;
      int month = holidayObj.getMonth();
      int date = holidayObj.getDay();

      if (holidayObj.isLunar()) {
        Solar solar = converter.lunarToSolar(year, month, date);
        year = solar.getSolarYear();
        month = solar.getSolarMonth();
        date = solar.getSolarDay();
      }

      HolidayInfo holidayInfo = HolidayInfo.builder()
          .year(year).month(month).date(date)
          .dateName(holidayObj.getDateName()).build();

      if (isNationalHoliday(holidayObj.getDateName())) {
        holidayArr.add( dayCal.getPrevDate(holidayInfo) );
        holidayArr.add( dayCal.getNextDate(holidayInfo) );
      }

      holidayArr.add(holidayInfo);
    }

    return holidayArr;
  }


  private boolean isNationalHoliday(String holiday) {
    boolean result;
    switch(holiday) {
      case "설날" :
        result = true;
        break;
      case "추석" :
        result = true;
        break;

      default: result = false;
    }

    return result;
  }

}
