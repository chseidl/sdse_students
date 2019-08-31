package edu.sdse.javafx.chart;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ChartExample extends Application {

	// Fruit import data for pie chart
	private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
			new PieChart.Data("Grapefruit", 13),
			new PieChart.Data("Oranges", 25),
			new PieChart.Data("Plums", 10),
			new PieChart.Data("Pears", 22),
			new PieChart.Data("Apples", 30));

	// Temperature data for area chart: {{x},{y}}
	private int[][] aprilTemperatures = {
			{  1,  3,  6,  9, 12, 15, 18, 21, 24, 27, 30 },
			{  4, 10, 15,  8,  5, 18, 15, 13, 19, 21, 21 } };
	private int[][] mayTemperatures = {
			{  1,  2,  6,  9, 12, 15, 18, 21, 24, 27, 31 },
			{ 20, 15, 13, 12, 14, 18, 25, 25, 23, 26, 26 } };

	// TODO: Declare a list of city records for city area chart.

	
	@Override
	public void start(Stage stage) throws IOException {
		readCSVFile();
		createAndShowCharts(stage);
	}
	
	private void createAndShowCharts(Stage stage) {
		//Create all charts
        // TODO: Create pieChart, areaChart, cityAreaChart and barChart.
		// TODO: Add all charts to the scene.
        // TODO: Fix window so that all chart information is visible.

		// Show multiple charts at once
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root, 1000, 900);
		stage.setScene(scene);
		stage.setTitle("My amazing charts");
		stage.show();
	}

	private void readCSVFile() throws IOException {
		// TODO: Input your CSV file reader method body here.
		// TODO: Clean up data if necessary.
        // TODO: Create a new city record for each line and add it to the list of city records.
		// TODO: Print the city record to the console as feedback.
	}

	public Chart createPieChart() {
		//Create the chart (with pre-formatted data)
		PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Imported Fruits");
		return chart;
	}

	public Chart createAreaChart() {
		//Create chart
		NumberAxis xAxis = new NumberAxis(1, 31, 1);
		NumberAxis yAxis = new NumberAxis();

		AreaChart<Number, Number> chart = new AreaChart<Number, Number>(xAxis, yAxis);
		chart.setTitle("Temperature Monitoring (Deg. C)");

		//Format data for chart
		XYChart.Series<Number, Number> seriesApril = new XYChart.Series<>();
		seriesApril.setName("April");
		
		XYChart.Series<Number, Number> seriesMay = new XYChart.Series<>();
		seriesMay.setName("May");

		// TODO: Add aprilTemperature and mayTemperature data to their respective series.

		
		//Add data to chart
		List<XYChart.Series<Number, Number>> chartData = chart.getData();
		chartData.add(seriesApril);
		chartData.add(seriesMay);
		
		return chart;
	}

	public Chart createBarChart() {
		//Create chart
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> chart = new BarChart<String, Number>(xAxis, yAxis);
		
		
		//Format data for chart
		XYChart.Series<String, Number> seriesImports2010 = new XYChart.Series<>();
		seriesImports2010.setName("Fruit imports 2010");
		
		List<XYChart.Data<String, Number>> dataImports2010 = seriesImports2010.getData();
		dataImports2010.add(new XYChart.Data<>("Grapefruit", 6));
		dataImports2010.add(new XYChart.Data<>("Oranges", 22));
		dataImports2010.add(new XYChart.Data<>("Plums", 4));
		dataImports2010.add(new XYChart.Data<>("Pears", 6));
		dataImports2010.add(new XYChart.Data<>("Apples", 10));

		
        // TODO: Create another data series called seriesImports2019 (make up your own y values).
        // TODO: Display seriesImports2019 in bar chart.

		
		//Add data to chart
		List<XYChart.Series<String, Number>> chartData = chart.getData();
		chartData.add(seriesImports2010);
		
		return chart;

	}

	public Chart createCityAreaChart() {
		//Create chart
		// TODO: Give sensible arguments to NumberAxis() below to fix axis ranges.
		// TODO: Create sensible axis labels.
		NumberAxis xAxis = new NumberAxis("Population", 2001, 2012, 2);
		NumberAxis yAxis = new NumberAxis("Year", 400000, 900000, 100000);
		AreaChart<Number, Number> chart = new AreaChart<Number, Number>(xAxis, yAxis);
		chart.setTitle("City Population");
		
		
		//Format data for chart
		XYChart.Series<Number, Number> seriesCopenhagen = new XYChart.Series<>();
		List<XYChart.Data<Number, Number>> dataCopenhagen = seriesCopenhagen.getData();
		seriesCopenhagen.setName("Copenhagen");
		
		XYChart.Series<Number, Number> seriesOslo = new XYChart.Series<>();
		List<XYChart.Data<Number, Number>> dataOslo = seriesOslo.getData();
		seriesOslo.setName("Oslo");
		
		XYChart.Series<Number, Number> seriesLisbon = new XYChart.Series<>();
		List<XYChart.Data<Number, Number>> dataLisbon = seriesLisbon.getData();
		seriesLisbon.setName("Lisbon");
		
		XYChart.Series<Number, Number> seriesAustin = new XYChart.Series<>();
		List<XYChart.Data<Number, Number>> dataAustin = seriesAustin.getData();
		seriesAustin.setName("Austin");

		// TODO: Loop through all city records.
        // TODO: Create a switch statement to add each city record object to the series of its respective city.
        // TODO: Make population the function of year (x-axis: year, y-axis: population).
		
//			switch (...) {
//				default:
//					throw new UnsupportedOperationException("Data for unknown city encountered.");
//			}

		
		//Add data to chart
		List<XYChart.Series<Number, Number>> chartData = chart.getData();
		
		chartData.add(seriesCopenhagen);
		chartData.add(seriesOslo);
		chartData.add(seriesLisbon);
		chartData.add(seriesAustin);
		
		return chart;
	}

	// Method necessary to launch window
	public static void main(String[] args) {
		launch(args);
	}
}