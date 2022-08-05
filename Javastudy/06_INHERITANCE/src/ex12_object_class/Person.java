package ex12_object_class;

public class Person {

	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void eat() {
		System.out.println("먹는다");
	}
	
	// ex12_object_class.Person@7637f22
	// 출력 바꿔주기위해 override
	@Override
	public String toString() {
		return "이름 : " + name;	// System.out.println(person); 에서 사용됨
	}
	
	@Override
	public boolean equals(Object anObject) {	//p1.equals(p2)에서 사용됨
		Person p = (Person) anObject;
		return name.equals(p.name);
	}//        p1            p2 	p1과 p2의 name을 비교하는 것
	
}
