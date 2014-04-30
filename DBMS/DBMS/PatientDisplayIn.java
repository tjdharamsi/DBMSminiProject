
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PatientDisplayIn extends JFrame implements ActionListener
{
 JLabel lpatname,lpid,ldateadd,lrno,lpattype,lph,lbloodgrp,ldob,larea,lcity,ldid,ldoa,lgender,lph1,lph2,lcur;
 String patname,pattype,name,dateadd,rno,patype,bloodgroup,dob1,area,city,did,doa,gender,ph1,ph2,cur,pid1;
 JButton bok;
 int pid,did1;
 PatientDisplayIn(String x,int pid2,String name2,String bloodgrp,String dob,String area1,String city1,int did2,String doa1,String gndr,String ph3,String ph4,String problem,String rno1)
 {
  super("Logged in as " +x);
  name=x;
  setSize(750,750);
  setVisible(true);
  setLayout(null);
  patname=name2;
  pid=pid2;
  pid1=Integer.toString(pid2);
  bloodgroup=bloodgrp;
  dob1=dob;
  area=area1;
  city=city1;
  did=Integer.toString(did2);
  doa=doa1;
  gender=gndr;
  ph1=ph3;
  ph2=ph4;
  cur=problem;
  pattype="In Patient";
  rno=rno1;
  
    lpatname=new JLabel("Patient Name :   " +patname);
    lpatname.setBounds(100,120,300,20);
    add(lpatname);
	 
    lpid=new JLabel("Patient Id :    " +pid1);
    lpid.setBounds(100,70,300,20);
    add(lpid);
    
    lbloodgrp=new JLabel("Blood Group :    "+bloodgroup);
    lbloodgrp.setBounds(100,170,300,20);
    add(lbloodgrp);
    
   ldob=new JLabel("Date Of Birth :    "+dob1);
    ldob.setBounds(100,220,300,20);
    add(ldob);
    
    larea=new JLabel("Area :    "+area);
    larea.setBounds(100,270,300,20);
    add(larea);
    
    lcity=new JLabel("City :    "+city);
    lcity.setBounds(400,270,300,20);
    add(lcity);
    
    lpattype=new JLabel("Patient Type :   " +pattype);
    lpattype.setBounds(100,320,300,20);
    add(lpattype);
    
     ldid=new JLabel("Consulting Doctor ID :  " +did);
     ldid.setBounds(100,370,300,20);
     add(ldid); 
	
     lcur=new JLabel("Disability :   " +cur); 
     lcur.setBounds(100,420,300,20);
     add(lcur);
     
    
    ldoa=new JLabel("Date Of Admission :   "+doa);
    ldoa.setBounds(100,470,300,20);
    add(ldoa);
    
    lrno=new JLabel("Room No.  :   "+rno);
    lrno.setBounds(100, 520, 300, 20);
    add(lrno);
    
    lph=new JLabel("Contact Details :   ");
    lph.setBounds(100,570,200,20);
    add(lph);    
    
    lph1=new JLabel("1.  :  "+ph1);
    lph1.setBounds(300,570,150,20);
    add(lph1);  
    
    lph2=new JLabel("2.  :  "+ph2);
    lph2.setBounds(450,570,150,20);
    add(lph2); 
    
	 
	 bok=new JButton("OK",new ImageIcon("images/ok.png"));
	 bok.setBounds(250,630,80,30);
	 add(bok);
	 
	  bok.addActionListener(new ok());
  }
  
  
  public void actionPerformed(ActionEvent ae)
  {}

  class ok implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new  PatientDetailsIn(name);
			setVisible(false);
		}
	}
	
	
}

