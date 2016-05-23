package model;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.CalculatorUtils;

/**
 * 
 * This Class represents a trip.
 *
 */
public class Trip {
	/**
	 * Id of the trip.
	 */
	private StringProperty id;

	/**
	 * Name of the trip.
	 */
	private StringProperty name;

	/**
	 * Start time of the trip.
	 */
	private ObjectProperty<LocalDateTime> startTime;

	/**
	 * End time of the trip.
	 */
	private ObjectProperty<LocalDateTime> endTime;

	/**
	 * Start height of the trip.
	 */
	private IntegerProperty startHeight;

	/**
	 * End height of the trip.
	 */
	private IntegerProperty endHeight;

	/**
	 * Length of the trip.
	 */
	private IntegerProperty length;

	/**
	 * Comment of the trip.
	 */
	private StringProperty comment;

	/**
	 * Duration of the trip. Calculated value. Duration equals end time minus
	 * start time.
	 */
	private ObjectProperty<Duration> duration;

	/**
	 * Height difference of the trip. Calculated value. Height difference equals
	 * the difference between the start height and the end height.
	 */
	private IntegerProperty heightDifference;

	/**
	 * Average speed of the trip. Calculated value. Average speed equals
	 * distance traveled divided by the duration of the trip.
	 */
	private DoubleProperty avgSpeed;

	/**
	 * Average Elevation of the trip. Calculated value. Average elevation equals
	 * the height difference between the start height and the end height,
	 * divided by the length of the trip.
	 */
	private DoubleProperty avgElevation;

	/**
	 * Empty constructor, creates an empty trip.
	 */
	public Trip() {
		this("", "", LocalDateTime.now(), LocalDateTime.now(), 0, 0, 0, "-");
		refresh();
	}

	/**
	 * Parameterized constructor creats a trip with the given paramaters.
	 * 
	 * @param id
	 *            is the {@code String} value which will be set as id of the new
	 *            trip.
	 * @param name
	 *            is the {@code String} value which will be set as name of the
	 *            new trip.
	 * @param startTime
	 *            is the {@code LocalDateTime} value which will be set as start
	 *            time of the new trip.
	 * @param endTime
	 *            is the {@code LocalDateTime} value which will be set as end
	 *            time of the new trip.
	 * @param startHeight
	 *            is the {@code int} value which will be set as start height of
	 *            the new trip.
	 * @param endHeight
	 *            is the {@code int} value which will be set as end height of
	 *            the new trip.
	 * @param length
	 *            is the {@code int} value which will be set as length of the
	 *            new trip.
	 * @param comment
	 *            is the {@code String} value which will be set as comment of
	 *            the new trip.
	 */
	public Trip(String id, String name, LocalDateTime startTime, LocalDateTime endTime, int startHeight, int endHeight,
			int length, String comment) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.startTime = new SimpleObjectProperty<LocalDateTime>(startTime);
		this.endTime = new SimpleObjectProperty<LocalDateTime>(endTime);
		this.startHeight = new SimpleIntegerProperty(startHeight);
		this.endHeight = new SimpleIntegerProperty(endHeight);
		this.length = new SimpleIntegerProperty(length);
		this.comment = new SimpleStringProperty(comment);

		refresh();
	}

	/**
	 * Refreshes the calculated values of the trip (duration, height difference,
	 * average speed, average elevation).
	 */
	public void refresh() {
		duration = CalculatorUtils.calculateDuration(startTime, endTime);
		heightDifference = CalculatorUtils.calculateHeightDifference(startHeight, endHeight);
		avgSpeed = CalculatorUtils.calculateAvgSpeed(duration, length);
		avgElevation = CalculatorUtils.calculateAvgElevation(startHeight, endHeight, length);
	}

	/**
	 * Getter for the trip id property.
	 * 
	 * @return the id property of the trip.
	 */
	public StringProperty getIdProperty() {
		return id;
	}

	/**
	 * Setter for the trip id property.
	 * 
	 * @param id
	 *            the {@code StringProperty} which will be set as the id
	 *            property of the trip.
	 */
	public void setIdProperty(StringProperty id) {
		this.id = id;
	}

	/**
	 * Getter for the trip name property.
	 * 
	 * @return the name property of the trip.
	 */
	public StringProperty getNameProperty() {
		return name;
	}

	/**
	 * Setter for the name property.
	 * 
	 * @param name
	 *            the {@code StringProperty} which will be set as the name
	 *            property of the trip.
	 */
	public void setNameProperty(StringProperty name) {
		this.name = name;
	}

	/**
	 * Getter for the trip start time property.
	 * 
	 * @return the start time property of the trip.
	 */
	public ObjectProperty<LocalDateTime> getStartTimeProperty() {
		return startTime;
	}

	/**
	 * Setter for the start time property.
	 * 
	 * @param startTime
	 *            {@code ObjectProperty<LocalDateTime>} which will be set as the
	 *            start time property of the trip.
	 */
	public void setStartTimeProperty(ObjectProperty<LocalDateTime> startTime) {
		this.startTime = startTime;
	}

	/**
	 * Getter for the end time property.
	 * 
	 * @return the end time property of the trip.
	 */
	public ObjectProperty<LocalDateTime> getEndTimeProperty() {
		return endTime;
	}

	/**
	 * Setter for the end time property.
	 * 
	 * @param endTime
	 *            {@code ObjectProperty<LocalDateTime>} which will be set as the
	 *            end time property of the trip.
	 */
	public void setEndTimeProperty(ObjectProperty<LocalDateTime> endTime) {
		this.endTime = endTime;
	}

	/**
	 * Getter for the start height property.
	 * 
	 * @return the start height of the trip.
	 */
	public IntegerProperty getStartHeightProperty() {
		return startHeight;
	}

	/**
	 * Setter for the start height property.
	 * 
	 * @param startHeight
	 *            {@code IntegerProperty} which will be set as the start height
	 *            property of the trip.
	 */
	public void setStartHeightProperty(IntegerProperty startHeight) {
		this.startHeight = startHeight;
	}

	/**
	 * Getter for the end height property.
	 * 
	 * @return the end height of the trip.
	 */
	public IntegerProperty getEndHeightProperty() {
		return endHeight;
	}

	/**
	 * Setter for the end height property.
	 * 
	 * @param endHeight
	 *            {@code IntegerProperty} which will be set as the end height
	 *            property of the trip.
	 */
	public void setEndHeightProperty(IntegerProperty endHeight) {
		this.endHeight = endHeight;
	}

	/**
	 * Getter for the height difference property.
	 * 
	 * @return the height difference of the trip.
	 */
	public IntegerProperty getheightDifferenceProperty() {
		return heightDifference;
	}

	/**
	 * Setter for the height difference property.
	 * 
	 * @param heightDifference
	 *            {@code Integer} which will be set as the end time property of
	 *            the trip.
	 */
	public void setHeightDifferenceProperty(IntegerProperty heightDifference) {
		this.heightDifference = heightDifference;
	}

	/**
	 * Getter for the length property.
	 * 
	 * @return the length of the trip.
	 */
	public IntegerProperty getLengthProperty() {
		return length;
	}

	/**
	 * Setter for the length property.
	 * 
	 * @param length
	 *            {@code IntegerProperty} which will be set as the length
	 *            property of the trip.
	 */
	public void setLengthProperty(IntegerProperty length) {
		this.length = length;
	}

	/**
	 * Getter for the comment property.
	 * 
	 * @return the comment of the trip.
	 */
	public StringProperty getCommentProperty() {
		return comment;
	}

	/**
	 * Setter for the comment property.
	 * 
	 * @param comment
	 *            {@code StringProperty} which will be set as the comment
	 *            property of the trip.
	 */
	public void setCommentProperty(StringProperty comment) {
		this.comment = comment;
	}

	/**
	 * Getter for the duration property.
	 * 
	 * @return the duration of the trip.
	 */
	public ObjectProperty<Duration> getDurationProperty() {
		return duration;
	}

	/**
	 * Setter for the duration property.
	 * 
	 * @param duration
	 *            {@code ObjectProperty<Duration>} which will be set as the
	 *            duration property of the trip.
	 */
	public void setDurationProperty(ObjectProperty<Duration> duration) {
		this.duration = duration;
	}

	/**
	 * Getter for average speed property.
	 * 
	 * @return average speed of the trip.
	 */
	public DoubleProperty getAvgSpeedProperty() {
		return avgSpeed;
	}

	/**
	 * Setter for the average speed property.
	 * 
	 * @param avgSpeed
	 *            {@code DoubleProperty} which will be set as the average speed
	 *            property of the trip.
	 */
	public void setAvgSpeedProperty(DoubleProperty avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	/**
	 * Getter for the average elevation property.
	 * 
	 * @return the average elevation of the trip.
	 */
	public DoubleProperty getAvgElevationProperty() {
		return avgElevation;
	}

	/**
	 * Setter for the average elevation property.
	 * 
	 * @param avgElevation
	 *            {@code DoubleProperty} which will be set as the average
	 *            elevation property of the trip.
	 */
	public void setAvgElevationProperty(DoubleProperty avgElevation) {
		this.avgElevation = avgElevation;
	}

	// Other type of setters and getters

	/**
	 * Getter for the id.
	 * 
	 * @return the id of the id.
	 */
	public String getId() {
		return id.get();
	}

	/**
	 * Setter for the id.
	 * 
	 * @param id
	 *            {@code String} which will be set as the id of the trip.
	 */
	public void setId(String id) {
		this.id.set(id);
	}

	/**
	 * Getter for the name.
	 * 
	 * @return the name of the trip.
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * Setter for the name.
	 * 
	 * @param name
	 *            {@code String} which will be set as the name of the trip.
	 */
	public void setName(String name) {
		this.name.set(name);
	}

	/**
	 * Getter for the start time.
	 * 
	 * @return the start time of the trip.
	 */
	public LocalDateTime getStartTime() {
		return startTime.get();
	}

	/**
	 * Setter for the start time.
	 * 
	 * @param startTime
	 *            {@code LocalDateTime} which will be set as the start time of
	 *            the trip.
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime.set(startTime);
	}

	/**
	 * Getter for the end time.
	 * 
	 * @return the end time of the trip.
	 */
	public LocalDateTime getEndTime() {
		return endTime.get();
	}

	/**
	 * Setter for the end time.
	 * 
	 * @param endTime
	 *            {@code LocalDateTime} which will be set as the end time of the
	 *            trip.
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime.set(endTime);
	}

	/**
	 * Getter for the start height.
	 * 
	 * @return the start height of the trip.
	 */
	public Integer getStartHeight() {
		return startHeight.get();
	}

	/**
	 * Setter for start height.
	 * 
	 * @param startHeight
	 *            {@code int} which will be set as the start height of the trip.
	 */
	public void setStartHeight(int startHeight) {
		this.startHeight.set(startHeight);
	}

	/**
	 * Getter for the end height.
	 * 
	 * @return end height of the trip.
	 */
	public Integer getEndHeight() {
		return endHeight.get();
	}

	/**
	 * Setter for the end height.
	 * 
	 * @param endHeight
	 *            {@code int} which will be set as the end height.
	 */
	public void setEndHeight(int endHeight) {
		this.endHeight.set(endHeight);
	}

	/**
	 * Getter for the length.
	 * 
	 * @return the length of the trip.
	 */
	public Integer getLength() {
		return length.get();
	}

	/**
	 * Setter for the length.
	 * 
	 * @param length
	 *            {@code int} which will be set as the end height of the trip.
	 */
	public void setLength(int length) {
		this.length.set(length);
	}

	/**
	 * Getter for the comment.
	 * 
	 * @return the comment of the trip.
	 */
	public String getComment() {
		return comment.get();
	}

	/**
	 * Setter for the comment.
	 * 
	 * @param comment
	 *            {@code String} which will be set as the comment of the trip.
	 */
	public void setComment(String comment) {
		this.comment.set(comment);
	}

	/**
	 * Getter for the average speed.
	 * 
	 * @return the average speed of the trip.
	 */
	public Double getAvgSpeed() {
		return avgSpeed.get();
	}

	/**
	 * Setter for the average speed.
	 * 
	 * @param avgSpeed
	 *            {@code Double} which will be set as the average speed of the
	 *            trip.
	 */
	public void setAvgSpeed(Double avgSpeed) {
		this.avgSpeed.set(avgSpeed);
	}

	/**
	 * Getter for the average elevation.
	 * 
	 * @return the average elevation of the trip.
	 */
	public Double getAvgElevation() {
		return avgElevation.get();
	}

	/**
	 * Setter for the average elevation.
	 * 
	 * @param avgElevation
	 *            {@code Double} which will be set as the average elevation of
	 *            the trip.
	 */
	public void setAvgElevation(Double avgElevation) {
		this.avgElevation.set(avgElevation);
	}

	/**
	 * Getter for the height difference.
	 * 
	 * @return the height difference of the trip.
	 */
	public Integer getHeightDifference() {
		return heightDifference.get();
	}

	/**
	 * Setter for the height difference.
	 * 
	 * @param heightDifference
	 *            {@code Integer} which will be set as the height difference of
	 *            the trip.
	 */
	public void setHeightDifference(Integer heightDifference) {
		this.heightDifference.set(heightDifference);
	}

	/**
	 * Getter for the duration.
	 * 
	 * @return the duration of the trip.
	 */
	public Duration getDuration() {
		return duration.get();
	}

	/**
	 * Setter for the duration.
	 * 
	 * @param duration
	 *            {@code Duration} which will be set as the duration of the
	 *            trip.
	 */
	public void setDuration(Duration duration) {
		this.duration.set(duration);
	}

}
