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
	private String customer_id;
	private String pass;
	private String name;
	private String phone;
	private String licence;
}

