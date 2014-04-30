import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NurseModify extends JFrame implements ActionListener
{
 JLabel ldid;
 JButton bsub,bback,bclr;
 JTextField tfdid;
 String name;
 String name1,area,city,nid,ph1="",ph2="";
 int nid1,workhr1,age1;
 boolean flag=false;
 
 NurseModify(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(400,400);
  setVisible(true);
  setLayout(null);

  ldid=new JLabel("Enter Nurse Id");
  ldid.setBounds(100,100,180,20);
  add(ldid);
  
  tfdid=new JTextField(30);
  tfdid.setBounds(200,100,100,20);
  add(tfdid);
  
  bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
  bclr.setBounds(160,180,100,30);
  add(bclr);
  
  bsub=new JButton("Submit",new ImageIcon("images/backup.png"));
  bsub.setBounds(100,220,100,30);
  add(bsub);
  
  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(220,220,100,30);
  add(bback);
  
  bclr.addActionListener(new NurseModify.clear());
  bsub.addActionListener(new NurseModify.submit());
  bback.addActionListener(new NurseModify.back());

 }
 
 public void actionPerformed(ActionEvent ae)
        {}

    class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  NurseOptions(name);
            setVisible(false);
        }
    }
    
    class clear implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

            tfdid.setText("");
        }
    }


  class submit implements ActionListener
  {
        public void actionPerformed(ActionEvent ae)
        {
            nid=tfdid.getText().trim();
         if(nid.equals(""))
         {
          new ErrorDialog1();
         }
         else
         {
             try
             {
             nid1=Integer.parseInt(nid);    
             Connection c = null;
             Statement stmt = null;
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
             c.setAutoCommit(false);
             stmt = c.createStatement();
             String sql5="select nid from nurse;";
                 ResultSet rs2 = stmt.executeQuery(sql5);
             while(rs2.next())
             {
                 int id=rs2.getInt("nid");
                 if(id==nid1)
                 {
                     flag=true;
                     break;
                 }           
             }
             if(!flag)
             {
                 JOptionPane.showMessageDialog(new JFrame(), "Nurse not found", "SORRY", JOptionPane.ERROR_MESSAGE);
                 new NurseModify(name);
                 setVisible(false);
             }
             else
             {
             String sql="Select * from nurse where nid="+nid1+";";
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next())
             {
               nid1=rs.getInt("nid");
               name1=rs.getString("name");
               workhr1=rs.getInt("workhours");
               area=rs.getString("area");
               city=rs.getString("city");
               age1=rs.getInt("age");
               
             }
             String sql1="Select * from nurse_phone where nid="+nid1+";";
             ResultSet rs1=stmt.executeQuery(sql1);
              while(rs1.next())
              {
                  nid1=rs1.getInt("nid");
                  if(ph1.equals(""))
                   ph1=rs1.getString("phoneno");
                  else
                    ph2=rs1.getString("phoneno");
              }
             new NurseModifySubmit(name,nid1,name1,area,city,age1,ph1,ph2);
             setVisible(false);
             rs2.close();
            stmt.close();
             c.close();
          }
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
 

  


