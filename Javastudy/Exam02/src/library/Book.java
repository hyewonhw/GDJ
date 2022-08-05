package library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor		// new Book()
@AllArgsConstructor		// new Book(1, "어린왕자", "생텍쥐베리")
@Getter					// getBookNo(), getTitle(), getAuthor()
@Setter					// setBookNo(1), setTitle("어린왕자"), setAuthor("생텍쥐베리")
@ToString				// String toString() { ... } -> System.out.println(books[i]); 여기서 쓰기위해 만들어줌
public class Book {
	private int bookNo;		// 1~100 자동부여
	private String title;	// 사용자입력
	private String author;	// 사용자입력
	// 저장된 책을 꺼내올때 title이 private이기 때문에 Getter필요
	
}
