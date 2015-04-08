package testchart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
public class LineChartDemo extends ApplicationFrame
{

    public LineChartDemo(String s)
    {
        super(s);
        JFreeChart jfreechart = createChart();
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(600, 350));
        setContentPane(chartpanel);
    }

    private static JFreeChart createChart()
    {
    
     String s = "家具产量";
        String s1 = "居民收入";        
        String s3 = "2007年1月";
        String s4 = "2007年2月";
        String s5 = "2007年3月";
        String s6 = "2007年4月";
        String s7 = "2007年5月";
        String s8 = "2007年6月";
        String s9 = "2007年7月";
        String s10 = "2007年8月";
        String s11 = "2007年9月";
        String s12 = "2007年10月";
        String s13 = "2007年11月";
        String s14 = "2007年12月";
        
        CategoryPlot categoryplot = new CategoryPlot();
        /**开始构造第一条折线*/
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        
        defaultcategorydataset.addValue(200, s, s3);
        defaultcategorydataset.addValue(210, s, s4);
        defaultcategorydataset.addValue(220, s, s5);
        defaultcategorydataset.addValue(230, s, s6);
        defaultcategorydataset.addValue(240, s, s7);
        defaultcategorydataset.addValue(250, s, s8);
        defaultcategorydataset.addValue(260, s, s9);
        defaultcategorydataset.addValue(270, s, s10);
        defaultcategorydataset.addValue(320, s, s11);
        
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        categoryplot.setDataset(0, defaultcategorydataset);
        categoryplot.setRenderer(0, lineandshaperenderer);/**到此为止，一条折线的设置完成*/
        
        categoryplot.setRangeGridlinesVisible(true);
        categoryplot.setDomainAxis(new CategoryAxis("Category"));
     categoryplot.setRangeAxis(new NumberAxis("Value"));
        categoryplot.setOrientation(PlotOrientation.VERTICAL);
        
        
        
        /**构造第一条虚线*/
//        DefaultCategoryDataset defaultcategorydataset1 = new DefaultCategoryDataset();
//        
//        defaultcategorydataset1.addValue(360, s, s12);
//        defaultcategorydataset1.addValue(400, s, s13);
//        defaultcategorydataset1.addValue(550, s, s14);
//        
//        categoryplot.setDataset(1, defaultcategorydataset1);
//                
//        LineAndShapeRenderer lineandshaperenderer1 = new LineAndShapeRenderer();        
//        lineandshaperenderer1.setBaseShapesVisible(true);/**设置折线中的端点图形是否可见*/
//        
//        lineandshaperenderer1.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {6F, 6F}, 0.0F));
//        lineandshaperenderer1.setBaseItemLabelsVisible(true);
//        
//        lineandshaperenderer1.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//        
//        categoryplot.setRenderer(1, lineandshaperenderer1);        
                
        return new JFreeChart(categoryplot);
    }

    public static void main(String args[])
    {
     LineChartDemo linechartdemo5 = new LineChartDemo("Line Chart Demo 5");
        linechartdemo5.pack();
        RefineryUtilities.centerFrameOnScreen(linechartdemo5);
        linechartdemo5.setVisible(true);
    }
} 