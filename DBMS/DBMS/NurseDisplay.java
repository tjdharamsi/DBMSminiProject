
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NurseDisplay extends JFrame implements ActionListener
{
 JLabel lname,lage,lnid,lworkhr,lsal,larea,lcity,lph1,lph2;
 String name;
 String name1,area,city,ph1="",ph2="";
 int nid1,workhr,salary,age;
 JButton bok;
 NurseDisplay(String x,int nid2,String name2,int workhrs,String area1,String city1,int age2,String ph3,String ph4)
 {
  super("Logged in as " +x);
  name=x;
  setSize(500,650);
  setVisible(true);
  setLayout(null);
  name1=name2;
  nid1=nid2;
  workhr=workhrs;
  area=area1;
  city=city1;
  age=age2;
  ph1=ph3;
  ph2=ph4;
  salary=workhrs*3000;
  
  
  
    lname=new JLabel("Nurse Name :   " +name1);
    lname.setBounds(100,120,300,20);
    add(lname);
	 
    lnid=new JLabel("Nurse Id :    " +nid1);
    lnid.setBounds(100,70,300,20);
    add(lnid);

    lworkhr=new JLabel("Working Hours :  "+workhr);
    lworkhr.setBounds(100,170,350,20);
    add(lworkhr);
	 
    lsal=new JLabel("Salary :   Rs " +salary);
    lsal.setBounds(100,220,350,20);
    add(lsal);
    
    larea=new JLabel("Area  :    "+area);
    larea.setBounds(100,320,350,20);
    add(larea);
    
    lcity=new JLabel("City  :   "+city );
    lcity.setBounds(100,370,350,20);
    add(lcity);
    
    lage=new JLabel("Age :"+age);
    lage.setBounds(100,270,350,20);
    add(lage);
    
    lph1=new JLabel("Contact Info:   1.  "+ph1);
    lph1.setBounds(100,420,400,20);
    add(lph1);
    
   if(!(ph2.equals("")))
    {
        lph2=new JLabel("2.  "+ph2);
        lph2.setBounds(270,420,100,20);
        add(lph2);
    }
    
    bok=new JButton("OK",new ImageIcon("images/ok.png"));
    bok.setBounds(200,500,80,30);
    add(bok);
	 
	  bok.addActionListener(new NurseDisplay.ok());
  }
  
  
  public void actionPerformed(ActionEvent ae)
  {}

  class ok implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new  NurseDetails(name);
			setVisible(false);
		}
	}
	
	
	
}

