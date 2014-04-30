import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NurseDelete extends JFrame implements ActionListener
{
 JLabel lnid;
 JButton bsub,bback,bclr;
 JTextField tfnid;
 String nid;
 String name;
 int nid1;
 boolean flag=false;
 
 NurseDelete(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(400,400);
  setVisible(true);
  setLayout(null);

  lnid=new JLabel("Enter Nurse Id");
  lnid.setBounds(100,100,180,20);
  add(lnid);
  
  tfnid=new JTextField(30);
  tfnid.setBounds(200,100,100,20);
  add(tfnid);
  
  bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
  bclr.setBounds(160,180,100,30);
  add(bclr);
  
  bsub=new JButton("Submit",new ImageIcon("images/backup.png"));
  bsub.setBounds(100,220,100,30);
  add(bsub);
  
  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(220,220,100,30);
  add(bback);
  
  bclr.addActionListener(new NurseDelete.clear());
  bsub.addActionListener(new NurseDelete.submit());
  bback.addActionListener(new NurseDelete.back());

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

            tfnid.setText("");
        }
    }


  class submit implements ActionListener
  {
        public void actionPerformed(ActionEvent ae)
        {
            nid=tfnid.getText().trim();
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
             String sql3="select nid from nurse;";
              ResultSet rs = stmt.executeQuery(sql3);
             while(rs.next())
             {
                 int id=rs.getInt("nid");
                 if(id==nid1)
                 {
                     flag=true;
                     break;
                 }           
             }
             if(!flag)
             {
                 JOptionPane.showMessageDialog(new JFrame(), "Nurse not found", "SORRY", JOptionPane.ERROR_MESSAGE);
                 new NurseDelete(name);
                 setVisible(false);
             }
             else
             {
             String sql="Delete from nurse where nid="+nid1+";";
             stmt.executeUpdate(sql);
             c.commit();
             JOptionPane.showMessageDialog(null,"Nurse deleted Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
             new NurseOptions(name);
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

  


