
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DoctorDisplay extends JFrame implements ActionListener
{
 JLabel lname,lqual,ldid,lworkhr,lspecial,lsal,ltype,larea,lcity,lph1,lnid,lph2;
 String name;
 String name1,type,qualific,special,area,city,ph1="",ph2="";
 int did1,nid1,workhr,salary;
 JButton bok;
 DoctorDisplay(String x,int did2,String name2,String type1,String qual1,String special1,int workhrs,String area1,String city1,int nid2,String ph3,String ph4)
 {
  super("Logged in as " +x);
  name=x;
  setSize(500,724);
  setVisible(true);
  setLayout(null);
  name1=name2;
  did1=did2;
  type=type1;
  qualific=qual1;
  special=special1;
  workhr=workhrs;
  area=area1;
  city=city1;
  nid1=nid2;
  ph1=ph3;
  ph2=ph4;
  salary=workhrs*10000;
  
  
  
    lname=new JLabel("Doctor Name :   " +name1);
    lname.setBounds(100,120,300,20);
    add(lname);
	 
    ldid=new JLabel("Doctor Id :    " +did1);
    ldid.setBounds(100,70,300,20);
    add(ldid);
	 
    ltype=new JLabel("Doctor Type :    " +type);
    ltype.setBounds(100,170,300,20);
    add(ltype);
	 
    lspecial=new JLabel("Specialization :   " +special);
    lspecial.setBounds(100,220,350,20);
    add(lspecial);
	 
    lqual=new JLabel("Qualification :   " +qualific);
    lqual.setBounds(100,270,350,20);
    add(lqual);
    
    lworkhr=new JLabel("Working Hours :  "+workhr);
    lworkhr.setBounds(100,320,350,20);
    add(lworkhr);
	 
    lsal=new JLabel("Salary :   Rs " +salary);
    lsal.setBounds(100,370,350,20);
    add(lsal);
    
    larea=new JLabel("Area  :    "+area);
    larea.setBounds(100,470,350,20);
    add(larea);
    
    lcity=new JLabel("City  :   "+city );
    lcity.setBounds(100,520,350,20);
    add(lcity);
    
    lnid=new JLabel("Assisted By Nurse_ID : "+nid1);
    lnid.setBounds(100,420,350,20);
    add(lnid);
    
    lph1=new JLabel("Contact Info:   1.  "+ph1);
    lph1.setBounds(100,570,400,20);
    add(lph1);
    
    if(!(ph2.equals("")))
    {
        lph2=new JLabel("2.  "+ph2);
        lph2.setBounds(270,570,100,20);
        add(lph2);
    }
    
    bok=new JButton("OK",new ImageIcon("images/ok.png"));
    bok.setBounds(200,650,80,30);
    add(bok);
	 
	  bok.addActionListener(new ok());
  }
  
  
  public void actionPerformed(ActionEvent ae)
  {}

  class ok implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new  DoctorDetails(name);
			setVisible(false);
		}
	}
	
	
	
}

