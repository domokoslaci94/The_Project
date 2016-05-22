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

public class Trip {
	private StringProperty id;
	private StringProperty name;
	private ObjectProperty<LocalDateTime> startTime;
	private ObjectProperty<LocalDateTime> endTime;
	private IntegerProperty startHeight;
	private IntegerProperty endHeight;
	private IntegerProperty length;
	private StringProperty comment;
		
	private ObjectProperty<Duration> duration;
	private IntegerProperty heightDifference;
	private DoubleProperty avgSpeed;
	private DoubleProperty avgElevation;
	

	public Trip() {
		this("","",LocalDateTime.now(),LocalDateTime.now(),0,0,0,"-");
		refresh();
	}

	public Trip(String id, String name, LocalDateTime startTime,
			LocalDateTime endTime, int startHeight, int endHeight,
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
	
	public void refresh(){
		duration = CalculatorUtils.calculateDuration(startTime, endTime);
		heightDifference = CalculatorUtils.calculateHeightDifference(startHeight, endHeight);
		avgSpeed = CalculatorUtils.calculateAvgSpeed(duration, length);
		avgElevation = CalculatorUtils.calculateAvgEvulation(startHeight, endHeight, length);
	}

	public StringProperty getIdProperty() {
		return id;
	}

	public void setIdProperty(StringProperty id) {
		this.id = id;
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public void setNameProperty(StringProperty name) {
		this.name = name;
	}

	public ObjectProperty<LocalDateTime> getStartTimeProperty() {
		return startTime;
	}

	public void setStartTimeProperty(ObjectProperty<LocalDateTime> startTime) {
		this.startTime = startTime;
	}

	public ObjectProperty<LocalDateTime> getEndTimeProperty() {
		return endTime;
	}

	public void setEndTimeProperty(ObjectProperty<LocalDateTime> endTime) {
		this.endTime = endTime;
	}

	public IntegerProperty getStartHeightProperty() {
		return startHeight;
	}

	public void setStartHeightProperty(IntegerProperty startHeight) {
		this.startHeight = startHeight;
	}

	public IntegerProperty getEndHeightProperty() {
		return endHeight;
	}

	public void setEndHeightProperty(IntegerProperty endHeight) {
		this.endHeight = endHeight;
	}
	
	public IntegerProperty getheightDifferenceProperty() {
		return heightDifference;
	}

	public void setHeightDifferenceProperty(IntegerProperty heightDifference) {
		this.heightDifference = heightDifference;
	}

	public IntegerProperty getLengthProperty() {
		return length;
	}

	public void setLengthProperty(IntegerProperty length) {
		this.length = length;
	}

	public StringProperty getCommentProperty() {
		return comment;
	}

	public void setCommentProperty(StringProperty comment) {
		this.comment = comment;
	}
	
	public ObjectProperty<Duration> getDurationProperty() {
		return duration;
	}

	public void setDurationProperty(ObjectProperty<Duration> duration) {
		this.duration = duration;
	}

	public DoubleProperty getAvgSpeedProperty() {
		return avgSpeed;
	}

	public void setAvgSpeedProperty(DoubleProperty avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public DoubleProperty getAvgElevationProperty() {
		return avgElevation;
	}

	public void setAvgElevationProperty(DoubleProperty avgElevation) {
		this.avgElevation = avgElevation;
	}
	
	// Other type of setters and getters

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public LocalDateTime getStartTime() {
		return startTime.get();
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime.set(startTime);
	}

	public LocalDateTime getEndTime() {
		return endTime.get();
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime.set(endTime);
	}

	public Integer getStartHeight() {
		return startHeight.get();
	}

	public void setStartHeight(int startHeight) {
		this.startHeight.set(startHeight);
	}

	public Integer getEndHeight() {
		return endHeight.get();
	}

	public void setEndHeight(int endHeight) {
		this.endHeight.set(endHeight);
	}

	public Integer getLength() {
		return length.get();
	}

	public void setLength(int length) {
		this.length.set(length);
	}

	public String getComment() {
		return comment.get();
	}

	public void setComment(String comment) {
		this.comment.set(comment);
	}
	
	public Double getAvgSpeed() {
		return avgSpeed.get();
	}

	public void setAvgSpeed(Double avgSpeed) {
		this.avgSpeed.set(avgSpeed);
	}

	public Double getAvgElevation() {
		return avgElevation.get();
	}

	public void setAvgElevation(Double avgElevation) {
		this.avgElevation.set(avgElevation);
	}
	
	public Integer getHeightDifference() {
		return heightDifference.get();
	}

	public void setHeightDifference(Integer heightDifference) {
		this.heightDifference.set(heightDifference);
	}
	
	public Duration getDuration(){
		return duration.get();
	}

	public void setDuration(Duration duration) {
		this.duration.set(duration);
	}

}
