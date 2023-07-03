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
	private String customer_id;
	private int car_id;
	private Date rent_start;
	private Date rent_end;

}
