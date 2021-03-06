package type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PatientDataBase implements Serializable {

	/** Generated by Eclipse. */
	private static final long serialVersionUID = -1245092197314563777L;

	/** A map that acts as the patient database. */
	private HashMap<String, Patient> patientMap;
	
	/** A list of patients ranked by urgency. */
	private List<Patient> patientListByUrgency;
	
	/** Lists of patients for each urgency category. */
	private List<Patient> nonUrgent, lessUrgent, urgent;

	/**
	 * Constructs a PatientDatabase reading from a file dir with filename
	 * fileName and throws an IOException if the file isn't found.
	 * @param dir The file being read that contains all persistent data.
	 * @param fileName The name of the file being read.
	 */
	public PatientDataBase(File dir, String fileName) throws IOException {

		patientMap = new HashMap<String, Patient>();
		patientListByUrgency = new ArrayList<Patient>();
		File file = new File(dir, fileName);
		if (file.exists()) { // If the file exists, read from it
			this.readFromFile(file.getPath());
		} else
			// If the file doesn't exist, create it
			file.createNewFile();
	}

	/**
	 * Adds a new patient object to the patient database, where the key is the
	 * health card number of the patient and value is the actual patient object
	 * @param cardNumber The health card number of the patient.
	 * @param patient The patient object being added to the database.
	 */
	public void addNewPatient(String cardNumber, Patient patient) {
		patientMap.put(cardNumber, patient);
		// patientMapByUrgency.put(patient.getUrgency(), patient);
		// System.out.println(patientMapByUrgency.size());

	}

	/**
	 * Saves patient info to a text file so it persists.
	 * @param outputStream the output stream to write the records to.
	 */
	// modified from lecture notes.
	public void saveToFile(FileOutputStream outputStream) {
		try {
			Iterator<Patient> it = patientMap.values().iterator();
			// write record info one per line into outputStream
			while (it.hasNext()) {
				Patient currentPatient = it.next();
				if (currentPatient != null)
					outputStream.write((currentPatient.toString() + "\n")
							.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads patient data from a file and adds the patient objects into the
	 * database.
	 * @param filePath The name of the file.
	 */
	// modified from lecture notes.
	public void readFromFile(String filePath) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		String[] record;
		while (scanner.hasNextLine()) {
			// Splits the data from the text file reading it and converting it
			// to patient objects
			record = scanner.nextLine().split("~");
			Patient newPatient = Patient.scan(record);
			String cardNumber = record[2];
			patientMap.put(cardNumber, newPatient);
			System.out.println("new added!!!!!!!!!!!!" + newPatient.getName());
		}
		scanner.close();
	}

	/**
	 * Lookup a patient object in the database by their health card number.
	 * @param cardNumber The health card number of the patient.
	 * @return A patient object corresponding to the health card number.
	 */
	public Patient getPatientByCardNumber(String cardNumber) {
		return patientMap.get(cardNumber);
	}

	/**
	 * Returns a list of patients categorized by urgency.
	 * @return A list of patients categorized by urgency.
	 */
	public List<Patient> getPatientByUrgency() {

		// Only selects Patients based on if they have visited the Doctor.
		// If they have been seen by the Doctor, their name will not appear.
		nonUrgent = new ArrayList<Patient>();
		lessUrgent = new ArrayList<Patient>();
		urgent = new ArrayList<Patient>();

		Iterator<Patient> it = patientMap.values().iterator();
		while (it.hasNext()) {
			// if the value is not empty
			Patient currentPatient = it.next();
			if (currentPatient != null && !(currentPatient.isSeenByDoctor())) {
				String urgency = currentPatient.getUrgency();
				if (urgency.equals("Non Urgent")) {
					nonUrgent.add(currentPatient);
				} // currentPatient.getUrgency returns a String

				if (urgency.equals("Less Urgent")) {
					lessUrgent.add(currentPatient);
				}

				if (urgency.equals("Urgent")) {
					urgent.add(currentPatient);
				}
			}
		}
		
		patientListByUrgency.addAll(patientListByUrgency.size(), urgent);
		patientListByUrgency.addAll(patientListByUrgency.size(), lessUrgent);
		patientListByUrgency.addAll(patientListByUrgency.size(), nonUrgent);
		return patientListByUrgency;
		// Add the respective lists to the string name
		// ArrayList<Patient> patientList = new ArrayList<Patient>();
		// Iterator<List<String>> it = patientMapByUrgency.values().iterator();
		// while (it.hasNext()) {
		// // if the value is not empty
		// Patient current = it.next();
		// // go to the next value
		// patientList.add(current);
		// }
		// return patientList;
	}

	// public static void main(String[] args) {
	// File file = new File("/h/u13/c3/00/c3linhai/csc207/");
	// File file2 = new File("/h/u13/c3/00/c3linhai/csc207/PatientRecord2.txt");
	// try {
	// PatientDataBase db = new PatientDataBase(file, "PatientRecord.txt");
	// Patient p1 = new Patient("Eric", "042593", "1234",
	// Calendar.getInstance());
	// Patient p2 = new Patient("Lin", "042593", "1235",
	// Calendar.getInstance());
	// Patient p3 = new Patient("Ignacio", "120294", "1236",
	// Calendar.getInstance());
	// Patient p4 = new Patient("Joseph", "090793", "1237",
	// Calendar.getInstance());
	// // System.out.println(p1.toString());
	// // System.out.println(p2.toString());
	// p1.setPrescription("asp0");
	// p1.setVitalSign(90, 90.0, 91);
	// p2.setVitalSign(91, 90.0, 91);
	// p2.setVitalSign(92, 90.0, 91);
	// p3.setVitalSign(94, 91.0, 93);
	// p1.setVitalSign(89, 87.0, 86);
	// // System.out.println(p1.toString());
	// p1.setPrescription("asprin1");
	// p1.setPrescription("asprin2");
	// p2.setPrescription("asprin1");
	// p3.setPrescription("asprin22");
	// // db.addNewPatient("1234", p1);
	// // db.addNewPatient("1235", p2);
	// // db.addNewPatient("1236", p3);
	// // db.addNewPatient("1237", p4) ;
	// //
	// // System.out.println(p1.getUrgency());
	// // System.out.println(p2.getUrgency());
	// // System.out.println(p3.getUrgency());
	// // System.out.println(p4.getUrgency());
	//
	// FileOutputStream outputStream = new FileOutputStream(file2);
	//
	// Patient patientRequested = db.getPatientByCardNumber("1234");
	// Patient patientRequested2 = db.getPatientByCardNumber("1235");
	// Patient patientRequested3 = db.getPatientByCardNumber("1236");
	// Patient patientRequested4 = db.getPatientByCardNumber("1237");
	// System.out.println(patientRequested.toString());
	// System.out.println(patientRequested2.toString());
	// System.out.println(patientRequested3.toString());
	// System.out.println(patientRequested4.toString());
	// List<Patient> urgencyList = db.getPatientByUrgency();
	//
	// System.out.println("Get it! 0");
	// if (patientRequested != null)
	// System.out.println("Get patient1");
	// if (patientRequested2 != null)
	// System.out.println("Get patient2");
	// // Iterator<Patient> it = urgencyList.iterator();
	// // while (it.hasNext()) {
	// // Patient curp = it.next();
	// // System.out.println(curp.infoDetails());
	// // System.out.println(curp.vitalSignsInfo());
	// // System.out.println(curp.prescriptionInfo());
	// // }
	// // System.out.println(urgencyList.get("Less Urgent List"));
	// // System.out.println(urgencyList.get("Urgent List"));
	// // System.out.println("urgency---------"
	// // +urgencyList.toString()+"---------urgency\n");
	// // if (patientRequested2 != null)
	// // System.out.println("Get patient2");
	// db.saveToFile(outputStream);
	// return;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return;
	// }

}
