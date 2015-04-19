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
 * -------------------------
 * StackedAreaChartDemo.java
 * -------------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: StackedAreaChartDemo.java,v 1.23 2004/04/29 16:24:03 mungady Exp $
 *
 * Changes
 * -------
 * 23-Sep-2002 : Version 1 (DG);
 * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package ui;

import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.experimental.chart.swt.ChartComposite;

/**
 * A simple demonstration application showing how to create a stacked area chart using data from a
 * {@link CategoryDataset}.
 */
public class DrawStackedChart {

	private JFreeChart chart;
	
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public DrawStackedChart() {

        CategoryDataset dataset = createDataset();
        chart = createChart(dataset);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    public CategoryDataset createDataset() {
        final double[][] data = new double[][] {
            {1.0, 4.0, 3.0, 5.0, 5.0, 7.0, 7.0, 8.0 },
            {5.0, 7.0, 6.0, 8.0, 4.0, 4.0, 2.0, 1.0 },
            {4.0, 3.0, 2.0, 3.0, 6.0, 3.0, 4.0, 3.0 }
        };

        final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
            "Series ", "Type ", data
        );
        return dataset;
    }
    
    public JFreeChart getChart(){
    	return chart;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A sample chart.
     */
    public JFreeChart createChart(CategoryDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createStackedAreaChart(
            null,      // chart title
            null,                // domain axis label
            null,                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,
            false
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.5f);
        plot.setBackgroundPaint(Color.white);
        //plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        
        return chart;
   
    }

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
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {
    	 System.out.println("sddsfdsfdsfdsffdsf");
    	DrawStackedChart chart = new DrawStackedChart();
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setSize(600, 300);
        shell.setLayout(new FillLayout());
        shell.setText("Test for jfreechart running with SWT");
        System.out.println("sddsfdsfdsfdsffdsf");
        final ChartComposite frame2 = new ChartComposite(shell, SWT.NONE, chart.getChart(),true);
        frame2.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

    }

}
