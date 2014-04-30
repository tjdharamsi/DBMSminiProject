import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BillDisplayIn extends JFrame implements ActionListener
{
 JLabel lpatname,lpatno,ldateadd,lrt,ldateofdis,lservicechg,lroomchg,ltotal,ldct,ldays,lrno,lbillno;
 String patname,name,patno,dateadd,rt,dateofdis,rno;
 JButton bok;
 final double servicechg=100.0;
 int[][] daytab={
          {0,31,28,31,30,31,30,31,31,30,31,30,31},
          {0,31,29,31,30,31,30,31,31,30,31,30,31}
			};

 double chg,total;
  int days,addda,admonth,disda, dismonth,adyear,disyear,addday, disday,l,leap,yeardif,dct,i=0,pid1,billno;
 
 
 BillDisplayIn(String x,int pid,String name1,String doa,String room_no,String rtype,String dodis,int dct)
 {
  super("Logged in as " +x);
  name=x;
  setSize(600,600);
  setVisible(true);
  setLayout(null);
  this.patname=name1;
  this.patno=Integer.toString(pid);
  this.dateadd=doa;
  this.rt=rtype;
  this.rno=room_no;
  this.dateofdis=dodis;
  this.dct=dct;
    addda=Integer.parseInt(dateadd.substring(8,10));
	 admonth=Integer.parseInt(dateadd.substring(5,7));
	 disda=Integer.parseInt(dateofdis.substring(8,10));	
	 dismonth=Integer.parseInt(dateofdis.substring(5,7));	
	 adyear=Integer.parseInt(dateadd.substring(0,4));		
	 disyear=Integer.parseInt(dateofdis.substring(0,4));
	 addday=dayofyear(adyear,admonth,addda);
	 disday=dayofyear(disyear,dismonth,disda);
         yeardif=disyear-adyear;
	
	 if(yeardif==0) 
            days=disday-addday+1;
         else if(yeardif>0)
         {
             days=disday-addday+1+(365*yeardif);
         }
	 
	  
	  if((rt.equals("Private")))
	  {
	   chg=500*days;
	  }
	  else if((rt.equals("Semi-Private")))
	  {
	   chg=300*days;
	 }
	 else if((rt.equals("General")))
	 {
	  chg=150*days;
	 }
	 else 
	 {
	  chg=80*days;
	 }
   total=servicechg+chg+dct;
   
   try
        {
          Connection c1 = null;
          Statement stmt1 = null;
          Class.forName("org.postgresql.Driver");
          c1=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
          c1.setAutoCommit(false);
          stmt1 = c1.createStatement();
          ResultSet rs1 = stmt1.executeQuery("SELECT (bill_no) FROM bill where bill_no=(select max(bill_no) from bill);");
           while(rs1.next())
           {
            int l=rs1.getInt("bill_no");
            if(l!=0)
              i=l;
            }     
             billno=i+1;
             
            lbillno=new JLabel("Bill No. :"+billno);
            lbillno.setBounds(350,70,100,20);
            add(lbillno);
            
           String sql1="Insert into bill values("+billno+","+total+");";
            stmt1.executeUpdate(sql1);
            String sql2="Insert into pays values("+billno+","+pid1+",'"+doa+"');";
            stmt1.executeUpdate(sql2);
           String sql3="delete from patient where pid="+pid1+";";
            stmt1.executeUpdate(sql3);
            c1.commit();
            stmt1.close();
            c1.close();
        }
        catch (Exception e)
              {
               e.printStackTrace();
               System.err.println(e.getClass().getName()+": "+e.getMessage());
              }

    lpatname=new JLabel("Patient Name :   " +patname);
	 lpatname.setBounds(100,70,300,20);
	 add(lpatname);
	 
	 lpatno=new JLabel("Patient Id :    " +patno);
	 lpatno.setBounds(100,120,300,20);
	 add(lpatno);
	 
	 ldateadd=new JLabel("Date of Admission :   " +dateadd);
	 ldateadd.setBounds(100,170,300,20);
	 add(ldateadd);
	 
	 ldateofdis=new JLabel("Date of Discharge :   " +dateofdis);
	 ldateofdis.setBounds(100,220,300,20);
	 add(ldateofdis);
	 
	 ldct=new JLabel("Doctor Charges :    Rs. " +dct);
	 ldct.setBounds(100,420,300,20);
	 add(ldct);
	 
	 ldays=new JLabel("Total number of days :   " +days);
	 ldays.setBounds(100,270,300,20);
	 add(ldays);
	 
	 lservicechg=new JLabel("Service Charges  :    Rs. " +servicechg);
	 lservicechg.setBounds(100,320,300,20);
	 add(lservicechg);
	 
	 lroomchg=new JLabel("Room Charges :     Rs. " +chg);
	 lroomchg.setBounds(100,370,300,20);
	 add(lroomchg);
	 
	 ltotal=new JLabel("Total Amount to be paid : Rs. " +total);
	 ltotal.setBounds(100,470,300,20);
	 add(ltotal);
	 
	 
	 bok=new JButton("OK",new ImageIcon("images/ok.png"));
	 bok.setBounds(275,500,80,30);
	 add(bok);
	 
	  bok.addActionListener(new BillDisplayIn.ok());
  }
  
  public int dayofyear(int year, int month, int day)
	{
	   int i,leap;
		if( year%4==0)
	    leap=1;
	  else
	  leap=0;
	  for(i=1;i<month;i++)
	  day+=daytab[leap][i];
	  return day;
	 }

 
  public void actionPerformed(ActionEvent ae)
  {}

  class ok implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new  Input(name);
			setVisible(false);
		}
	}
	
}
