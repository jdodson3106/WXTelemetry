package WX;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private AreaChart areaChart;
    private LineChart lineChart;

    @FXML
    private ComboBox menuComSelect;
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem closeBtn;
    @FXML
    private ComboBox chartSelection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /*
     *   populate combobox with chart types.
     */
    public void populateChartBox() {
        ArrayList<String> chartList = new ArrayList<String>();
        chartList.add("Area Chart");
        chartList.add("Line Chart");
        chartList.add("Ass Chart");

        ObservableList list = FXCollections.observableList(chartList);
        chartSelection.getItems().clear();
        chartSelection.getItems().addAll(list);
    }

    @FXML
    public void selectChartType(ActionEvent event) {
        if (chartSelection.getValue().equals("Area Chart")) {
            areaChart = createAreaChart();
            populateAreaChartData();
            borderPane.setCenter(areaChart);

        }
        else if (chartSelection.getValue().equals("Line Chart")) {
            lineChart = createLineChart();
            populateLineChartData();
            borderPane.setCenter(lineChart);

        }
        else if(chartSelection.getValue().equals("Ass Chart")) {
            showAlert();
        }

        System.out.println(chartSelection.getValue().toString());

    }

    /*
     *   Creates new area chart.
     */
    private AreaChart<Number, Number> createAreaChart() {
        AreaChart<Number, Number> areaChart = new AreaChart<Number, Number>(xAxis, yAxis);
        areaChart.setTitle("Atmospheric Data");
        return areaChart;
    }

    /*
     *   Creates new line chart.
     */
    private LineChart<Number, Number> createLineChart() {
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Atmospheric Data");
        return lineChart;
    }


    /*
     * closes the program with the close menuItem.
     */
    @FXML
    public void close(ActionEvent event) {
        System.exit(0);
    }



    /*
     * populates the chart with dummy data.
     * TODO: add chart data upon inflow of Serial data.
     */
    @FXML
    public void populateAreaChartData() {
        XYChart.Series<Number, Number> temperature = new XYChart.Series<Number, Number>();
        temperature.setName("Temperature (F)");
        temperature.getData().add(new XYChart.Data<Number, Number>(0, 90));
        temperature.getData().add(new XYChart.Data<Number, Number>(2, 99));
        temperature.getData().add(new XYChart.Data<Number, Number>(3, 100));
        temperature.getData().add(new XYChart.Data<Number, Number>(4, 105));
        temperature.getData().add(new XYChart.Data<Number, Number>(5, 97));
        temperature.getData().add(new XYChart.Data<Number, Number>(6, 92));
        temperature.getData().add(new XYChart.Data<Number, Number>(7, 91));
        temperature.getData().add(new XYChart.Data<Number, Number>(8, 88));
        temperature.getData().add(new XYChart.Data<Number, Number>(9, 82));


        XYChart.Series<Number, Number> dewPoint = new XYChart.Series<Number, Number>();
        dewPoint.setName("Dew Point");
        dewPoint.getData().add(new XYChart.Data<Number, Number>(0, 70));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(2, 72));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(3, 77));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(4, 78));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(5, 72));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(6, 71));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(7, 77));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(8, 79));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(9, 70));

        areaChart.getData().addAll(temperature, dewPoint);
    }

    @FXML
    public void populateLineChartData() {
        XYChart.Series<Number, Number> temperature = new XYChart.Series<Number, Number>();
        temperature.setName("Temperature (F)");
        temperature.getData().add(new XYChart.Data<Number, Number>(0, 90));
        temperature.getData().add(new XYChart.Data<Number, Number>(2, 99));
        temperature.getData().add(new XYChart.Data<Number, Number>(3, 100));
        temperature.getData().add(new XYChart.Data<Number, Number>(4, 105));
        temperature.getData().add(new XYChart.Data<Number, Number>(5, 97));
        temperature.getData().add(new XYChart.Data<Number, Number>(6, 92));
        temperature.getData().add(new XYChart.Data<Number, Number>(7, 91));
        temperature.getData().add(new XYChart.Data<Number, Number>(8, 88));
        temperature.getData().add(new XYChart.Data<Number, Number>(9, 82));


        XYChart.Series<Number, Number> dewPoint = new XYChart.Series<Number, Number>();
        dewPoint.setName("Dew Point");
        dewPoint.getData().add(new XYChart.Data<Number, Number>(0, 70));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(2, 72));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(3, 77));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(4, 78));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(5, 72));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(6, 71));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(7, 77));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(8, 79));
        dewPoint.getData().add(new XYChart.Data<Number, Number>(9, 70));

        lineChart.getData().addAll(temperature, dewPoint);
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - Something Went Wrong");
        alert.setResizable(true);
        alert.setContentText("Something went wrong while trying to select a chart type. Please try again.");
        alert.showAndWait();
    }

    /*
     *  add menu item for every com port available.
      */
    public void populateCommPorts() {
        SerialPort ports[] = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            menuComSelect.getItems().add(port.getSystemPortName());
        }

    }

    /*
     *  Select Comm Port from drop down
     */
    @FXML
    public void selectPort(ActionEvent event) {
        //TODO: Select serial port based on the combobox selection.
    }


}
