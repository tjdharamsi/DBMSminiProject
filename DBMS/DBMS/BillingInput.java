import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

class BillingInput extends JFrame implements ActionListener
{
 JLabel lpid;
 JButton bsub,bback,bclr;
 JTextField tfpid;
 String pid;
 String name,name1,doa,rno,pattype,rt,dodis;
 int pid1,userid,workhr,doctchg;
 boolean flag=false;
 
 BillingInput(String x)
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
  
           try
		{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);  
		 dodis=df.format(cal.getTime());

	
		}
		catch (Exception e)
	       {}
  
  bclr.addActionListener(new BillingInput.clear());
  bsub.addActionListener(new BillingInput.submit());
  bback.addActionListener(new BillingInput.back());

 }
 
 public void actionPerformed(ActionEvent ae)
        {}

    class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  Input(name);
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
             String sql5="select pid from patient;";
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
                 new BillingInput(name);
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
              flag=false;
              String sql="Select pid from out_patient";
              ResultSet rs = stmt.executeQuery(sql);
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
                  pattype="In_Patient";
              else
                  pattype="Out_Patient";
             if(pattype.equals("In_Patient"))
             {
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
              
                new BillDisplayIn(name,pid1,name1,doa,rno,rt,dodis,doctchg);
                setVisible(false);
                rs1.close();
             }
              else
              {
               String sql2="Select p.pid,p.name,p.doa from patient p,out_patient op where p.pid=op.pid"+
                            " and p.pid="+pid1+";";
               ResultSet rs3 =stmt.executeQuery(sql2);
               while(rs3.next())
               {
                  pid1=rs3.getInt("pid");
                  name1=rs3.getString("name");
                  doa=rs3.getString("doa");
               }
               
               new BillDisplay(name,pid1,name1,doa,doctchg);
               setVisible(false);
              rs3.close();
              }
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

  


