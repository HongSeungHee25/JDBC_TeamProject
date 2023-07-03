package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class Car_Superintend {
	//자동차 관리 DTO - 진만
	private int car_id;
	private String car_garde;
	private String carType;
	private String rent_Type;
	private int price;
	private int insurance;
	private String car_no;
	private String PL;
}