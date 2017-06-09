package WX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Chart chart;

    @FXML
    private AreaChart<Number, Number> dataChart;

    @FXML
    private MenuItem closeBtn;

    @FXML
    private ComboBox chartSelection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    // populate combobox with chart types.
    public void populateChartBox() {
        ArrayList<String> chartList = new ArrayList<String>();
        chartList.add("Stacked Area Chart");
        chartList.add("Stacked Line Chart");
        chartList.add("Area Chart");
        chartList.add("Line Chart");

        ObservableList list = FXCollections.observableList(chartList);
        chartSelection.getItems().clear();
        chartSelection.getItems().addAll(list);
    }


    // closes the program with the close menuItem.
    @FXML
    public void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void populateChartData() {
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

        dataChart.getData().addAll(temperature, dewPoint);
    }



}
