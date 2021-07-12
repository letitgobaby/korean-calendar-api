package com.calendar.letitgobaby.repository;

import com.calendar.letitgobaby.vo.Holiday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

  @Query(
    nativeQuery = true, 
    value = "select * from HOLIDAY where MONTH = :month AND DAY = :date AND ISLUNAR = false"
  )
  Holiday findSolarHoliday(@Param("month") int month, @Param("date") int date);

  @Query(
    nativeQuery = true, 
    value = "select * from HOLIDAY where MONTH = :month AND DAY = :date AND ISLUNAR = true"
  )
  Holiday findLunarHoliday(@Param("month") int month, @Param("date") int date);

}
