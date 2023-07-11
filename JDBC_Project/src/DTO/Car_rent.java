package DTO;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Car_rent {
	public Car_rent() {
		// TODO Auto-generated constructor stub
	}
	//자동차 렌트예약 DTO - 종화
	private int rent_no;
	private String name;
	private String car_no;
	private String rent_start;
	private String rent_end;

}
