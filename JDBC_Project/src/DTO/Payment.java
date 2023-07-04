package DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Payment {
	//결제 정보관리 DTO - 종화
	private int payment_id;
	private String name;
	private int rent_no;
	private String payment_day;
	private int money;
	private String payment_method;
	private String car_no;
}