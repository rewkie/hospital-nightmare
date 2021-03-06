import java.util.Random;

public abstract class Employee {

	private String empID;
	private String empName;
	private String empTitle;
	private boolean isAvailable;
	private int salary;

	public Employee(String empID, String empName, boolean isAvailable) {

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

	public String getEmpTitle() {
		return empTitle;
	}

	public String getSpecialty() {
		return "";
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	abstract public int calculatePay();

	public void notBusy() {
		System.out.println("--- " + this.getEmpName() + " has become available.");
		isAvailable = true;

	}

	public void busy() {
		isAvailable = false;

	}
	/*
	 * public void empTick() { tickBusy(); }
	 * 
	 * private void tickBusy() { if (this.getIsAvailable() == false) { Random h =
	 * new Random(); int maybeBusy = h.nextInt(1); if (maybeBusy == 2) {
	 * this.isAvailable = true; }
	 * 
	 * } }
	 */

	public void statusSummary() {
		System.out.println(
				"[ID:" + this.getEmpID() 
				+ "]\t" + this.getEmpName() 
				+ "\t" + this.getEmpTitle() 
				+ "\tSalary: $" + this.calculatePay() 
				+ "\t" + this.getSpecialty() 
				+ "\t" + this.busyStatus());

	}
	
	public String toString() {
		return "[" + empName + "]\t[ID: " + empID + "]\t[CURRENTLY AVAILABLE: "
+ isAvailable + "]";
	}


	public String busyStatus() {

		return "";
	}
}
