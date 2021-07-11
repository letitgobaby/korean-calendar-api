package com.calendar.letitgobaby.service;

import java.util.List;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.vo.Holiday;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HolidayService {

  private final HolidayRepository repository;

  public List<Holiday> findAll() {
    return repository.findAll();
  }


}
