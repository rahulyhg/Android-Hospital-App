 
package type;

import java.io.Serializable;

public class Doctor implements Serializable{

	/** Generated by Eclipse. */
	private static final long serialVersionUID = -867153217061388477L;
	
	/** Username and password of the doctor. */
	private String username, password;
	
	/** The PatientDatabase object where the data is stored. */
	private PatientDataBase database;
	
	/** The patient object for the patient currently being treated. */
	private Patient patient;
	
	/**
	 * Creates a new Doctor account
	 * @param the doctor's username to log in to the triage application
	 * @param the validation key so the Doctor can log in to the program
	 */
	public Doctor(String username, String password) {
		this.username = username;
		this.password = password;
		
	}
	
	/**
	 * Assigns a Patient to a Doctor. Only one Patient can be assigned to 
	 * a Doctor at any given time.
	 * @param The Patient's health card number used to assign them to a Doctor.
	 */
	public void getCurrentPatient(String cardNumber) {
		this.patient = database.getPatientByCardNumber(cardNumber);
	}
	
	/**
	 * Returns a Patient given the health card number.
	 * @param The card number identification for a patient. 
	 * It is used to identify a patient in the database.
	 * @return A Patient with the given health card number.
	 */
	public Patient viewPatientByCard(String cardNumber) {
		return database.getPatientByCardNumber(cardNumber);
	}
	
	
	/**
	 * Returns the information of the Doctor.
	 * @return A Doctor with their login credentials
	 */
	public String toString() {
		return "Doctor" + "~" + username + "~" + password;

	}
	
	/**
	 * Returns a true or false according to the correctness of password.
	 * @param The password being entered into the system. 
	 * @return A boolean according to whether the password was correct.
	 */
	public boolean verifyPassWord(String password) {
		if (this.password.equals(password))
			return true;
		else
			return false;
	}

}
