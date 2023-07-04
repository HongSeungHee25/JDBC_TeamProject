package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Month_total {
	//월별 토탈 DTO - 승희
	private String months;
	private int total;
	
}
