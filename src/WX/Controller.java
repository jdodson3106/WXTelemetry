package WX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Chart chart;

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




}
