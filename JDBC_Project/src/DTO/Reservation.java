package DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class Reservation {
	private String carNo;
    private String carType;
    private String rentStart;
    private String rentEnd;
    private String paymentMethod;
    private int money;

}
