import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PatientDelete extends JFrame implements ActionListener
{
 JLabel lpid;
 JButton bsub,bback,bclr;
 JTextField tfpid;
 String pid;
 String name,name1,doa;
 int pid1,doctchg,workhr;
 boolean flag=false;
 
 PatientDelete(String x)
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
  
  bclr.addActionListener(new PatientDelete.clear());
  bsub.addActionListener(new PatientDelete.submit());
  bback.addActionListener(new PatientDelete.back());

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
             String sql3="select pid from out_patient;";
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
                 new PatientDelete(name);
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
               String sql2="Select p.pid,p.name,p.doa from patient p,out_patient op where p.pid=op.pid"+
                            " and p.pid="+pid1+";";
               ResultSet rs3 =stmt.executeQuery(sql2);
               while(rs3.next())
               {
                  pid1=rs3.getInt("pid");
                  name1=rs3.getString("name");
                  doa=rs3.getString("doa");
               }
             String sql="Delete from patient where pid="+pid1+";";
             stmt.executeUpdate(sql);
             c.commit();
             JOptionPane.showMessageDialog(null,"Patient deleted Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
             new BillDisplay(name,pid1,name1,doa,doctchg);
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

  


