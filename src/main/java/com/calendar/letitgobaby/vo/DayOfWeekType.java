package com.calendar.letitgobaby.vo;

public enum DayOfWeekType {
  
  SUN(1, "일"),
  MON(2, "월"),
  TUE(3, "화"),
  WED(4, "수"),
  THU(5, "목"),
  FRI(6, "금"),
  SAT(7, "토");

  private final int weekIndex;
  private final String value;
  
  public int getWeekIndex() {
    return weekIndex;
  }

  public String getValue() {
      return value;
  }

  public String getKoreanWeekName(int weekIndex) {
    for (DayOfWeekType type : DayOfWeekType.values()) {
      if (type.getWeekIndex() == weekIndex) return type.getValue();
    }
    return null;
  }

  DayOfWeekType(int weekIndex, String value) {
    this.weekIndex = weekIndex;
    this.value = value;
  }

}
