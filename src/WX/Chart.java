package WX;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;

/**
 * Created by justindodson on 6/7/17.
 */
public class Chart {


    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private String title;
    private String xAxisLabel;
    private String yAxisLable;

    public Chart() {

    }
    // eventually will take an Enum to pick chart type and create but using a standard SAC for now during testing.
    public Chart(String title, String xAxisLabel, String yAxisLable) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLable = yAxisLable;
        AreaChart<Number, Number> stackedAreaChart = new AreaChart<Number, Number>(this.xAxis, this.yAxis);
        stackedAreaChart.setTitle(this.title);
        xAxis.setLabel(this.xAxisLabel);
        yAxis.setLabel(this.yAxisLable);
    }

}
