import java.awt.Label;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Hospital nightmare = new Hospital();
		/*
		 * Populate the hospital with patients and employees
		 */

		Patient starterPatient1 = new Patient("01", "Freddie", "[Psych Ward]");
		Patient starterPatient2 = new Patient("02", "Annabell", "[Psych Ward]");
		Patient starterPatient3 = new Patient("03", "Jason", "[Psych Ward]");

		Patient starterPatient4 = new Patient("04", "Laurie", "[Pain management]");
		Patient starterPatient5 = new Patient("05", "Michael", "[Pain management]");
		Patient starterPatient6 = new Patient("06", "Carrie", "[Pain management]");

		Employee starterDoctor1 = new Doctor("07", "Dr. Loboto", true, "[Psych Ward]");
		Employee starterDoctor2 = new Doctor("08", "Dr. Numb", true, "[Pain management]");

		Employee starterSurgeon1 = new Surgeon("09", "Dr. Cuts", false, "[Psych Ward]");
		Employee starterSurgeon2 = new Surgeon("10", "Dr. Slasher", false, "[Pain management]");

		Employee starterNurse1 = new Nurse("11", "Nurse Bates", true, "[Psych Ward]");
		Employee starterNurse2 = new Nurse("12", "Nurse Damien", true, "[Pain management]");

		Employee starterJanitor = new Janitor("13", "Zeke", true);
		Employee starterVampireJanitor = new VampireJanitor("14", "Vlad", true);

		Employee starterReceptionist = new Receptionist("15", "Pam", true);

		AllEmployees staff = new AllEmployees();

		staff.addEmployee(starterDoctor1);
		staff.addEmployee(starterDoctor2);
		staff.addEmployee(starterSurgeon1);
		staff.addEmployee(starterSurgeon2);
		staff.addEmployee(starterNurse1);
		staff.addEmployee(starterNurse2);
		staff.addEmployee(starterJanitor);
		staff.addEmployee(starterVampireJanitor);
		staff.addEmployee(starterReceptionist);

		PatientCollection admitted = new PatientCollection();

//		tickHospital(staff, admitted);

		admitted.addPatient(starterPatient1);
		admitted.addPatient(starterPatient2);
		admitted.addPatient(starterPatient3);
		admitted.addPatient(starterPatient4);
		admitted.addPatient(starterPatient5);
		admitted.addPatient(starterPatient6);
		/*
		 * End pre-population
		 */
		System.out.println("******************\n" + "NIGHTMARE HOSPITAL" + "\n******************" + "\n      v0.13"
				+ "\n------------------");
		System.out.println("\nPress Enter to start...");
		String go = input.nextLine();
		System.out.println(
				"\nYou arrive at High Street Hospital ready to take on your new role as the top Administrator....");
		go = input.nextLine();
		System.out.println(
				"The hospital needs your expertise righting the ship. You're excited for this new challenge....");
		go = input.nextLine();
		System.out.println("For some reason, some patients are getting sick and having trouble recovering...");
		go = input.nextLine();
		System.out.println("You plan to solve this mystery and save the lives of both your patients and staff.\n\n");
		System.out.println("At any time, press \"help\" to learn more or \"exit\" to quit the game");
		boolean loseCondition = false;
		boolean forfeitCondition = false;
		boolean winCondition = false;
		
		while (!winCondition && !loseCondition && !forfeitCondition) {
			nightmare.getVladFound();
			winCondition = checkForWin(nightmare, winCondition);

			/*
			 * MAIN MENU BEGINS HERE
			 */

			System.out.println(
					"In your office." + "\n-----------------" + "\n1. Get Hospital Status\n2. Review patient log.");
			String mainMenuResponse = input.nextLine();

			switch (mainMenuResponse) {

			// Main case 1
			case "1":
				System.out.println("\n" + "CURRENT PATIENT COUNT: " + "[" + admitted.getCollectionLength() + "]"
						+ "\n\nHOSPITAL STATUS: " + nightmare.getCleanHospital() + "\n-----------------"
						+ "\n\nSTAFF INFORMATION" + "\n-----------------");
				System.out.println("");
				staff.allempStatusSummary();
				System.out.println("-----------------\n");
				break;// Main menu case 1 break

			// Main case 2, Patient Summary top Menu
			case "2":
				boolean interactingWithPatientSummary = true;
				while (interactingWithPatientSummary) {
					winCondition = checkForWin(nightmare, winCondition);
					if (winCondition == true) {
						interactingWithPatientSummary = false;
					}
					System.out.println("PATIENT SUMMARY" + "\n-----------------");
					admitted.allPatientSummary();
					System.out.println("-----------------\n");

					boolean interactingWithPatientLog = true;
					while (interactingWithPatientLog) {
						winCondition = checkForWin(nightmare, winCondition);
						if (winCondition == true) {
							interactingWithPatientLog = false;
						}
						// PATIENT SUMMARY MENU START
						System.out.println("Reviewing your patient log:\n" + "\n-----------------"
								+ "\nn. Dispatch someone to treat all patients."
								+ "\nd. Dispatch someone to treat a patient in need. "
								+ "\ns. Dispatch someone perform emergency surgury on a patient."
								+ "\nw. Wait in your office and do nothing while those around you suffer.");

						String patientLogMenu = input.nextLine();
						switch (patientLogMenu) {
						case "n":
							int countOfAvailableMedicalStaff = staff.checkStaffAvailability();
							if (countOfAvailableMedicalStaff == 0) {
								System.out.println("There are no staff available.");
								System.out.println("");
								break;
							}
							System.out.println("Employee to dispatch?");
							System.out.println("");
							staff.allAvailMedical();
							String staffToGet = input.nextLine();
							Employee selectedStaff = staff.getEmployee(staffToGet);
							if (staffToGet.equals("exit")) {
								break;
							} else if (selectedStaff.getIsAvailable() == false) {
								System.out.println("That employee is not available.");
								System.out.println("");
								break;
							} else if ((selectedStaff instanceof Doctor) && (!(selectedStaff instanceof Surgeon))) {
								selectedStaff.busy();
								admitted.treatAllPatients();
								System.out.println("All patients were treated.");
								nightmare.tickHospital(staff, admitted, nightmare);
						
								if (winCondition ==true) {interactingWithPatientLog = false;};
								System.out.println("");
								break;
							} else if (selectedStaff instanceof Surgeon) {
								System.out.println(
										"Surgeons don't get paid to walk the ward." + "\nNo patients were treated.");
								nightmare.tickHospital(staff, admitted, nightmare);
								System.out.println("");
								break;
							} else if (selectedStaff instanceof Nurse) {
								selectedStaff.busy();
								String nurseWard = selectedStaff.getSpecialty();
								if (nurseWard == "[Psych Ward]") {
									admitted.treatAllPsychPatients();
									System.out.println("All Psych Ward patients were treated.");
									System.out.println("");
									nightmare.tickHospital(staff, admitted, nightmare);
									break;
								} else if (nurseWard == "[Pain Management]") {
									admitted.allPainMgmtPatientSummary();
									admitted.treatAllPainMgmtPatients();
									System.out.println("All Pain management patients were treated.");
									System.out.println("");
									nightmare.tickHospital(staff, admitted, nightmare);
									break;
								}
								break;
							}
						case "d":
							System.out.println("Which patient would you like to treat?");
							admitted.allPatientSummary();
							String patientToGet = input.nextLine();
							Patient selectedPatient = admitted.getPatient(patientToGet);
							String patientsWard = selectedPatient.getWard();
							System.out.println("Which employee should be dispatched to "
									+ selectedPatient.getPatientName() + " ?");
							staff.allAvailMedical();
							String staffToGetNext = input.nextLine();
							Employee chosenStaff = staff.getEmployee(staffToGetNext);
							if (chosenStaff instanceof Surgeon) {
								System.out.println("Choose: \n1-Infusion \n2-Medication");
								int surgeonTreatment = input.nextInt();
								switch (surgeonTreatment) {
								case 1:
									// can't figure how to infuse one patient using the surgeon's return value for
									// infuse()
								}
							}

							break;// Select Doctor Break

						case "s":
							System.out.println("Which patient needs emergency surgery?");
							break;// Select Patient Break

						case "w":
							System.out.println("You've made a selfish choice...");
							interactingWithPatientSummary = false;
							break;
						case "help":
							System.out.println("\n" + "-HELP MENU-"
									+ "\n-Press '1' to display your hospital staff's metrics and availability."
									+ "\n-Press '2' to view patients and begin patient interaction."
									+ "\n-Type 'exit' to exit the game at any time." + "\n");
							break;
						default:
							System.out.println("Stop wasting time! There are people dieing here!");
							break;

						}
						break;

					}

				}

			case "help":
				System.out.println(
						"\n" + "-HELP MENU-" + "\n-Press '1' to display your hospital staff's metrics and availability."
								+ "\n-Press '2' to view patients and begin patient interaction."
								+ "\n-Type 'exit' to exit the game at any time." + "\n");
				break;
			case "exit":
				forfeitCondition = true;
				break;// Main menu exit case

			// Main default case
			default:
				System.out.println("\nPlease try again.\n");
				break;// Main menu default break
				}
			winCondition = checkForWin(nightmare, winCondition);
		} // GameRunning Loop Ends
		if (forfeitCondition) {
			System.out.println("\nYou have resigned your duties but kept your life. "
					+ "\nPatients will suffer, and you will be haunted by their memory.");
		}
		if (loseCondition) {
			System.out.println("\nYou failed to contain whatever lurked in the hospital. "
					+ "\nYour patients and staff have all been killed under your guidance. "
					+ "\nIn your office a shadowy figure waits for you.");
		}
		if (winCondition) {
			System.out.println("\nYou drive a spike into the vampire's heart. "
					+ "\nIt's a long fight, but the vampire turns to dust. "
					+ "\nYou feel relief for the first time since ariving at High Street Hospital. "
					+ "\nThe only injury you receive is a small bite on the back of your neck.");

		}
		Label copyrightL = new Label("\u00a9");
		System.out.println("\nCredits:\n" 
		+ "Jessica Wright & Chad Collins\n"
		+ "All Rights Reserved. ©2019");
	}

	public static boolean checkForWin(Hospital nightmare, boolean winCondition) {
		if (nightmare.getVladFound() == true) {
			winCondition = true;
		}
		return winCondition;
	}
}