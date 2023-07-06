package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
@AllArgsConstructor
@Builder
public class Customer {
	//회원 관리 DTO - 지수
	private String name;
	private String customer_id;
	private String pw;
	private String phone;
	private String licence;
}


