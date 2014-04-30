import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BillDisplay extends JFrame implements ActionListener
{
 JLabel lpatname,lwardchg,lpatno,ldateadd,lservicechg,ltotal,ldct,ldays,lbillno;
 String patname,name,patno,dateadd;
 JButton bok;
 int pid1,billno,l,i=0;
 final double servicechg=100.0;
 final double wardchg=50.0;

 double total;
  int days,addda,admonth,disda, dismonth,adyear,disyear,addday, disday,leap,dct;
 
 
 BillDisplay(String x,int pid,String name1,String doa,int dct)
 {
  super("Logged in as " +x);
  name=x;
  setSize(600,600);
  setVisible(true);
  setLayout(null);
  this.patname=name1;
  this.patno=Integer.toString(pid);
  this.dateadd=doa;
  this.dct=dct;
  pid1=pid;
  
   total=servicechg+wardchg+dct;
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
            c1.close();
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
	 
	 lwardchg=new JLabel("Ward Charges :    Rs. " +wardchg);
	 lwardchg.setBounds(100,220,300,20);
	 add(lwardchg);
	 
	 ldct=new JLabel("Doctor Charges :    Rs. " +dct);
	 ldct.setBounds(100,270,300,20);
	 add(ldct);
	 
	 lservicechg=new JLabel("Service Charges  :    Rs. " +servicechg);
	 lservicechg.setBounds(100,320,300,20);
	 add(lservicechg);
	
	 ltotal=new JLabel("Total Amount to be paid : Rs. " +total);
	 ltotal.setBounds(100,370,300,20);
	 add(ltotal);
	 
	 
	 bok=new JButton("OK",new ImageIcon("images/ok.png"));
	 bok.setBounds(275,430,80,30);
	 add(bok);
	 
	  bok.addActionListener(new ok());
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
