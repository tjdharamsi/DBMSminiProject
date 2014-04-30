import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DoctorDetails extends JFrame implements ActionListener
{
 JLabel ldid;
 JButton bsub,bback,bclr;
 JTextField tfdid;
 String did;
 String name;
 String name1,type,qualific,special,workhr,area,city,nid,ph1="",ph2="";
 int did1,nid1,workhr1;
 boolean flag=false;

 
 DoctorDetails(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(400,400);
  setVisible(true);
  setLayout(null);

  ldid=new JLabel("Enter Doctor Id");
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
  
  bclr.addActionListener(new clear());
  bsub.addActionListener(new submit());
  bback.addActionListener(new back());

 }
 
 public void actionPerformed(ActionEvent ae)
        {}

    class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  ViewDoctorOptions(name);
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
         did=tfdid.getText().trim();
         if(did.equals(""))
         {
          new ErrorDialog1();
         }
         else
         {
             try
             {
             did1=Integer.parseInt(did);    
             Connection c = null;
             Statement stmt = null;
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
             c.setAutoCommit(false);
             stmt = c.createStatement();
             String sql5="select did from doctor;";
                 ResultSet rs2 = stmt.executeQuery(sql5);
             while(rs2.next())
             {
                 int id=rs2.getInt("did");
                 if(id==did1)
                 {
                     flag=true;
                     break;
                 }           
             }
             if(!flag)
             {
                 JOptionPane.showMessageDialog(new JFrame(), "Doctor not found", "SORRY", JOptionPane.ERROR_MESSAGE);
                 new DoctorDetails(name);
                 setVisible(false);
             }
             else
             {
              String sql="Select * from doctor where did="+did1+";";
              ResultSet rs = stmt.executeQuery(sql);
              while(rs.next())
              {
               did1=rs.getInt("did");
               name1=rs.getString("name");
               type=rs.getString("type");
               qualific=rs.getString("qualification");
               special=rs.getString("specialization");
               workhr1=rs.getInt("workhours");
               area=rs.getString("area");
               city=rs.getString("city");
               nid1=rs.getInt("nid");
               
             }
             String sql1="Select * from doc_phone where did="+did1+";";
             ResultSet rs1=stmt.executeQuery(sql1);
              while(rs1.next())
              {
                  did1=rs1.getInt("did");
                  if(ph1.equals(""))
                   ph1=rs1.getString("phoneno");
                  else
                    ph2=rs1.getString("phoneno");
              }
             new DoctorDisplay(name,did1,name1,type,qualific,special,workhr1,area,city,nid1,ph1,ph2);
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
 