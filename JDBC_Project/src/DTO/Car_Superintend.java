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
	
	private int car_id;
	private String car_garde;
	private String carType;
	private String rent_Type;
	private int price;
	private int insurance;
	private String car_No;
	private String PL;
}