package com.calendar.letitgobaby.repository;

import com.calendar.letitgobaby.vo.Holiday;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
  
}
