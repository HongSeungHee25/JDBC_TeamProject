package Project_230703;

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

	private int car_id;
	private String inspection_type;
	private Date last_inspection;
	private Date next_inspection;
}
