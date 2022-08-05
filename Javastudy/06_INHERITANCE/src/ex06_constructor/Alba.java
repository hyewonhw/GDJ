package ex06_constructor;

public class Alba extends Student {	// extends Student만 잘 적으면 생성자 자동생성 가능

	private String company;
	
	public Alba(String name, String school, String company) {
		super(name, school);
		this.company = company;	// 이 두개 순서 바뀌면 오류남 항상 슈퍼(부모)가 먼저 
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
