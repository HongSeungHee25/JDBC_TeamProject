package DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Manager_Pay {
	private String name;
	private int money;
	private String payment_method;
	private String grade;
}
