package type;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Prescription implements Serializable {

	/** Generated by Eclipse. */
	private static final long serialVersionUID = 1923367127684716859L;

	/** List of strings containing all the prescriptions of this patient. */
	private List<String> prescriptionList;

	/** List of calendars containing all the times of prescription updates. */
	private List<Calendar> timeStamps;

	/** A dateformat object containing the patient's arrival time. */
	private DateFormat dateFormat;

	/**
	 * Constructs an empty Prescription object. This contains empty the arrays of
	 * different vital signs.
	 */
	public Prescription() {
		this.prescriptionList = new ArrayList<String>();
		this.timeStamps = new ArrayList<Calendar>();
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return;
	}

	/**
	 * Constructs a Prescription object for a patient with existing vital signs.
	 * @param prescriptions The list of prescriptions of a patient.
	 * @param timeStamps The list of times of when the prescriptions were updated.
	 */
	public Prescription(List<String> prescriptions, List<Calendar> timeStamps) {
		this.prescriptionList = prescriptions;

		this.timeStamps = timeStamps;
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return;
	}

	/**
	 * Adds a prescription to the list of prescriptions.
	 * @param newPrescription The new prescription being added.
	 */
	public void addPrescription(String newPrescription) {
		prescriptionList.add(newPrescription);
		return;
	};

	/**
	 * Adds a timestamp value to the list of timestamps.
	 * @param newTimeStamp The new timestamp being added.
	 */
	public void addTimeStamp(Calendar newTimeStamp) {
		timeStamps.add(newTimeStamp);
		return;
	};

	/**
	 * Returns a list of all the prescriptions.
	 * @return A list of all prescriptions in the form of strings.
	 */
	public List<String> getPrescriptionList() {
		return prescriptionList;
	}

	/**
	 * Returns a string representing all prescriptions and their timestamps.
	 * @return A string representing all prescriptions and their timestamps.
	 */
	public String toString() {
		if (prescriptionList.isEmpty())
			return "null~null";
		else
			return prescriptionList.toString() + "~" + timeToString();
	}

	/**
	 * Returns a string representing all timestamps when the prescriptions were
	 * added.
	 * @return A string representing all timestamps of the prescriptions.
	 */
	public String timeToString() {
		String timeString = "[";
		for (Calendar timeStamp : timeStamps)
			timeString += dateFormat.format(timeStamp.getTime()) + ", ";

		timeString = timeString.substring(0, timeString.length() - 2);
		timeString += "]";
		return timeString;
	}

	/**
	 * Combines the Prescription and the time it was recorded into a single
	 * string but with multiple lines and returns it
	 * 
	 * @return A string that combines the many Prescriptions and time it was
	 *         recorded The format should look like: Prescription: [List of
	 *         Prescription] Time Recorded: [Time Stamp]
	 */
	public String showPrescriptionList() {
		String combined;
		if (this.prescriptionList.isEmpty())
			combined = "\nThis patient doesn't have any prescription yet.";
		else
			combined = "\nPrescription: " + this.getPrescriptionList()
					+ "\nPrescription Times Recorded: " + this.timeToString();
		return combined;
	}

	// public static void main(String[] args) {
	// Patient p1;
	// Patient p2;
	// p1 = new Patient("Eric", "93", "1234", Calendar.getInstance());
	// p2 = new Patient("Lin", "93", "1235", Calendar.getInstance());
	//
	// System.out.println(p1.toString());
	// System.out.println(p2.toString());
	// p1.setVitalSign(90, 90.0, 91);
	// }
}