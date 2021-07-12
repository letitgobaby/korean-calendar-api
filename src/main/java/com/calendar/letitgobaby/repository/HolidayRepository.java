package com.calendar.letitgobaby.repository;

import com.calendar.letitgobaby.vo.Holiday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
  
  @Query(
    nativeQuery = true, 
    value = "select * from HOLIDAY " +
            "where (MONTH = :smonth AND DAY = :sdate AND ISLUNAR = false) " + 
            "OR (MONTH = :lmonth AND DAY = :ldate AND ISLUNAR = true) "
  )
  Holiday findHoliday(
    @Param("smonth") int smonth, @Param("sdate") int sdate, 
    @Param("lmonth") int lmonth, @Param("ldate") int ldate
  );

}
