package ex04_condition;

public class Ex03 {

	public static void main(String[] args) {
		
//		조건 연산자
//		조건을 만족하는 경우와 그렇지 않은 경우 모두를 처리하는 연산
//		형식
//		조건 ? 만족하는 경우 : 만족하지 않는 경우
		
		int score = 40;
		String result = (score >= 60) ? "합격" : "불합격";
		System.out.println(result + "\n");
		
//		연습
//		순위가 1이면 "금메달" , 순위가 2이면 "은메달" , 순위가 3이면 "동메달"
//		나머지 순위는 "없음"
		System.out.println("연습1 메달 구분");
		int rank = 4;
		String medal = (rank ==1) ? "금메달" : (rank ==2) ? "은메달" : (rank == 3) ? " 동메달" : "메달없음";
		System.out.println(medal + "\n");
		
//		연습2
//		홀수는 홀수 , 짝수는 짝수
//		힌트
//		홀수 2로 나눈 나머지가 1인 수
//		짝수 2로 나눈 나머지가 0인 수
		System.out.println("연습2 홀수 짝수 구분");
		int n = -1;
//		String type = ((n % 2) == 0 && (n != 0))  ? "짝수" : ((n % 2) == 1) ? "홀수" : (n == 0) ? "0" : "홀수도 짝수도 아님" ;
		String type = (n == 0) ? "0" : (n % 2) == 0   ? "짝수" : (n % 2) == 1 ? "홀수" : "그외";
		System.out.println(type + "\n");
		
//		연습3
//		홀수는 "홀수" , 짝수는 "짝수" , 3의 배수는 "3의 배수"
//		힌트
//		3의 배수는 3으로 나눈 나머지가 0인수
		System.out.println("연습3 3의배수와 홀수 짝수 구분");
		int a = 0;
		String type2 = ((a % 3) == 0 && (a != 0)) ? "3의 배수" : ((a % 2) == 0 && (a != 0)) ? "짝수": ((a % 2) == 1) ? "홀수" : "그외";
		System.out.println(type2 + "\n");
		
//		연습4
//		주민등록번호 뒷 7 자리 중 첫 번째 숫자가 1,3,5이면 "남자" , 2,4,6이면 "여자"
		System.out.println("연습4 주민등록번호 앞자리로 성별 구분");
		int serial = 4234567;
		String gender = (((serial / 1000000 ) % 2) == 0) ? "여자" : (((serial / 1000000 ) % 2) ==1) ? "남자" : "그외" ;
		System.out.println(gender + "\n");
		
      }
}
