package domain;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data // Getter, Setter, ToString 등

public class ContactDTO {

	private int contact_no;
	private String name;
	private String tel;
	private String email;
	private Date reg_date;
	
}
