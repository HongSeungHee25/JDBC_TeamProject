package DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Car_Inspection {
//자동차 검사 DTO - 진만
	private String car_no;
	private String inspection_type;
	private Date Last_date;
	private Date Next_date;
}