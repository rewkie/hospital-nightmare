
public abstract class Employee {

	
	//CLASS ABSTRACTED. TESTS FAIL
	private String empID;
	private String empName;
	private boolean isAvailable;
	public Employee (String empID, String empName, boolean isAvailable) {
		
		this.empID = empID;
		this.empName = empName;
		this.isAvailable = isAvailable;
	}


	public String getEmpID() {
		
		return empID;
	}


	public String getEmpName() {
		return empName;
	}


	public boolean getIsAvailable() {
		
				return isAvailable;
	}


	public void notBusy() {
		isAvailable = true;
		
	}

	public void busy() {
		isAvailable = false;
		
	}

	
	
	
	
	
}
