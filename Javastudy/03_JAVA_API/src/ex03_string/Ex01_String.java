package ex03_string;

public class Ex01_String {

	public static void main(String[] args) {
		
		String str1 = "hello";
		String str2 = "hello";
		
		/*	
		  	str1 0x123
		 	str2 0x123
		 
		 		 hello 0x123
		 */	
		
		System.out.println(str1 == str2);
		
		String str3 = new String("hi");
		String str4 = new String("hi");
		
		/*
		  str3	0x123
		  str4	0x456
		  
		  	 	hi	 0x123             //123번지에 저장된 hi와
		   		hi	 0x456             // 456번지에 저장된 hi가 같은지를 비교해야됨
		 */
		
		System.out.println(str3 == str4); // 문자열을 비교할 때는 == 사용하면 안됨 (이건 주소가 같은지를 비교하는거임)
		
		// 1. 문자열 동등 비교
		boolean result1 = str1.equals(str2);
		boolean result2 = str4.equals(str4);
		
		System.out.println(result1);
		System.out.println(result2);
		
		if(str1.equals(str2)) {
			System.out.println("str1, str2는 같아요");
		} else {
			System.out.println("str1, str2는 달라요");
		}
		
		if(!str3.equals(str4)) {
			System.out.println("str3, str4는 달라요");
		} else {
			System.out.println("str3, atr4는 같아요");
		}
		
		// 2. 대소문자를 무시한 문자열 동등 비교
		String str5 = "Hello World";
		String str6 = "hELLO wORLD";
		
		// System.out.println(str5.equals(str6));  // 한글자만 달라도 다른문자열, equals 사용하면 안됨 
		
		boolean result3 = str5.equalsIgnoreCase(str6);  // Upper Case / Lower case 무시하고 비교해라
		System.out.println(result3);
		
		
		// 3. 문자열 길이 반환
		String name = "조혜원";
		int length = name.length();
		
		System.out.println("글자수 : " + length);
		
		// 4. 특정 위치의 문자(char)만 반환
		// 특정 위치
		// 인덱스(Index)라고 함
		// 글자마다 부여된 정수값
		// 0으로 시작
		System.out.println(name.charAt(0));
		System.out.println(name.charAt(1));
		System.out.println(name.charAt(2));
		
		// 5. 문자열의 일부 문자열(String)을 반환
		// 1) substring(begin) : 인덱스 begin부터 끝까지 반환, 이때 begin도 포함됨
		// 2) substring(begin, end) : 인덱스 begin부터 인덱스 end까지 반환, end는 포함안됨
		System.out.println(name.substring(0, 1));
		System.out.println(name.substring(1));
		
		// 6. 특정 문자열을 찾아서 해당 인덱스(int)를 반환
		//    1) indexOf
		// 		(1) 발견된 첫 번째 문자열의 인덱스를 반환
		//		(2) 발견된 문자열이 없는 경우 -1을 반환
		//	  2) lastIndexOf
		//		(1) 발견된 마지막 문자열의 인덱스를 반환
		// 		(2) 발견된 문자열이 없는 경우 -1을 반환
		int idx1 = name.indexOf("조");
		int idx2 = name.indexOf("혜원");
		int idx3 = name.lastIndexOf("조");
		int idx4 = name.lastIndexOf("혜원");
		
		System.out.println(idx1);
		System.out.println(idx2);
		System.out.println(idx3);
		System.out.println(idx4);
		
		// 7. 문자열이 특정 패턴으로 시작하는지 boolean(true, false) 반환
		//    startWith(문자열)
		if(name.startsWith("조")) {
			System.out.println("조씨입니다.");
		} else { 
			System.out.println("조씨가 아닙니다.");
		}
		
		// 8. 문자열이 특정 패턴으로 끝나는지 여부를 boolean(true, false) 반환
		//    endWith(문자열)
		String filename = "image.jpg"; // jpg, png로 끝나면 이미지로 가정
		
		if(filename.endsWith("jpg") || filename.endsWith("png")){
			System.out.println("이미지입니다.");
		} else {
			System.out.println("이미지가 아닙니다.");
		}
		
		// 9. 문자열이 특정 패턴을 포함하는지 여부를 boolean(true, false) 반환
		String email = "hwcho1205@naver.com";
		if(email.contains("@") && email.contains(".")) {
			System.out.println("이메일입니다.");
		} else {
			System.out.println("이메일이 아닙니다.");
		}
		
		// 10. 불필요한 공백 제거(좌우 공백)  // 중간에 공백은 제거 안됨
		String message = "  안녕  하세요  ";
		System.out.println(message.trim());
		System.out.println(message.trim().length());
		
		// 11. 대소문자 변환하기
		// toUpperCase() : 대문자로 변환하기
		// toLowerCase() : 소문자로 변환하기
		String source = "best of best";
		System.out.println(source.toUpperCase());     // source가 변하는 것은 아님
		System.out.println(source.toLowerCase());
		
		// 12. 찾아 바꾸기
		// replace(old, new) : old를 new로 변환하기
		String replaced = source.replace("best", "worst");
		System.out.println(source);
		System.out.println(replaced);
		
		// 13. 빈 문자열인지 여부를 검사한 뒤 boolean(true, false) 반환
		String id = " ";
		if(id.trim().isEmpty()) { 
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열 아님");
		}
		
		//if(id.isBlank()) {                               // 자바 11버전에서 나왔기 때문에 안됨
		//		System.out.println("빈 문자열");
		//} else {
		//		System.out.println("빈 문자열 아님");
		//}
		
		// 연습 01 . 파일이름을 파일명과 확장자로 분리하시오
		// 단, jpg, git, png 이미지인 경우에만 작업을 진행한다.
		String fullName = "apple.jpg";
		String fileName = "";// 여기에 애플 저장되길 기대
		String extName = "";  // jpg
		
		int idxOfDot = fullName.lastIndexOf(".");
		fileName = fullName.substring(0,idxOfDot);
		extName = fullName.substring(idxOfDot + 1);
		System.out.println(fileName);
		System.out.println(extName);
		
		/*	
		 *  내가 쓴 답
		 *  if(fileName.endsWith("jpg") || fileName.endsWith("png") || fileName.endsWith("git")){
		 *	System.out.println("apple");
		 *	} else { System.out.println("");
		 *	} 
		 */ 
			
		
		
		
		// 연습 02 . 문자열 "abc12345def67890ghijk"에서 
		// 아라비아숫자 1234567890을 제외하고 한 글자씩 화면에 출력하시오.
		String str = "abc12345def67890ghijk";
		
		//for(int i = 0; i <= str.length() - 1; i++) {
		//		System.out.println(str.charAt(i));
		//}
		       											// 위, 아래 동일한 코드임 str에 길이 넣으면됨
		//for(int i = 0; i < str.length(); i++) {
		//		System.out.println(str.charAt(i));            
		//}
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch >= '0' && ch <= '9') { 
				continue;
			}	
			System.out.println(ch);
		}  
		
		
		/*
		 * 내가 쓴 답
		 * System.out.println(str.charAt(0));
		 * System.out.println(str.charAt(1));
		 * System.out.println(str.charAt(2));
		 * System.out.println(str.charAt(8));
		 * System.out.println(str.charAt(9));
		 * System.out.println(str.charAt(10));
		 * System.out.println(str.charAt(16));
		 * System.out.println(str.charAt(17));
		 * System.out.println(str.charAt(18));
		 * System.out.println(str.charAt(19));
		 * System.out.println(str.charAt(20));
		 */
		
		
		
		
		
		
		
		
		
	}

}
