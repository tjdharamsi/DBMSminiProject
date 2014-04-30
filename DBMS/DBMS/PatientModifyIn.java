import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PatientModifyIn extends JFrame implements ActionListener
{
 JLabel lpid;
 JButton bsub,bback,bclr;
 JTextField tfpid;
 String pid;
 String name,name1,doa,dob,bloodgp,gender,area,city,cur,ph1="",ph2="",rno;
 int did1,pid1,userid;
 boolean flag=false;
 
 PatientModifyIn(String x)
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
  
  bclr.addActionListener(new PatientModifyIn.clear());
  bsub.addActionListener(new PatientModifyIn.submit());
  bback.addActionListener(new PatientModifyIn.back());

 }
 
 public void actionPerformed(ActionEvent ae)
        {}

    class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  PatOptionsIn(name);
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
             String sql5="select pid from in_patient;";
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
                 new PatientModifyIn(name);
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
               String sql2="Select * from in_patient where pid="+pid1+";";
               ResultSet rs3 =stmt.executeQuery(sql2);
               while(rs3.next())
               {
                  pid1=rs3.getInt("pid");
                  cur=rs3.getString("disorder");
                  rno=rs3.getString("room_no");
               }
             new PatientModifySubmitIn(name,pid1,name1,bloodgp,dob,area,city,did1,doa,gender,ph1,ph2,cur);
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
 

  


