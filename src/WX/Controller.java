package WX;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable{


    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private AreaChart areaChart;
    private LineChart lineChart;
    private XYChart.Series<String, Number> temperature = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> pressure = new XYChart.Series<String, Number>();
    @FXML
    private ComboBox menuComSelect;
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem closeBtn;
    @FXML
    private ComboBox chartSelection;
    @FXML
    private Button connectButton;

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
            //populateAreaChartData();
            borderPane.setCenter(areaChart);

        }
        else if (chartSelection.getValue().equals("Line Chart")) {
            lineChart = createLineChart();
           // populateLineChartData();
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
    private AreaChart<String, Number> createAreaChart() {
        AreaChart<String, Number> areaChart = new AreaChart<String, Number>(xAxis, yAxis);
        areaChart.setTitle("Atmospheric Data");
        borderPane.setCenter(areaChart);
        return areaChart;
    }

    /*
     *   Creates new line chart.
     */
    private LineChart<String, Number> createLineChart() {
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("Atmospheric Data");
        xAxis.setLabel("Current Time");
        yAxis.setLabel("Measurements");
        borderPane.setCenter(lineChart);
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
     * populates charts with dummy data
     */
    @FXML
    public void populateAreaChartData(XYChart.Series pressureReading, XYChart.Series altitudeReading) {
        pressureReading.setName("Pressure (mBar)");
        altitudeReading.setName("Altitude");
        areaChart.getData().addAll(pressureReading, altitudeReading);
    }

    @FXML
    public void populateLineChartData(XYChart.Series pressureReading, XYChart.Series altitudeReading) {
        pressureReading.setName("Pressure (mBar)");
        altitudeReading.setName("Altitude");
        lineChart.getData().addAll(pressureReading, altitudeReading);
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
        // create a serial port object based on what port the user selects
        final SerialPort chosenPort = SerialPort.getCommPort(menuComSelect.getValue().toString());

        // Check to see if the connect button text is equal to connect when clicked.
        if(connectButton.getText().equals("Connect")) {

            // configure port timeouts.
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if (chosenPort.openPort()) {
                /*
                    *if the port opens, then change text, disable the dropdown,
                    * create new line chart, and create a new scanner to read in serial data.
                 */
                connectButton.setText("Disconnect");
                menuComSelect.setDisable(true);
                // TODO: create chart type based on user selection;
                final LineChart chart = createLineChart();
                temperature.setName("Pressure (mBar)");
                pressure.setName("Altitude");
                // Create a new thread that will listen for incoming data and populate the graph
                final Scanner inputStream = new Scanner(chosenPort.getInputStream());

                // Set up a new runnable task.
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        while (inputStream.hasNextLine() && connectButton.getText().equals("Disconnect")) {
                            // put the thread to sleep for 1 second so the chart can update in sync with the incoming data.
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // create new date and format objects to setup for the chart xAxis.
                            final Date now = new Date();
                            final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

                            // runLater to ensure the UI maintains usability while reading in serial port data on another thread
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    /*
                                        * grab the first two lines of data, and assign them to variables and then
                                        * convert them to ints and add to the two different series.
                                        * Add the series to the chart.
                                        * NOTE: this is in a try/catch because sometimes the initial data
                                        *       can be corrupt. This allows us to catch that corrupt data
                                        *       and not do anything with the exception so we can discard it
                                        *       and move on to the next lines of incoming data.
                                     */
                                    try{
                                        String line1 = inputStream.nextLine();
                                        String line2 = inputStream.nextLine();
                                        final int pressureOutput = Integer.parseInt(line1);
                                        final int altitudeOutput = Integer.parseInt(line2);
                                        temperature.getData().add(new XYChart.Data<String, Number>(formatter.format(now), pressureOutput));
                                        pressure.getData().add(new XYChart.Data<String, Number>(formatter.format(now), altitudeOutput));
                                        chart.getData().addAll(temperature, pressure);
                                    } catch (Exception e){};

                                }
                            });

                        }
                        // close the Scanner object from reading in.
                        inputStream.close();
                    }

                };
                // create new Thread, pass in the Runnable object, and then start the thread.
                new Thread(task).start();
            }
        }
        else if (connectButton.getText().equals("Disconnect")){
            // disconnect from the port
            chosenPort.closePort();
            connectButton.setText("Connect");
            menuComSelect.setDisable(false);
        }
    }


}
