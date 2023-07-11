package DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Car_Inspection {
//자동차 검사 DTO - 진만
	private String car_no;
	private String inspection_type;
	private String Last_date;
	private String Next_date;
}