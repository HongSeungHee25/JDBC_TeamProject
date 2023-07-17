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
public class CustomerServiceCenter {
	//고객센터 DTO - 승희
	private String name;
	private String customer_id;
	private String phone;
	private String service;
	
}
