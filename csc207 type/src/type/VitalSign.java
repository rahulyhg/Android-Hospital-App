package type;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VitalSign implements Serializable {

	/** Generated by Eclipse. */
	private static final long serialVersionUID = 1923367127684716859L;

	/** List of integers containing all the blood pressures of this patient. */
	private List<Integer> bloodPressure;

	/** List of doubles containing all the temperatures of this patient. */
	private List<Double> temperature;

	/** List of integers containing all the heart rates of this patient. */
	private List<Integer> heartRate;

	/** List of calendars containing all the times of vital sign updates. */
	private List<Calendar> timeStamps;

	/** A dateformat object containing the patient's arrival time. */
	private DateFormat dateFormat;

	/**
	 * Constructs an empty VitalSign object. This contains empty the arrays of
	 * different vital signs.
	 */
	public VitalSign() {
		this.bloodPressure = new ArrayList<Integer>();
		this.temperature = new ArrayList<Double>();
		this.heartRate = new ArrayList<Integer>();
		this.timeStamps = new ArrayList<Calendar>();
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return;
	}

	/**
	 * Constructs a VitalSign object for a patient with existing vital signs.
	 * 
	 * @param bloodPressure
	 *            The list of blood pressures of a patient.
	 * @param temperature
	 *            The list of temperatures of a patient.
	 * @param heartRate
	 *            The list of heart rates of a patient.
	 * @param timeStamps
	 *            The list of times of when vital signs were updated.
	 */
	public VitalSign(List<Integer> bloodPressure, List<Double> temperature,
			List<Integer> heartRate, List<Calendar> timeStamps) {
		this.bloodPressure = bloodPressure;
		this.temperature = temperature;
		this.heartRate = heartRate;
		this.timeStamps = timeStamps;
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return;
	}

	/**
	 * Adds a blood pressure value to the list of blood pressures.
	 * 
	 * @param newBloodPressure
	 *            The new blood pressure being added.
	 */
	public void addBloodPressure(Integer newBloodPressure) {
		bloodPressure.add(newBloodPressure);
		return;
	};

	/**
	 * Adds a temperature value to the list of temperatures.
	 * 
	 * @param newTemperature
	 *            The new temperature being added.
	 */
	public void addTemperature(Double newTemperature) {
		temperature.add(newTemperature);
		return;
	};;

	/**
	 * Adds a heart rate value to the list of heart rates.
	 * 
	 * @param newHeartRate
	 *            The new heart rate being added.
	 */
	public void addHeartRate(Integer newHeartRate) {
		heartRate.add(newHeartRate);
		return;
	};

	/**
	 * Adds a timestamp value to the list of timestamps.
	 * 
	 * @param newTimeStamp
	 *            The new blood pressure being added.
	 */
	public void addTimeStamp(Calendar newTimeStamp) {
		timeStamps.add(newTimeStamp);
		return;
	};

	/**
	 * Returns a List of integers that represent all blood pressure values.
	 * 
	 * @return A List of integers that represent all blood pressure values.
	 */
	public List<Integer> getAllBloodPressure() {
		return bloodPressure;
	};

	/**
	 * Returns a List of doubles that represent all temperature values.
	 * 
	 * @return A List of doubles that represent all blood pressures values.
	 */
	public List<Double> getAllTemperature() {
		return temperature;
	};

	/**
	 * Returns a List of integers that represent all heart rate values.
	 * 
	 * @return A List of integers that represent all heart rate values.
	 */
	public List<Integer> getAllHeartRate() {
		return heartRate;
	};

	public boolean isEmpty() {
		if (bloodPressure.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Returns a string representing all vital signs and their timestamps.
	 * 
	 * @return A string representing all vital signs and their timestamps.
	 */
	public String toString() {
		if (bloodPressure.isEmpty())
			return "null~null~null~null~";
		else
			return bloodPressure.toString() + "~" + temperature.toString()
					+ "~" + heartRate.toString() + "~" + timeToString() + "~";
	}

	/**
	 * Returns a string representing all timestamps when the vital signs were
	 * added.
	 * 
	 * @return A string representing all timestamps of the vital signs.
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
	 * Combines all of the vital signs into multiple lines, but into a single
	 * string and returns it.
	 * 
	 * @return A string containing all of the values but with multiple lines:
	 *         The format should look like: Blood Pressure: [List of Blood
	 *         Pressure levels] Heart Rate: [List of Heart Rate levels]
	 *         Temperature: [List of Temperature levels] Time Recorded: [List of
	 *         Time Stamps]
	 */
	public String showAllVitalSigns() {
		String combined;
		if (bloodPressure.isEmpty())
			combined = "\nThis patient doesn't have vital signs.";
		else {
			combined = "\nBlood Pressure: " + this.getAllBloodPressure()
					+ "\nHeart Rate: " + this.getAllHeartRate()
					+ "\nTemperature: " + this.getAllHeartRate()
					+ "\nTime Recorded: " + this.timeToString();
		}
		return combined;
	}
}
