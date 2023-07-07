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
public class Price_Screen {
	//결제 정보 DTO - 승희
	private String rent_start;
	private String rent_end;
	private int money;
	private String payment_method;
}
