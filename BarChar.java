import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarChar extends ApplicationFrame implements ActionListener
{
   JButton btn;
   public BarChar(String x)
   {
	super(x);
   }
   public void BarChart_AWT( String applicationTitle , String chartTitle )
   {
      //this.super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            //X axis
         "Score",            //Y axis
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
     pack( );        
  RefineryUtilities.centerFrameOnScreen(this);        
           setVisible( true );      
   }
   private CategoryDataset createDataset( )
   {
      final String teacher1 = "J.J.";        
      final String teacher2 = "Lakshmi";        
      final String teacher3 = "Priya";        
      final String accuracy = "Accuracy";        
      final String punctuality = "Punctuality";        
      final String understanding = "Understanding";        
      final String coop = "Cooperation";        
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

      dataset.addValue( 1.0 , teacher1 , accuracy );        
      dataset.addValue( 3.0 , teacher1 , understanding );        
      dataset.addValue( 5.0 , teacher1 , punctuality ); 
      dataset.addValue( 5.0 , teacher1 , coop );           

      dataset.addValue( 5.0 , teacher2 , accuracy );        
      dataset.addValue( 6.0 , teacher2 , understanding );       
      dataset.addValue( 10.0 , teacher2 , punctuality );        
      dataset.addValue( 4.0 , teacher2 , coop );

      dataset.addValue( 4.0 , teacher3 , accuracy );        
      dataset.addValue( 2.0 , teacher3 , understanding );        
      dataset.addValue( 3.0 , teacher3 , punctuality );        
      dataset.addValue( 6.0 , teacher3 , coop );               

      return dataset; 
   }
	
   public void create()
{
	btn=new JButton("Graph-BAR");
	JFrame frm=new JFrame();
	frm.add(btn);
	frm.setVisible(true);
	btn.addActionListener(this);
}

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource().equals(btn))
	{
		BarChart_AWT("Teacher Feedback Statistics", "Overall Stats");
     	}
}

   public static void main( String[ ] args )
   {
      BarChar chart = new BarChar("ABC");
     chart.create();
	
   }
}