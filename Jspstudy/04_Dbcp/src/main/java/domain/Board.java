package domain;
//***** 1 *****//

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 디폴트생성자
@AllArgsConstructor // 필드이용한생성자
@Getter
@Setter
@Builder
@ToString  // +getter,setter = @Data
public class Board {
	// 맵핑을 하기위해 이름 똑같이 맞추기
	private int board_no;
	private String title;
	private String content;
	private Date create_date;
}
