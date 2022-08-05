package ex02_field;

public class SchoolMain {

	public static void main(String[] args) {
		
		// 1. 학교(객체)를 생성
		// 2. 학교의 이름을 지정
		// 3. 학생(배열로 생성해둔 두명)을 생성
		// 4. 학생 학번과 이름을 저장
		// 5. 배열에 각 학생 저장
		// 6. 배열 짝꿍 for문으로 각 학생 학번, 이름 출력
		
		School school = new School();
		school.name = "경인중학교";
		
		Student student1 = new Student();
		student1.stuNo = "11025";
		student1.name = "전지현";
		Student student2 = new Student();
		student2.stuNo = "11026";
		student2.name = "정우성";
		
		// 저장 (안하면 출력시 null값이라 출력 불가능)
		school.students[0] = student1;
		school.students[1] = student2;
		
		// school.student는 배열임
		for(int i = 0; i < school.students.length;i++) {
			System.out.println(school.students[i].stuNo);
			System.out.println(school.students[i].name);
		}
		
		
		
		
	}
}
		
		 

