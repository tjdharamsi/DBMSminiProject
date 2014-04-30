import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PatientModify extends JFrame implements ActionListener
{
 JLabel lpid;
 JButton bsub,bback,bclr;
 JTextField tfpid;
 String pid;
 String name,name1,doa,dob,bloodgp,gender,area,city,cur,ph1="",ph2="";
 int did1,pid1,userid;
 boolean flag=false;
 
 PatientModify(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(400,400);
  setVisible(true);
  setLayout(null);

  lpid=new JLabel("Enter Patient Id");
  lpid.setBounds(100,100,180,20);
  add(lpid);
  
  tfpid=new JTextField(30);
  tfpid.setBounds(200,100,100,20);
  add(tfpid);
  
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
            new  PatOptions(name);
            setVisible(false);
        }
    }
    
    class clear implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

            tfpid.setText("");
        }
    }


  class submit implements ActionListener
  {
        public void actionPerformed(ActionEvent ae)
        {
            pid=tfpid.getText().trim();
         if(pid.equals(""))
         {
          new ErrorDialog1();
         }
         else
         {
             try
             {
             pid1=Integer.parseInt(pid);    
             Connection c = null;
             Statement stmt = null;
             Class.forName("org.postgresql.Driver");
             c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
             c.setAutoCommit(false);
             stmt = c.createStatement();
             String sql5="select pid from out_patient;";
                 ResultSet rs2 = stmt.executeQuery(sql5);
             while(rs2.next())
             {
                 int id=rs2.getInt("pid");
                 if(id==pid1)
                 {
                     flag=true;
                     break;
                 }           
             }
             if(!flag)
             {
                 JOptionPane.showMessageDialog(new JFrame(), "Patient not found", "SORRY", JOptionPane.ERROR_MESSAGE);
                 new PatientModify(name);
                 setVisible(false);
             }
             else
             {
              String sql="Select * from Patient where pid="+pid1+";";
              ResultSet rs = stmt.executeQuery(sql);
              while(rs.next())
              {
               pid1=rs.getInt("pid");
               name1=rs.getString("name");
               bloodgp=rs.getString("bloodgroup");
               dob=rs.getString("dob");
               area=rs.getString("area");
               city=rs.getString("city");
               did1=rs.getInt("did");
               userid=rs.getInt("userid");
               doa=rs.getString("doa");
               gender=rs.getString("gender");
              }
              String sql1="Select * from pat_phone where pid="+pid1+";";
              ResultSet rs1=stmt.executeQuery(sql1);
              while(rs1.next())
              {
                  pid1=rs1.getInt("pid");
                  if(ph1.equals(""))
                    ph1=rs1.getString("phoneno");
                  else
                    ph2=rs1.getString("phoneno");
              }
               String sql2="Select * from out_patient where pid="+pid1+";";
               ResultSet rs3 =stmt.executeQuery(sql2);
               while(rs3.next())
               {
                  pid1=rs3.getInt("pid");
                  cur=rs3.getString("illness");
               }
             new PatientModifySubmit(name,pid1,name1,bloodgp,dob,area,city,did1,doa,gender,ph1,ph2,cur);
             c.commit();
             setVisible(false);
             rs3.close();
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
 

  


