package com.calendar.letitgobaby.service;

import com.calendar.letitgobaby.repository.HolidayRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HolidayBuilderService {
  
  private final HolidayRepository holidayRepository;

  public void getHoliday() {
    System.out.println( holidayRepository.findAll(). );
  }

}
