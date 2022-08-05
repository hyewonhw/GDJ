package ex09_this;

public class School {

	// 필드
	private Student[] students;
	private int idx;	// students 배열의 인덱스. students배열에 저장된 학생수와 같다.
	
	// 생성자
	public School(int cnt) {
		students = new Student[cnt];
	}
	
	// 메소드
	public void addStudent(Student student) {
		if(idx == students.length) {
			System.out.println("Full");
			return;
		}
		students[idx++] = student;  
		// index 0 에 데이터가 전달 (초기값이 0이기때문)
		// [1]에 student1이 들어가야되기때문에 후위연산	
	}
	// 배열에 있는 정보를 출력한다
	public void printStudents() {
		/*일반 for문
		for(int i = 0; i < idx; i++) {
			System.out.println(students[i].getName() + ", " + students[i].getStuNo());
		}
		*/
		
		// 향상for문
		for(Student student : students) {
			if(student != null) {
				System.out.println(student.getName() + ", " + student.getStuNo());
			}
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
