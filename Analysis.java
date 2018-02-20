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
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.*;


class MyTableModel extends AbstractTableModel {
    private String[] columnNames = { "Faculty Name", "Attentiveness", "Discipline","Teaching","Punctuality"};

    Analysis obj=new Analysis("ABC");
    int x;
    public MyTableModel(int y)
    {
      this.x=y;
      System.out.println(y);
      System.out.println(x);
    }

    private Object[][] data = {
        { "Ameya Biwalkar", 4, 5,5,5}};
        /*{ "Alison", "Huml", "Rowing" },
        { "Kathy", "Walrath", "Knitting"},
        { "Sharon", "Zakhour", "Speed reading"},
        { "Philip", "Milne", "Pool"} };*/



   public Object getValueAt(int row, int col) {
      return data[row][col];
    }

   public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

}

public class Analysis extends ApplicationFrame implements ActionListener
{
   JButton btn;
   int count=4;

   JFrame firstFrame,secondFrameA,secondFrameB,thirdFrame;
   JPanel pnlYear,pnltxt1,pnlDiv,pnlBtn;
   JLabel lblYear,lbltext1,lblDiv,lblSubject,lblTeacher,lblTeachCompare;
   JComboBox cbxYear,cbxDiv,cbxSubject,cbxTeacher;
   JButton btnSingle,btnAll,btnGraphA,btnTextAna,btnShow;
   JRadioButton rbLectures,rbPracticals;
   ButtonGroup rbTypeGroup;
   JCheckBox checkTeach1,checkTeach2,checkTeach3,checkTeach4; 
   String[] strYear={"--Select--","FE","SE","TE","BE"};
   String[] strDiv={"--Select--","A","B"};
   String[] strSubject={"--Select--","AOA","CG","COA","DBMS","Maths","TCS"};
   String[] strTeacher={"--Select--","Lakshmi Kurup","Ruhina Karani","Harish Narula","Pranit Bari"};
   //String[] showTeacher;
   ArrayList<String> showTeacher = new ArrayList<String>();
   String year="",div="",subj="",singleTeacher="";
   ResultSet rs;
   Connection con;
   Statement stmt;
   Boolean boolLec=false,boolPrac=false,boolSingle=false,boolAll=false;

   public Analysis(String x)
   {
	super(x);
   }

   public void connectionmake()
   {
      try{
      Class.forName("com.mysql.jdbc.Driver");  
      con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12163906","sql12163906","mxaCU2UQWe");  
      stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
      System.out.println("Connected");
      }catch(Exception se){System.out.println(se);}
   }

   public String value(int x)
   {
      String s="";
      int sum1=0,sum2=0,sum3=0,sum4=0,count=0;
      double avg1=0,avg2=0,avg3=0,avg4=0;
      System.out.println("Year"+year);
      try{
         String sql="select * from krupashirke where class='"+year+"' and divison='"+div+"' and teacher_name='"+singleTeacher+"' and practical=1;";
              System.out.println(sql);
               rs=stmt.executeQuery(sql);
               rs.first();
         count++;
         sum1+=rs.getInt(4);
         sum2+=rs.getInt(5);
         sum3+=rs.getInt(6);
         sum4+=rs.getInt(7);
         while(rs.next())
      {
         count++;
         sum1+=rs.getInt(4);
         sum2+=rs.getInt(5);
         sum3+=rs.getInt(6);
         sum4+=rs.getInt(7);
      }  
      
      
      avg1=1.0*(sum1/count);
      avg2=1.0*(sum2/count);
      avg3=1.0*(sum3/count);
      avg4=1.0*(sum4/count);
      System.out.println("\t"+sum1+"\t"+sum2+"\t"+sum3+"\t"+sum4+"\t"+count);
   }catch(Exception e){System.out.println(e);}
      if(x==1)
      {
         s+=singleTeacher;
         return s;
      }
      if(x==4)
      {
         s+=avg1;
         return s;
      }
      if(x==5)
      {
         s+=avg2;
         return s;
      }
      if(x==6)
      {
         s+=avg3;
         return s;
      }
      if(x==7)
      {
         s+=avg4;
         return s;
      }
      return s;
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
      /*final String teacher1 = "J.J.";        
      final String teacher2 = "Lakshmi";        
      final String teacher3 = "Priya"; */       
      final String attentiveness = "Attentiveness";        
      final String discipline = "Class Discipline";        
      final String teaching = "Teaching";        
      final String punctuality = "Punctuality";
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); ;
      if(boolSingle)
      {
      int sum1=0,sum2=0,sum3=0,sum4=0,count=0;
      double avg1=0,avg2=0,avg3=0,avg4=0;
      try{
         count++;
         sum1+=rs.getInt(4);
         sum2+=rs.getInt(5);
         sum3+=rs.getInt(6);
         sum4+=rs.getInt(7);
         while(rs.next())
      {
         count++;
         sum1+=rs.getInt(4);
         sum2+=rs.getInt(5);
         sum3+=rs.getInt(6);
         sum4+=rs.getInt(7);
      }  
      
      
      avg1=1.0*(sum1/count);
      avg2=1.0*(sum2/count);
      avg3=1.0*(sum3/count);
      avg4=1.0*(sum4/count);
      System.out.println("\t"+sum1+"\t"+sum2+"\t"+sum3+"\t"+sum4+"\t"+count);
   }catch(Exception e){System.out.println(e);}

       

      dataset.addValue( avg1, singleTeacher , attentiveness );        
      dataset.addValue( avg2, singleTeacher , teaching );        
      dataset.addValue( avg3, singleTeacher , discipline ); 
      dataset.addValue( avg4, singleTeacher , punctuality );
      boolSingle=false;
      }                 
      else
      {
         for(int i=0;i<showTeacher.size();i++)
         {
            String sql="select * from krupashirke where class='SE"+year+"' and divison='B"+div+"' and teacher_name='"+showTeacher.get(i)+"';";
            System.out.println(sql);
            int sum1=0,sum2=0,sum3=0,sum4=0,count=0;
            double avg1=0,avg2=0,avg3=0,avg4=0;
            try{
            rs=stmt.executeQuery(sql);
            rs.first();
            count++;
            sum1+=rs.getInt(4);
            sum2+=rs.getInt(5);
            sum3+=rs.getInt(6);
            sum4+=rs.getInt(7);
            while(rs.next())
            {
               count++;
               sum1+=rs.getInt(4);
               sum2+=rs.getInt(5);
               sum3+=rs.getInt(6);
               sum4+=rs.getInt(7);
            }  
      
      
            avg1=1.0*(sum1/count);
            avg2=1.0*(sum2/count);
            avg3=1.0*(sum3/count);
            avg4=1.0*(sum4/count);
            System.out.println("\t"+sum1+"\t"+sum2+"\t"+sum3+"\t"+sum4+"\t"+count);
            }catch(Exception e){System.out.println(e);}

       

      dataset.addValue( avg1, showTeacher.get(i) , attentiveness );        
      dataset.addValue( avg2, showTeacher.get(i) , teaching );        
      dataset.addValue( avg3, showTeacher.get(i) , discipline ); 
      dataset.addValue( avg4, showTeacher.get(i) , punctuality );
      boolAll=false;
      }
      }
      return dataset; 
      
      /*dataset.addValue( 5.0 , teacher2 , attentiveness );        
      dataset.addValue( 6.0 , teacher2 , teaching );       
      dataset.addValue( 10.0 , teacher2 , discipline );        
      dataset.addValue( 4.0 , teacher2 , punctuality );

      dataset.addValue( 4.0 , teacher3 , attentiveness );        
      dataset.addValue( 2.0 , teacher3 , teaching );        
      dataset.addValue( 3.0 , teacher3 , discipline );        
      dataset.addValue( 6.0 , teacher3 , punctuality );   */ 
        

      
   }
	
   public void create()
   {
	btn=new JButton("Graph-BAR");
	JFrame frm=new JFrame();
	frm.add(btn);
	frm.setVisible(true);
	btn.addActionListener(this);
   }

public void frame3()
{
   thirdFrame=new JFrame("Frame3");
   JTable table = new JTable(new MyTableModel(4));
   table.setPreferredScrollableViewportSize(new Dimension(500, 70));
   JScrollPane scrollPane = new JScrollPane(table);
   thirdFrame.add(scrollPane);
   thirdFrame.setVisible(true);
   thirdFrame.setSize(550,77);
  
}

public void frame2a()
{
   String sql="select distinct teacher_name from krupashirke;";
   secondFrameA=new JFrame("Frame2A");
   System.out.println("insidee");
   lblTeacher=new JLabel("Teacher: ",SwingConstants.CENTER);
   cbxTeacher=new JComboBox();
   rbLectures=new JRadioButton("Lectures");
   rbPracticals=new JRadioButton("Practicals");
   rbTypeGroup=new ButtonGroup();
   btnGraphA=new JButton("Bar Graph");
   btnTextAna=new JButton("Text Analysis");
   try{
      rs=stmt.executeQuery(sql);
      System.out.println("executed");
      cbxTeacher.removeAllItems();
      cbxTeacher.addItem("--Select--");
      rs.first();
      cbxTeacher.addItem(rs.getString(1));
      System.out.println("will add");
      while(rs.next())
      {
         System.out.println("inside while");
         cbxTeacher.addItem(rs.getString(1));
      }  
   }catch(Exception e){System.out.println(e);}
   secondFrameA.setLayout(new GridLayout(3,2,2,2));
   secondFrameA.add(lblTeacher);
   secondFrameA.add(cbxTeacher);
   secondFrameA.add(rbLectures);
   secondFrameA.add(rbPracticals);
   rbTypeGroup.add(rbLectures);
   rbTypeGroup.add(rbPracticals);
   secondFrameA.add(btnGraphA);
   secondFrameA.add(btnTextAna);
   secondFrameA.setVisible(true);
   secondFrameA.setSize(300,150);
   btnGraphA.addActionListener(this);
   btnTextAna.addActionListener(this);
}

public void frame2b()
{
   secondFrameB=new JFrame("Frame2B");
   btnShow=new JButton("Show");
   secondFrameB.setLayout(new GridLayout(count+2,1,1,1));
   lblTeachCompare=new JLabel("Choose teachers to compare",SwingConstants.CENTER);
   secondFrameB.add(lblTeachCompare);
   checkTeach1=new JCheckBox(strTeacher[1]);
   checkTeach2=new JCheckBox(strTeacher[2]);
   checkTeach3=new JCheckBox(strTeacher[3]);
   checkTeach4=new JCheckBox(strTeacher[4]);
   secondFrameB.add(checkTeach1);
   secondFrameB.add(checkTeach2);
   secondFrameB.add(checkTeach3);
   secondFrameB.add(checkTeach4);
   secondFrameB.add(btnShow);
   secondFrameB.setVisible(true);
   secondFrameB.setSize(250,200);
   btnShow.addActionListener(this);
}

public void frame1()
{
   firstFrame=new JFrame("Frame1");
   firstFrame.setLayout(new GridLayout(4,1,5,5));
   pnlYear=new JPanel();
   firstFrame.add(pnlYear);
   pnlYear.setLayout(new GridLayout(1,2,1,1));
   lblYear=new JLabel("Year: ",SwingConstants.CENTER);
   pnlYear.add(lblYear);
   cbxYear=new JComboBox(strYear);
   pnlYear.add(cbxYear);
   pnltxt1=new JPanel();
   firstFrame.add(pnltxt1);
   lbltext1=new JLabel("--------------------Choose Either of the following--------------------");
   pnltxt1.add(lbltext1);
   pnlDiv=new JPanel();
   firstFrame.add(pnlDiv);
   pnlDiv.setLayout(new GridLayout(1,4,1,1));
   lblDiv=new JLabel("Div: ",SwingConstants.CENTER);
   pnlDiv.add(lblDiv);
   cbxDiv=new JComboBox(strDiv);
   pnlDiv.add(cbxDiv);
   lblSubject=new JLabel("Subject: ",SwingConstants.CENTER);
   pnlDiv.add(lblSubject);
   cbxSubject=new JComboBox(strSubject);
   pnlDiv.add(cbxSubject);
   pnlBtn=new JPanel();
   firstFrame.add(pnlBtn);
   pnlBtn.setLayout(new GridLayout(1,2,1,1));
   btnSingle=new JButton("Individual Analysis");
   btnAll=new JButton("Comparison");
   pnlBtn.add(btnSingle);
   pnlBtn.add(btnAll);
   firstFrame.setVisible(true);
   firstFrame.setSize(350,200);
   btnSingle.addActionListener(this);
   btnAll.addActionListener(this);
}

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource().equals(btnSingle))
   {
      boolSingle=true;
      if(cbxYear.getSelectedItem().equals("--Select--"))
         JOptionPane.showMessageDialog(firstFrame,"Please select the Year","Error!",JOptionPane.ERROR_MESSAGE);
      else if(cbxDiv.getSelectedItem().equals("--Select--")&&cbxSubject.getSelectedItem().equals("--Select--"))
         JOptionPane.showMessageDialog(firstFrame,"Please select either Div or Subject","Error!",JOptionPane.ERROR_MESSAGE);
      else
      {
         year=""+cbxYear.getSelectedItem();
         
         if(cbxDiv.getSelectedItem().equals("--Select--"))
            subj=""+cbxSubject.getSelectedItem();
         else
            div=""+cbxDiv.getSelectedItem();

         System.out.println(""+year+subj+div);

         firstFrame.setVisible(false);
         System.out.println("before");
         frame2a();
         System.out.println("after");   
      }
      
   }
   else if(ae.getSource().equals(btnAll))
   {
      boolAll=true;
      if(cbxYear.getSelectedItem().equals("--Select--"))
         JOptionPane.showMessageDialog(firstFrame,"Please select the Year","Error!",JOptionPane.ERROR_MESSAGE);
      else if(cbxDiv.getSelectedItem().equals("--Select--")&&cbxSubject.getSelectedItem().equals("--Select--"))
         JOptionPane.showMessageDialog(firstFrame,"Please select either Div or Subject","Error!",JOptionPane.ERROR_MESSAGE);
      else
      {
         frame2b();
      }
   }
   else if(ae.getSource().equals(btnGraphA))
   {
      if(cbxTeacher.getSelectedItem().equals("--Select--"))
         JOptionPane.showMessageDialog(firstFrame,"Please select Teacher Name","Error!",JOptionPane.ERROR_MESSAGE);
      else if(!(rbLectures.isSelected()||rbPracticals.isSelected()))
         JOptionPane.showMessageDialog(firstFrame,"Please choose Practicals or Lectures","Error!",JOptionPane.ERROR_MESSAGE);
      else
      {
         singleTeacher=""+cbxTeacher.getSelectedItem();
         if(rbLectures.isSelected())
         {
            boolLec=true;
            try{
               String sql="select * from krupashirke where class='"+year+"' and divison='"+div+"' and teacher_name='"+singleTeacher+"' and lecture=1;";
               rs=stmt.executeQuery(sql);
               rs.first();
            }catch(Exception e){System.out.println(e);}
            BarChart_AWT("Teacher Feedback Statistics", "Overall Stats");
         }   
         else{
            boolPrac=true;
            try{
               String sql="select * from krupashirke where class='"+year+"' and divison='"+div+"' and teacher_name='"+singleTeacher+"' and practical=1;";
               rs=stmt.executeQuery(sql);
               rs.first();
            }catch(Exception e){System.out.println(e);}
            BarChart_AWT("Teacher Feedback Statistics", "Overall Stats");
         }

      }
   }
   else if(ae.getSource().equals(btnShow))
   {
      if(checkTeach1.isSelected())
      {
        showTeacher.add("Lakshmi Kurup");
      }
      if(checkTeach2.isSelected())
      {
         showTeacher.add("Ruhina Karani");
      }
      if(checkTeach3.isSelected())
      {
         showTeacher.add("Teacher 3");
      }
      if(checkTeach4.isSelected())
      {
         showTeacher.add("Teacher 4");
      }

      BarChart_AWT("Teacher Feedback Statistics", "Overall Stats");
   }
   else if(ae.getSource().equals(btnTextAna))
   {
      if(cbxTeacher.getSelectedItem().equals("--Select--"))
         JOptionPane.showMessageDialog(firstFrame,"Please select Teacher Name","Error!",JOptionPane.ERROR_MESSAGE);
      else if(!(rbLectures.isSelected()||rbPracticals.isSelected()))
         JOptionPane.showMessageDialog(firstFrame,"Please choose Practicals or Lectures","Error!",JOptionPane.ERROR_MESSAGE);
      else
      {
         singleTeacher=""+cbxTeacher.getSelectedItem();
         if(rbLectures.isSelected())
         {
            boolLec=true;
            try{
               String sql="select * from krupashirke where class='"+year+"' and divison='"+div+"' and teacher_name='"+singleTeacher+"' and lecture=1;";
               rs=stmt.executeQuery(sql);
               rs.first();
            }catch(Exception e){System.out.println(e);}
            frame3();
         }   
         else{
            boolPrac=true;
            try{
               String sql="select * from krupashirke where class='"+year+"' and divison='"+div+"' and teacher_name='"+singleTeacher+"' and practical=1;";
               rs=stmt.executeQuery(sql);
               rs.first();
            }catch(Exception e){System.out.println(e);}
            frame3();
         }

      }
   }
}

   public static void main( String[ ] args )
   {
      Analysis chart = new Analysis("ABC");
      chart.connectionmake();
      chart.frame1();
     //chart.create();
	
   }
}
