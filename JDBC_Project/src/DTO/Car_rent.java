package DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Car_rent {
	//자동차 렌트예약 DTO - 종화
	private int rent_no;
	private String name;
	private String car_no;
	private Date rent_start;
	private Date rent_end;

}