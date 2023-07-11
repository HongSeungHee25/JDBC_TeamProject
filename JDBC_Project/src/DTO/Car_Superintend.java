package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Setter
@Builder
public class Car_Superintend {
	public Car_Superintend() {
		// TODO Auto-generated constructor stub
	}
	//자동차 관리 DTO - 진만
	private String car_no;
	private String car_garde;
	private String carType;
	private String rent_Type;
	private int price;
	private int insurance;
	private String PL;
}