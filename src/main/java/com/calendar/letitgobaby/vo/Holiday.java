package com.calendar.letitgobaby.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "HOLIDAY")
public class Holiday {
  
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "DATENAME")
  private String dateName;

  @Column(name = "MONTH")
  private int month;

  @Column(name = "DAY")
  private int day;

  @Column(name = "ISLUNAR")
  private boolean isLunar;

  public Holiday() {}

  @Builder
  public Holiday(String dateName, int month, int day, boolean isLunar) {
    this.dateName = dateName;
    this.month = month;
    this.day = day;
    this.isLunar = isLunar;
  }

}


// ID BIGINT AUTO_INCREMENT,
// DATENAME VARCHAR(50) NOT NULL,
// MONTH SMALLINT NOT NULL,
// DAY SMALLINT NOT NULL,
// ISLUNAR boolean NOT NULL,