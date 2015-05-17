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

package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
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

import controller.DrawLineChart;
import controller.TimeSeries;

/**
 * A simple demonstration application showing how to create a line chart using data from a
 * {@link CategoryDataset}.
 */
public class LineChart {
	
	public static final SimpleDateFormat DF = new SimpleDateFormat("yy-MM-dd");
	public static final SimpleDateFormat NDF = new SimpleDateFormat("MM/dd");

	private JFreeChart chart;
	private DrawLineChart dlc;
	private CategoryDataset dataset;
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public LineChart(final int type) {
    	
    	
    	dlc = new DrawLineChart();
    	CategoryDataset dataset;
    	switch(type){
    	case 1:
    		dataset = oneMonth();
    		break;
    	case 2:
    		dataset = threeMonth();
    		break;
    	case 3:
    		dataset = sixMonth();
    		break;
    	default:
    		dataset = null;
    	}
    	
        chart = createChart(dataset);
        
        
//        Thread td = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				dlc = new DrawLineChart();
//				
//				switch(type){
//		    	case 1:
//		    		dataset = oneMonth();
//		    		break;
//		    	case 2:
//		    		dataset = threeMonth();
//		    		break;
//		    	case 3:
//		    		dataset = sixMonth();
//		    		break;
//		    	default:
//		    		dataset = null;
//		    	}
//				
//		        chart = createChart(dataset);
//			}
//		});
    }
    
    public void update(){
    	dlc.update();
    }
    
    public CategoryDataset oneMonth(){
		List<Double> list = dlc.oneMonth();
		String[] timeseries = TimeSeries.threeMonth();
		
		return createDataset(list, timeseries);
    }
    
    public CategoryDataset threeMonth(){
		List<Double> list = dlc.threeMonth();
		String[] timeseries = TimeSeries.threeMonth();
		
		return createDataset(list, timeseries);
    }
    
    public CategoryDataset sixMonth(){
		List<Double> list = dlc.sixMonth();
		String[] timeseries = TimeSeries.sixMonth();
		
		return createDataset(list, timeseries);
    }

    /**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset(
    		List<Double> list, String[] str) {
    	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	for(double d : list){
    		System.out.println("list           " + d);
    	}
    	
    	for(int i = 0; i < list.size(); ++i){
    		try {
				dataset.addValue(list.get(i), "收益率", NDF.format(DF.parse(str[i])));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
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
            null,       // chart title
            null,                    // domain axis label
            null,                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            false,                      // include legend
            true,                      // tooltips
            false                      // urls
        );


        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.lightGray);
        
        plot.setDomainGridlinesVisible(true);
        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        
        //rangeAxis.setInverted(true);
        rangeAxis.setAutoRange(true);
        rangeAxis.setAutoRangeMinimumSize(0.01);
//        rangeAxis.setFixedAutoRange(0.1);
//        rangeAxis.setAutoRangeIncludesZero(true);
//        rangeAxis.setLowerBound(-0.2);
//        rangeAxis.setUpperBound(0.2);
//        rangeAxis.setTickMarksVisible(true);
        rangeAxis.setTickUnit(new NumberTickUnit(0.1));//设置显示Y轴数据间隔

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
        
        
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setSize(600, 300);
        shell.setLayout(new FillLayout());
        shell.setText("Test for jfreechart running with SWT");
        
        LineChart chart = new LineChart(1);
        final ChartComposite frame2 = new ChartComposite(shell, SWT.NONE, chart.chart,true);
        frame2.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

    }

}
