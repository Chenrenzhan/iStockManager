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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.DrawStackedChart;
import controller.TimeSeries;

/**
 * A simple demonstration application showing how to create a stacked area chart using data from a
 * {@link CategoryDataset}.
 */
public class StackedChart {

	public static final SimpleDateFormat DF = new SimpleDateFormat("yy-MM-dd");
	public static final SimpleDateFormat NDF = new SimpleDateFormat("MM/dd");
	
	private JFreeChart chart;
	private DrawStackedChart dsc;
	
//	private List<JSONArray> jsList;
	
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedChart(String account,int type) {
    	dsc = new DrawStackedChart(account);
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
    	
    	if(dataset == null){
    		return ;
    	}
        
        chart = createChart(dataset);
    }
    
    public void update(){
    	dsc.update();
    }
    
    public CategoryDataset oneMonth(){
		List<JSONArray> jsList = dsc.oneMonth();
		String[] timeseries = TimeSeries.threeMonth();
		
//		if(jsList == null){
//			return null;
//		}
		
		return createDataset(jsList, timeseries);
    }
    
    public CategoryDataset threeMonth(){
		List<JSONArray> jsList = dsc.threeMonth();
		String[] timeseries = TimeSeries.threeMonth();
		
//		if(jsList == null){
//			return null;
//		}
		
		return createDataset(jsList, timeseries);
    }
    
    public CategoryDataset sixMonth(){
		List<JSONArray> jsList = dsc.sixMonth();
		String[] timeseries = TimeSeries.sixMonth();
		
//		if(jsList == null){
//			return null;
//		}
		
		return createDataset(jsList, timeseries);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    public CategoryDataset createDataset(
    		List<JSONArray> jsList, String[] timeseries) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
//		for(JSONArray jo : jsList){
//			System.out.println(jo.toString());
//		}
		
		if(jsList == null){
			return dataset;
		}
		
		for(int i = 0; i < timeseries.length; ++i){
			
			JSONArray ja = jsList.get(i);	
			String date = "";
			try {
				date = NDF.format(DF.parse(timeseries[i]));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ja == null){
				dataset.addValue(null, "", date);
				dataset.removeRow("");//去掉为空的
				continue;
			}
			for(int j = 0; j < ja.length(); ++j){
				int holdSum = 0;
				String name = "";
			
				try {
					holdSum = ja.getJSONObject(j).getInt("holdSum");
					name = ja.getJSONObject(j).getString("name");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				dataset.addValue(holdSum, name, date);
			}
			
		}
    	

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
//    public static void main(final String[] args) {
//    	StackedChart chart = new StackedChart(1);
//        Display display = new Display();
//        Shell shell = new Shell(display);
//        shell.setSize(600, 300);
//        shell.setLayout(new FillLayout());
//        shell.setText("Test for jfreechart running with SWT");
//        final ChartComposite frame2 = new ChartComposite(shell, SWT.NONE, chart.getChart(),true);
//        frame2.pack();
//        shell.open();
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch())
//                display.sleep();
//        }
//
//    }

}
