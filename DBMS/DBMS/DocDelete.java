import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DocDelete extends JFrame implements ActionListener
{
 JLabel ldid;
 JButton bsub,bback,bclr;
 JTextField tfdid;
 String did;
 String name;
 int did1;
 boolean flag=false;
 
 DocDelete(String x)
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
  
  bclr.addActionListener(new DocDelete.clear());
  bsub.addActionListener(new DocDelete.submit());
  bback.addActionListener(new DocDelete.back());

 }
 
 public void actionPerformed(ActionEvent ae)
        {}

    class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  DoctOptions(name);
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
             String sql3="select did from doctor;";
              ResultSet rs = stmt.executeQuery(sql3);
             while(rs.next())
             {
                 int id=rs.getInt("did");
                 if(id==did1)
                 {
                     flag=true;
                     break;
                 }           
             }
             if(!flag)
             {
                 JOptionPane.showMessageDialog(new JFrame(), "Doctor not found", "SORRY", JOptionPane.ERROR_MESSAGE);
                 new DocDelete(name);
                 setVisible(false);
             }
             else
             {
             String sql="Delete from doctor where did="+did1+";";
             stmt.executeUpdate(sql);
             c.commit();
             JOptionPane.showMessageDialog(null,"Doctor deleted Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
             new DoctOptions(name);
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

  


