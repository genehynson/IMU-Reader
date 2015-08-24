import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMUGraph extends ApplicationFrame {

    /** The time series data. */
    private TimeSeries xSeries;
    private TimeSeries ySeries;
    private TimeSeries zSeries;

    /**
     * Constructs a new demonstration application.
     *
     */
    public IMUGraph() {

        super("IMU Plot Data");
        this.xSeries = new TimeSeries("X", Millisecond.class);
        this.ySeries = new TimeSeries("Y", Millisecond.class);
        this.zSeries = new TimeSeries("Z", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(xSeries);
        dataset.addSeries(ySeries);
        dataset.addSeries(zSeries);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        chartPanel.setPreferredSize(new Dimension(700, 470));
        setContentPane(content);
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "IMU Plot Graph",
                "Time",
                "Value",
                dataset,
                true,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(-20.0, 20.0);
        return result;
    }

    /**
     * Call this to add new data to the dynamic graph
     * @param x data for x line
     * @param y data for y line
     * @param z data for z line
     */
    public void addNewData(double x, double y, double z) {
        this.xSeries.add(new Millisecond(), x);
        this.ySeries.add(new Millisecond(), y);
        this.zSeries.add(new Millisecond(), z);

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final IMUGraph imuGraph = new IMUGraph();
        imuGraph.pack();
        RefineryUtilities.centerFrameOnScreen(imuGraph);
        imuGraph.setVisible(true);

    }

}
