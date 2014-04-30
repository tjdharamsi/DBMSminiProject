import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.*;

class NewUser extends JFrame implements ActionListener
{
 JLabel lusername,lpass,lrepass,lemailid;
 JTextField tfusername,tfemailid;
 JPasswordField tfpass,tfrepass;
 JButton bback,bsub,bclr;
 String name,pass,repass,username,emailid;
 
 NewUser(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(768,600);
  setVisible(true);
  setLayout(null);

  lusername=new JLabel("Username:");
  lusername.setBounds(100,80,180,20);
  add(lusername);
  
  tfusername=new JTextField(30);
  tfusername.setBounds(220,80,150,20);
  add(tfusername);
  
  lpass=new JLabel("Password:");
  lpass.setBounds(100,140,180,20);
  add(lpass);
  
  tfpass=new JPasswordField(30);
  tfpass.setBounds(220,140,150,20);
  add(tfpass);
  
  lrepass=new JLabel("ReEnter Password:");
  lrepass.setBounds(100,200,180,20);
  add(lrepass);
  
  tfrepass=new JPasswordField(30);
  tfrepass.setBounds(220,200,150,20);
  add(tfrepass);
  
  lemailid=new JLabel("Email Id:");
  lemailid.setBounds(100,260,180,20);
  add(lemailid);
  
  tfemailid=new JTextField(70);
  tfemailid.setBounds(220,260,220,20);
  add(tfemailid);
  
  bsub=new JButton("ADD",new ImageIcon("images/add.gif"));
  bsub.setBounds(210,400,100,30);
  add(bsub);	

  bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
  bclr.setBounds(330,400,100,30);
  add(bclr);

  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(450,400,100,30);
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

			tfpass.setText("");
			tfrepass.setText("");
			tfemailid.setText("");
			tfusername.setText("");
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
           pass=tfpass.getText().trim();
           repass=tfrepass.getText().trim();
           username=tfusername.getText().trim();
	   emailid=tfemailid.getText().trim();
           Connection c=null;
           Statement stmt=null;
		
           if((pass.equals(""))||(repass.equals(""))||(username.equals(""))||(emailid.equals("")))
	   {
	    new ErrorDialog1();
	   }

	   if(!(pass.equals(repass)))
	   {
	    JOptionPane.showMessageDialog(null,"Passwords did not match" , "tryagain!!!",JOptionPane.INFORMATION_MESSAGE);
	    tfpass.setText("");
            tfrepass.setText("");
           }
		
           else
	   {
	    try
            {
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
             c.setAutoCommit(false);
             stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT (userid) FROM Admin where userid=(select max(userid) from admin);");
             while(rs.next())
             {
              int j=rs.getInt("userid");
              if(j!=0)
              i=j;
             }    
              int k=i+1;
              String sql = "INSERT INTO ADMIN VALUES("+k+",'"+pass+"','"+username+"');";
              stmt.executeUpdate(sql); 
              JOptionPane.showMessageDialog(null,"User added Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
              rs.close();
              stmt.close();
              c.commit();
              c.close();
        
                 }
          catch (Exception e)
        {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());                
        }
  }
	
          }
        }
          
        
}




  

  
  

  
  
  
  
