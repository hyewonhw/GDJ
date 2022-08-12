package Prac_0729;

public class ex05 {

	public static void main(String[] args) {
		
		// 해설이랑 매우다름
		// 이대로 구현해보고싶은데 가능한건지모르겠음
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("피카츄의 점수 입력 >>>");
		int score1 = sc.nextInt();
		System.out.println("뽀로로의 점수 입력 >>>");
		int score2 = sc.nextInt();
		System.out.println("브레드의 점수 입력 >>>");
		int score3 = sc.nextInt();
		
		double p = (double)((score1 + score2 + score3) / 3);
		
		if(score1 > score2 > score3) {
			System.out.println("피카츄");
		} else if (scor){
		
		}
		System.out.println("평균 : " + p + "점");
		System.out.println("1등 : " + );
		System.out.println("3등 : " + );
		*/
		
		String[] names = {"뽀로로", "크롱","에디"};
		
		int[] scores = new int[names.length];
		
		for(int i = 0; i < scores.length; i++) {
			scores[i] = (int)(Math.random() * 101);
		}
		
		int total = scores[0];
		int max = scores[0];
		int min = scores[0];
		int top = 0;
		int bottom = 0;
		for(int i = 1; i < scores.length; i++) {
			total += scores[i];
			if(max < scores[i]) {
				max = scores[i];
				top = i;
			}
			if(min > scores[i]) {
				min = scores[i];
				bottom = i;
			}
		}
		
		double average = (double)total / scores.length;
		System.out.println("평균 : " + average + "점");
		System.out.println("1등 점수 : " + max + "점");
		System.out.println("1등 이름 : " + names[top]);
		System.out.println("꼴등 점수 : " + min + "점");
		System.out.println("꼴등 이름 : " + names[bottom]);

	}

}
