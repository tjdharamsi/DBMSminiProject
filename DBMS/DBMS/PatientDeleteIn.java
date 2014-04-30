import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

class PatientDeleteIn extends JFrame implements ActionListener
{
 JLabel lpid;
 JButton bsub,bback,bclr;
 JTextField tfpid;
 String pid;
 String name,name1,doa,rt,rno,dodis;
 int pid1,doctchg,workhr;
 boolean flag=false;
 
 PatientDeleteIn(String x)
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
  
  try
  {
   Calendar cal=Calendar.getInstance();
   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   df.setLenient(false);  
   dodis=df.format(cal.getTime());
  }
  catch (Exception e)
  {}
  
  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(220,220,100,30);
  add(bback);
  
  bclr.addActionListener(new PatientDeleteIn.clear());
  bsub.addActionListener(new PatientDeleteIn.submit());
  bback.addActionListener(new PatientDeleteIn.back());

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
             String sql3="select pid from in_patient;";
              ResultSet rs = stmt.executeQuery(sql3);
             while(rs.next())
             {
                 int id=rs.getInt("pid");
                 if(id==pid1)
                 {
                     flag=true;
                     break;
                 }           
             }
             if(!flag)
             {
                 JOptionPane.showMessageDialog(new JFrame(), "Patient not found", "SORRY", JOptionPane.ERROR_MESSAGE);
                 new PatientDeleteIn(name);
                 setVisible(false);
             }
             else
             {
              String sql6="Select d.workhours from patient p,doctor d where p.did=d.did and p.pid="+pid1+";";
              ResultSet rs4=stmt.executeQuery(sql6);
              while(rs4.next())
              {
                  workhr=rs4.getInt("workhours");
              }
              doctchg=50*workhr;
              String sql1="Select p.pid,p.name,p.doa,r.room_no,r.type from patient p,in_patient ip,room r "+
                          "where p.pid=ip.pid and p.pid="+pid1+" and ip.room_no=r.room_no;";
             ResultSet rs1=stmt.executeQuery(sql1);
              while(rs1.next())
              {
                 pid1=rs1.getInt("pid");
                 name1=rs1.getString("name");
                 doa=rs1.getString("doa");
                 rno=rs1.getString("room_no");
                 rt=rs1.getString("type");
               }
             String sql="Delete from patient where pid="+pid1+";";
             stmt.executeUpdate(sql);
             c.commit();
             JOptionPane.showMessageDialog(null,"Patient deleted Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
             new BillDisplayIn(name,pid1,name1,doa,rno,rt,dodis,doctchg);
             setVisible(false);
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

  


