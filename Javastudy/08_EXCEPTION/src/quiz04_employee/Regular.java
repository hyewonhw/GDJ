package quiz04_employee;

public class Regular extends Employee {

	private int salary;

	public Regular(int empNo, String name, int salary) {
		super(empNo, name);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + "Regular [salary=" + salary + "]";			// Empoloyee의 toString이 넘어옴
	}
	
	@Override
	public int getPay() {
		return salary;
	}
	
	
	
	
	
	
	
	
}
