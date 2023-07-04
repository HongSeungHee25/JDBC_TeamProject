package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class Customer {
	//회원 관리 DTO - 지수
	private String pw;
	private String name;
	private String customer_id;
	private String phone;
	private String licence;
}


