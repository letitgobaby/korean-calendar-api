package com.calendar.letitgobaby.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.util.LunarSolarConverter;
import com.calendar.letitgobaby.vo.DateInfo;
import com.calendar.letitgobaby.vo.DayOfWeekType;
import com.calendar.letitgobaby.vo.Holiday;
import com.calendar.letitgobaby.vo.Lunar;
import com.calendar.letitgobaby.vo.Solar;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarBuilderService {

  private final HolidayRepository holidayRepository;
	private final LunarSolarConverter converter;
  
  public ArrayList getMonthCalendar(int year, int month, ArrayList<Holiday> holiList) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1); 
		int thisMonthLastDate = cal.getActualMaximum(Calendar.DATE); // 해당 달의 마지막 날
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일 계산

		ArrayList weekArr = new ArrayList(); 

		int count = 1; // 일자 카운트 1일 부터 시작
		for (int week = 0; week < 6; week++) {
			ArrayList dayArr = new ArrayList();

			// 첫 배열 전달 데이터 넣기
			if (count == 1) {
				for (int j = 1; j < dayOfWeek; j++) {
					DateInfo lastInfo = dayOfWeekInfo( beforeDateBuilder(year, month, dayOfWeek - (j+1)), j);
					dayArr.add(lastInfo);
				}
			}

			// 해당 월 데이터 넣기
			for (int index = dayOfWeek; index < 8; index++) {
				if (count <= thisMonthLastDate) {
					DateInfo info = dayOfWeekInfo( dateBuilder(year, month, count), index);

					// specificDay(사용자 휴일) 처리
					if (info.getIsHoliday().equals("none") && holiList != null) {
						int targetDate = count;
						holiList.forEach(item -> {
							if (item.getDay() == targetDate && item.getMonth() == month) {
								info.setIsHoliday(item.getDateName());
							}
						});
					}

					dayArr.add(info);	count++;

				} else {
					// 마지막 열 다음달 데이터 넣기
					DateInfo nextInfo = dayOfWeekInfo(nextDateBuilder(year, month, count - thisMonthLastDate), index);
					dayArr.add(nextInfo); count++;
				}
			}

			dayOfWeek = 1; // 1(일요일)로 초기화
			weekArr.add(dayArr);
		}

		return weekArr;
  }


	private DateInfo dateBuilder(int year, int month, int date) {
		Solar solar = converter.solarInfo(year, month, date);
		Lunar lunar = converter.lunarInfo(year, month, date);

		return new DateInfo(solar, lunar, holidayInfo(solar, lunar));
	}


	private DateInfo beforeDateBuilder(int year, int month, int count) {
		int lastDate;
		Solar solar;
		Lunar lunar;
		if ((month - 2) < 1) {
			lastDate = getLastDate(year - 1, 12) - count;
			solar = converter.solarInfo(year - 1, 12, lastDate);
			lunar = converter.lunarInfo(year - 1, 12, lastDate);
		} else {
			lastDate = getLastDate(year, month - 1) - count;
			solar = converter.solarInfo(year, month - 1, lastDate);
			lunar = converter.lunarInfo(year, month - 1, lastDate);
		}

		return new DateInfo(solar, lunar, holidayInfo(solar, lunar));
	}


	private DateInfo nextDateBuilder(int year, int month, int count) {
		Solar solar; 
		Lunar lunar;
		if ((month + 1) > 12) {
			solar = converter.solarInfo(year + 1, 1, count);
			lunar = converter.lunarInfo(year + 1, 1, count);
		} else {
			solar = converter.solarInfo(year, month + 1, count);
			lunar = converter.lunarInfo(year, month + 1, count);
		}

		return new DateInfo(solar, lunar, holidayInfo(solar, lunar));
	}


	private DateInfo dayOfWeekInfo(DateInfo info, int index) {
		for (DayOfWeekType type : DayOfWeekType.values()) {
			if (type.getWeekIndex() == index)  {
				info.setDayOfWeek(type.getValue());
				break;
			}
		}

		return info;
	}

	
	private String holidayInfo(Solar solar, Lunar lunar) {
		Holiday holiday = holidayRepository.findHoliday(
			solar.getSolarMonth(), solar.getSolarDay(),
			lunar.getLunarMonth(), lunar.getLunarDay()
		);

		if (holiday == null) return "none";

		return holiday.getDateName();
	}

	
	private int getLastDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);

		return cal.getActualMaximum(Calendar.DATE);
	}


}