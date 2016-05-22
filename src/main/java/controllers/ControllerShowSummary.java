package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Trip;
import utils.DataUtils;
import utils.StatisticUtils;

public class ControllerShowSummary {
	Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private PieChart pieChart;

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private LineChart<Number, Number> lineChart;

	@FXML
	private CategoryAxis xAxisLineChart, xAxisBarChart;

	@FXML
	private NumberAxis yAxisLineChart, yAxisBarChart;

	@FXML
	private Button buttonOk;

	@FXML
	private Label numberOfTripsLabel, averageLengthLabel, averageStartHeightLabel, averageEndHeightLabel,
			averageHeightDifferenceLabel, sumOfLengthsLabel;

	@FXML
	public void handleButtonOk() {
		stage.close();
	}

	@FXML
	public void initialize() {
		long count, shortTrips, mediumTrips, longTrips;

		count = StatisticUtils.getTripsCount(DataUtils.tripList);
		shortTrips = StatisticUtils.getShortTripsCount(DataUtils.tripList);
		mediumTrips = StatisticUtils.getMediumTripsCount(DataUtils.tripList);
		longTrips = StatisticUtils.getLongTripsCount(DataUtils.tripList);
		List<Double> avgSpeeds = new ArrayList<Double>();
		List<Double> avgElevations = new ArrayList<Double>();

		// Labels

		numberOfTripsLabel.setText(StatisticUtils.getTripsCount(DataUtils.tripList).toString());
		averageLengthLabel.setText(StatisticUtils.calculateAvgOfLengths(DataUtils.tripList).toString());
		averageStartHeightLabel.setText(StatisticUtils.calculateAvgOfStartHeights(DataUtils.tripList).toString());
		averageEndHeightLabel.setText(StatisticUtils.calculateAvgOfEndHeights(DataUtils.tripList).toString());
		averageHeightDifferenceLabel
				.setText(StatisticUtils.calculateAvgOfHeightDifference(DataUtils.tripList).toString());
		sumOfLengthsLabel.setText(StatisticUtils.calculateSumOfLengths(DataUtils.tripList).toString());

		for (Trip trip : DataUtils.tripList) {
			avgSpeeds.add(trip.getAvgSpeed());
			avgElevations.add(trip.getAvgElevation());
		}
		// Pie chart
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Short trips", (double) shortTrips / count),
				new PieChart.Data("Medium trips", (double) mediumTrips / count),
				new PieChart.Data("Long trips", (double) longTrips / count));

		pieChart.dataProperty().setValue((pieChartData));

		ObservableList<String> xAxisData = FXCollections.observableArrayList();

		for (int i = 0; i < avgElevations.size(); i++) {
			xAxisData.add(String.format("%f", avgElevations.get(i)));
		}

		// Lince chart
		XYChart.Series lineChartSeries = new XYChart.Series();

		yAxisLineChart.setLabel("Avarge Speed");
		xAxisLineChart.setLabel("Avarge elevation");

		for (int i = 0; i < avgSpeeds.size(); i++) {
			lineChartSeries.getData()
					.add(new XYChart.Data(String.format("%f", avgElevations.get(i)), avgSpeeds.get(i)));
		}

		lineChart.getData().add(lineChartSeries);

		// Bar chart
		XYChart.Series barChartSeries = new XYChart.Series();

		ObservableList<String> names = FXCollections.observableArrayList();

		for (Trip trip : DataUtils.tripList) {
			if (!names.contains(trip.getName())) {
				names.add(trip.getName());
			}
		}

		int[] occurrence = new int[names.size()];

		for (int i = 0; i < names.size(); i++) {
			for (Trip trip : DataUtils.tripList) {
				if (names.get(i).equals(trip.getName())) {
					occurrence[i]++;
				}
			}
		}

		xAxisBarChart.setCategories(names);

		for (int i = 0; i < names.size(); i++) {
			barChartSeries.getData().add(new XYChart.Data<>(names.get(i), occurrence[i]));
		}

		barChart.getData().add(barChartSeries);

	}

}
