import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class NurseModifySubmit extends JFrame implements ActionListener
{
    
   
    JLabel lname,lage,lworkfrom,lworkto,larea,lcity,lwork,lph1,lph2,lph;
 JTextField tfname,tfage,tfworkfrom,tfworkto,tfarea,tfcity,tfph1,tfph2;
 JButton bback,bsub,bclr;
 String name1,area,city,age,workf,workt,name,ph1,ph2;
 int workfrom,workto,workhrs,age1,nid1;

    
    NurseModifySubmit(String x,int nid,String name2,String area1,String city1,int age,String ph3,String ph4)
    {
   super("Logged in as " +x);
  name=x;
  setSize(768,600);
  setVisible(true);
  setLayout(null);
  nid1=nid;

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
    
  bsub=new JButton("MODIFY",new ImageIcon("images/add.gif"));
  bsub.setBounds(210,450,100,30);
  add(bsub);	

  bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
  bclr.setBounds(330,450,100,30);
  add(bclr);

  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(450,450,100,30);
  add(bback);
 
  bclr.addActionListener(new NurseModifySubmit.clear());
  bsub.addActionListener(new NurseModifySubmit.submit());
  bback.addActionListener(new NurseModifySubmit.back());
  
    
    set(name2,area1,city1,age,ph3,ph4);

    }

class clear implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            tfname.setText("");
            tfworkfrom.setText("");
            tfworkto.setText("");
            tfarea.setText("");
            tfcity.setText("");
            tfph1.setText("");
            tfph2.setText("");
        }
    }


class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new NurseModify(name);
            setVisible(false);
        }
    }
    
   
class submit implements ActionListener
{
    public void actionPerformed(ActionEvent ae)
    {
           city=tfcity.getText().trim();
           area=tfarea.getText().trim();
           name1=tfname.getText().trim();
	   age=tfage.getText().trim();
           workf=tfworkfrom.getText().trim();
           workt=tfworkto.getText().trim();
           ph1=tfph1.getText().trim();
           ph2=tfph2.getText().trim();
           age1=Integer.parseInt(age);
           Connection c=null;
           Statement stmt=null;
		
           if((name1.equals(""))||(age.equals(""))||(city.equals(""))||(area.equals(""))||(workf.equals(""))||(workt.equals(""))||(ph1.equals("")))
	   {
	    new ErrorDialog1();
	   }
           
           else
	   {
            workfrom=Integer.parseInt(workf);
           workto=Integer.parseInt(workt);
           workhrs=workto-workfrom;
           if(workhrs<0)
                workhrs=workhrs+24;
	    try
            {
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
             c.setAutoCommit(false);
             stmt = c.createStatement();
              String sql = "update nurse set name='"+name1+"',workhours="+workhrs+",area='"+area+
                          "',city='"+city+"',age="+age1+" where nid="+nid1+";";
              stmt.executeUpdate(sql); 
              String sql1="update nurse_phone set phoneno='"+ph1+"' where nid="+nid1+";";
                stmt.executeUpdate(sql1);
                if(!(ph2.equals("")))
                {
                 String sql2="update nurse_phone set phoneno='"+ph2+"' where nid="+nid1+";"; 
                 stmt.executeUpdate(sql2);
                }
              
              JOptionPane.showMessageDialog(null,"Nurse modified Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
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

    
    public void set(String name2,String area1,String city1,int age,String ph3,String ph4)
    {
            String age2=Integer.toString(age);
            tfname.setText(name2);
            tfworkfrom.setText("");
            tfworkto.setText("");
            tfarea.setText(area1);
            tfcity.setText(city1);
            tfph1.setText(ph3);
            tfph2.setText(ph4);
            tfage.setText(age2);
    }
        
    
     public void actionPerformed(ActionEvent ae)
       {}

}

