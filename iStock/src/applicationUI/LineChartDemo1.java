/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------
 * LineChartDemo1.java
 * -------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: LineChartDemo1.java,v 1.27 2004/05/27 09:10:42 mungady Exp $
 *
 * Changes
 * -------
 * 08-Apr-2002 : Version 1 (DG);
 * 30-May-2002 : Modified to display values on the chart (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package applicationUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a line chart using data from a
 * {@link CategoryDataset}.
 */
public class LineChartDemo1 {

	private JFreeChart chart;
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public LineChartDemo1(final String title) {
       
        final CategoryDataset dataset = createDataset();
        chart = createChart(dataset);
//        final ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(500, 270));
//        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset() {
        
        // row keys...
        final String series1 = "First";
        final String series2 = "Second";
        final String series3 = "Third";

        // column keys...
        final String type1 = "Type 1";
        final String type2 = "Type 2";
        final String type3 = "Type 3";
        final String type4 = "Type 4";
        final String type5 = "Type 5";
        final String type6 = "Type 6";
        final String type7 = "Type 7";
        final String type8 = "Type 8";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(-0.01, series1, type1);
        dataset.addValue(0.04, series1, type2);
        dataset.addValue(-0.03, series1, type3);
        dataset.addValue(0.05, series1, type4);
        dataset.addValue(0.05, series1, type5);
        dataset.addValue(-0.07, series1, type6);
        dataset.addValue(0.07, series1, type7);
        dataset.addValue(-0.08, series1, type8);

//        dataset.addValue(5.0, series2, type1);
//        dataset.addValue(7.0, series2, type2);
//        dataset.addValue(6.0, series2, type3);
//        dataset.addValue(8.0, series2, type4);
//        dataset.addValue(4.0, series2, type5);
//        dataset.addValue(4.0, series2, type6);
//        dataset.addValue(2.0, series2, type7);
//        dataset.addValue(1.0, series2, type8);
//
//        dataset.addValue(4.0, series3, type1);
//        dataset.addValue(3.0, series3, type2);
//        dataset.addValue(2.0, series3, type3);
//        dataset.addValue(3.0, series3, type4);
//        dataset.addValue(6.0, series3, type5);
//        dataset.addValue(3.0, series3, type6);
//        dataset.addValue(4.0, series3, type7);
//        dataset.addValue(3.0, series3, type8);

        return dataset;
                
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "Line Chart Demo 1",       // chart title
            "Type",                    // domain axis label
            "Range",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
    //    legend.setShapeScaleX(1.5);
      //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        
        plot.setDomainGridlinesVisible(true);
//        plot.setRangeAxis(new ValueAxis(0));
/*        
        void setDataset(CategoryDataset dataset) 数据区的2维数据表 
        void setColumnRenderingOrder(SortOrder order) 数据分类的排序方式 
        void setAxisOffset(Spacer offset) 坐标轴到数据区的间距 
        void setOrientation(PlotOrientation orientation) 数据区的方向（PlotOrientation.HORIZONTAL或PlotOrientation.VERTICAL） 
        void setDomainAxis(CategoryAxis axis) 数据区的分类轴 
        void setDomainAxisLocation(AxisLocation location) 分类轴的位置（参数常量在org.jfree.chart.axis.AxisLocation类中定义） 
        void setDomainGridlinesVisible(boolean visible) 分类轴网格是否可见 
        void setDomainGridlinePaint(Paint paint) 分类轴网格线条颜色 
        void setDomainGridlineStroke(Stroke stroke) 分类轴网格线条笔触 
        void setRangeAxis(ValueAxis axis) 数据区的数据轴 
        void setRangeAxisLocation(AxisLocation location) 数据轴的位置（参数常量在org.jfree.chart.axis.AxisLocation类中定义） 
        void setRangeGridlinesVisible(boolean visible) 数据轴网格是否可见 
        void setRangeGridlinePaint(Paint paint) 数据轴网格线条颜色 
        void setRangeGridlineStroke(Stroke stroke) 数据轴网格线条笔触 
        void setRenderer(CategoryItemRenderer renderer) 数据区的表示者（详见Renderer组） 
        void addAnnotation(CategoryAnnotation annotation) 给数据区加一个注释 
        void addRangeMarker(Marker marker,Layer layer) 给数据区加一个数值范围区域
*/

/*
  对于坐标轴，由于是要区分x轴和Y周（在不同的图表的叫法可能有所不同）。

对于x轴使用的类：CategoryAxis
CategoryAxis domainAxis = (CategoryAxis)plot.getDomainAxis();

 void setCategoryMargin(double margin) 分类轴边距 
void setLowerMargin(double margin) 分类轴下（左）边距 
void setUpperMargin(double margin) 分类轴上（右）边距 
void setVerticalCategoryLabels(boolean flag) 分类轴标题是否旋转到垂直 
void setMaxCategoryLabelWidthRatio(float ratio) 分类轴分类标签的最大宽度
 
        
对于Y的使用类：NumberAxis
NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

 void setAutoRangeIncludesZero(boolean flag) 是否强制在自动选择的数据范围中包含0
void setAutoRangeStickyZero(boolean flag) 是否强制在整个数据轴中包含0，即使0不在数据范围中 
void setNumberFormatOverride(NumberFormat formatter) 数据轴数据标签的显示格式 
void setTickUnit(NumberTickUnit unit) 数据轴的数据标签（需要将AutoTickUnitSelection设false）
 
 */
  
        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        
        //rangeAxis.setInverted(true);
        rangeAxis.setAutoRange(true);
        rangeAxis.setAutoRangeMinimumSize(0.01);
//        rangeAxis.setFixedAutoRange(0.1);
        rangeAxis.setAutoRangeIncludesZero(true);
//        rangeAxis.setLowerBound(-0.2);
//        rangeAxis.setUpperBound(0.2);
//        rangeAxis.setTickMarksVisible(true);
        rangeAxis.setTickUnit(new NumberTickUnit(0.01));//设置显示Y轴数据间隔

        // ****************************************************************************
        // * JFREECHART DEVELOPER GUIDE                                               *
        // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
        // * to purchase from Object Refinery Limited:                                *
        // *                                                                          *
        // * http://www.object-refinery.com/jfreechart/guide.html                     *
        // *                                                                          *
        // * Sales are used to provide funding for the JFreeChart project - please    * 
        // * support us so that we can continue developing free software.             *
        // ****************************************************************************
        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);
        renderer.setItemLabelsVisible(true);

//        renderer.setSeriesStroke(
//            0, new BasicStroke(
//                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//                1.0f, new float[] {10.0f, 6.0f}, 0.0f
//            )
//        );
//        renderer.setSeriesStroke(
//            1, new BasicStroke(
//                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//                1.0f, new float[] {6.0f, 6.0f}, 0.0f
//            )
//        );
//        renderer.setSeriesStroke(
//            2, new BasicStroke(
//                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//                1.0f, new float[] {2.0f, 6.0f}, 0.0f
//            )
//        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
    }
    
    
    public JFreeChart getChart(){
    	return chart;
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

//        final LineChartDemo1 demo = new LineChartDemo1("Line Chart Demo");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
        
        LineChartDemo1 chart = new LineChartDemo1("Line Chart Demo");
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setSize(600, 300);
        shell.setLayout(new FillLayout());
        shell.setText("Test for jfreechart running with SWT");
        
        final ChartComposite frame2 = new ChartComposite(shell, SWT.NONE, chart.chart,true);
        frame2.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

    }

}
