package com.calendar.letitgobaby.service;

import java.util.ArrayList;
import java.util.List;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.util.LunarSolarConverter;
import com.calendar.letitgobaby.vo.Holiday;
import com.calendar.letitgobaby.vo.Solar;
import com.calendar.letitgobaby.vo.payload.HolidayPayload;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HolidayBuilderService {
  
  private final HolidayRepository holidayRepository;
  private final LunarSolarConverter converter;

  public ArrayList getHoliday(int targetYear) {
    ArrayList holidayArr = new ArrayList();
    List<Holiday> hois = holidayRepository.findAll();

    for (int i = 0; i < hois.size(); i++) {
      Holiday holidayObj = hois.get(i);
      HolidayPayload payload;
      if (holidayObj.isLunar()) {
        Solar solar = converter.lunarToSolar(targetYear, holidayObj.getMonth(), holidayObj.getDay());
        payload = new HolidayPayload(solar.getSolarYear(), solar.getSolarMonth(), solar.getSolarDay(), holidayObj.getDateName());
      } else {
        payload = new HolidayPayload(targetYear, holidayObj.getMonth(), holidayObj.getDay(), holidayObj.getDateName());
      }

      holidayArr.add(payload);
    }
    
    return holidayArr;
  }




}
