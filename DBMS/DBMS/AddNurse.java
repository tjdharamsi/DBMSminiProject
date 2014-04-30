import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

class AddNurse extends JFrame implements ActionListener
{
 JLabel lname,lage,lworkfrom,lworkto,larea,lcity,lwork,lph1,lph2,lph;
 JTextField tfname,tfage,tfworkfrom,tfworkto,tfarea,tfcity,tfph1,tfph2;
 JButton bback,bsub,bclr;
 String name1,area,city,age,workf,workt,name,ph1,ph2;
 int workfrom,workto,workhrs,userid,age1;
 
 AddNurse(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(768,600);
  setVisible(true);
  setLayout(null);

  lname=new JLabel("Name:");
  lname.setBounds(100,80,180,20);
  add(lname);
  
  tfname=new JTextField(30);
  tfname.setBounds(220,80,150,20);
  add(tfname);
  
  lage=new JLabel("Age:");
  lage.setBounds(100,140,180,20);
  add(lage);
  
  tfage=new JTextField(10);
  tfage.setBounds(220,140,150,20);
  add(tfage);
  
  larea=new JLabel("Area:");
  larea.setBounds(100,200,180,20);
  add(larea);
  
  tfarea=new JTextField(70);
  tfarea.setBounds(220,200,150,20);
  add(tfarea);
  
  lcity=new JLabel("City:");
  lcity.setBounds(100,260,180,20);
  add(lcity);
  
  tfcity=new JTextField(70);
  tfcity.setBounds(220,260,220,20);
  add(tfcity);
  
   lwork=new JLabel("Working hours(24hr Format) :");
   lwork.setBounds(100,320,200,20);
   add(lwork);

    lworkfrom=new JLabel("From :");
    lworkfrom.setBounds(300,320,40,20);
    add(lworkfrom);

    tfworkfrom=new JTextField(30);
    tfworkfrom.setBounds(350,320,30,20);
    add(tfworkfrom);
    
    lworkto=new JLabel("to :");
    lworkto.setBounds(390,320,40,20);
    add(lworkto);
    

    tfworkto=new JTextField(30);
    tfworkto.setBounds(450,320,30,20);
    add(tfworkto);
    
    lph=new JLabel("Contact Information");
    lph.setBounds(100,380,150,20);
    add(lph);
    
    lph1=new JLabel("1.");
    lph1.setBounds(250,380,20,20);
    add(lph1);
    
    tfph1=new JTextField(70);
    tfph1.setBounds(270,380,100,20);
    add(tfph1);
            
    lph2=new JLabel("2.");
    lph2.setBounds(370,380,20,20);
    add(lph2);
    
    tfph2=new JTextField(70);
    tfph2.setBounds(390,380,100,20);
    add(tfph2);
    
  bsub=new JButton("ADD",new ImageIcon("images/add.gif"));
  bsub.setBounds(210,450,100,30);
  add(bsub);	

  bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
  bclr.setBounds(330,450,100,30);
  add(bclr);

  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(450,450,100,30);
  add(bback);
 
  bclr.addActionListener(new clear());
  bsub.addActionListener(new submit());
  bback.addActionListener(new back());
  
 }
 
 public void actionPerformed(ActionEvent ae)
		{}

 
 class clear implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{

			tfname.setText("");
			tfage.setText("");
			tfarea.setText("");
			tfcity.setText("");
                        tfworkfrom.setText("");
                        tfworkto.setText("");
                        tfph1.setText("");
                        tfph2.setText("");
		}
	}
 
 class back implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new Input(name);
			setVisible(false);
		}
	}
	
	class submit implements ActionListener
	{
	  public void actionPerformed(ActionEvent ae)
	  {
	   int i=0;
           city=tfcity.getText().trim();
           area=tfarea.getText().trim();
           name1=tfname.getText().trim();
	   age=tfage.getText().trim();
           workf=tfworkfrom.getText().trim();
           workt=tfworkto.getText().trim();
           ph1=tfph1.getText().trim();
           ph2=tfph2.getText().trim();
           age1=Integer.parseInt(age);
           workfrom=Integer.parseInt(workf);
           workto=Integer.parseInt(workt);
           workhrs=workto-workfrom;
           if(workhrs<0)
                workhrs=workhrs+24;
           Connection c=null;
           Statement stmt=null;
		
           if((name1.equals(""))||(age.equals(""))||(city.equals(""))||(area.equals(""))||(workf.equals(""))||(workt.equals(""))||(ph1.equals("")))
	   {
	    new ErrorDialog1();
	   }
           
           else
	   {
	    try
            {
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
             c.setAutoCommit(false);
             stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT (nid) FROM Nurse where nid=(select max(nid) from nurse);");
             while(rs.next())
             {
              int j=rs.getInt("nid");
              if(j!=0)
              i=j;
             }    
              int k=i+1;
              String sql = "INSERT INTO Nurse VALUES("+k+",'"+name1+"',"+workhrs+",'"+area+"','"+city+"',"+age1+");";
              stmt.executeUpdate(sql); 
              String sql1="Insert into Nurse_phone values("+k+",'"+ph1+"');";
                stmt.executeUpdate(sql1);
                if(!(ph2.equals("")))
                {
                 String sql2="Insert into Nurse_phone values("+k+",'"+ph2+"');";  
                 stmt.executeUpdate(sql2);
                }
                String sql3="Select (userid) from admin where name='"+name+"';";
                ResultSet rs2=stmt.executeQuery(sql3);
                while(rs2.next())
                {
                    userid=rs2.getInt("userid");
                }
                String sql4="Insert into employs values("+userid+","+k+");";
                stmt.executeUpdate(sql4);
              JOptionPane.showMessageDialog(null,"Nurse added Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
              rs.close();
              rs2.close();
              stmt.close();
              c.commit();
              c.close();
              new NurseOptions(name);
                setVisible(false);
          }
            
        catch (Exception e)
        {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());   
         new NurseOptions(name);
          setVisible(false);
        }
  }
	
          }
        }
          
        
}