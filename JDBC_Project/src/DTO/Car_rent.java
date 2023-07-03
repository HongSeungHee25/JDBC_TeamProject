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
	private int rent_no;
	private String customer_id;
	private int car_id;
	private Date rent_start;
	private Date rent_end;

}
