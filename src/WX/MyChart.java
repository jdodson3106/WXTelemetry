package WX;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;

/**
 * Created by justindodson on 6/7/17.
 */
public class MyChart extends Node {


    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private String title;
    private String xAxisLabel;
    private String yAxisLable;



    public MyChart() {

    }



    // eventually will take an Enum to pick chart type and create but using a standard SAC for now during testing.
    public MyChart(String title, String xAxisLabel, String yAxisLable, String chartType) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLable = yAxisLable;
        if (chartType.equals("Line MyChart")) {
            new LineChart<Number, Number>(xAxis, yAxis);
        } else if(chartType.equals("Area MyChart")) {
            new AreaChart<Number, Number>(xAxis, yAxis);
        } else {
            showAlert();
        }

        xAxis.setLabel(this.xAxisLabel);
        yAxis.setLabel(this.yAxisLable);
    }


    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - Something Went Wrong");
        alert.setResizable(true);
        alert.setContentText("Something went wrong while trying to select a chart type. Please try again.");
        alert.showAndWait();
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }
    @Override
    protected NGNode impl_createPeer() {
        return null;
    }
}
