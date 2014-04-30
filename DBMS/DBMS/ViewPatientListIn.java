import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ViewPatientListIn extends JFrame implements ActionListener
{
 JTable patList;
 JScrollPane jsp;
 JButton bok;
 JLabel lpat;
 String name;
 String[] colHeads={"Patient Id","Patient name","Conculting Doctor ID"};
 String[][] pat;
 int n=0;
 int i=0;
 
 
 ViewPatientListIn(String x)
 {
   super("Logged in as " +x);
    name=x;
    setSize(500,500);
    setVisible(true);
    setLayout(null);
    
    lpat=new JLabel("Patient List");
    lpat.setBounds(70,60,100,20);
    add(lpat);
    
    try
    {    
      Connection c = null;
      Statement stmt = null;
      Class.forName("org.postgresql.Driver");
      c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      String sql="Select count(*) from In_patient;";
      ResultSet rs=stmt.executeQuery(sql);
      while(rs.next())
      {
          n=rs.getInt("count");
      }
      pat=new String[n][3];
      String sql1="Select pid,name,did from patient where pid in(select pid from in_patient);";
      ResultSet rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
          int id=rs1.getInt("pid");
          String name1=rs1.getString("name");
          int did=rs1.getInt("did");
          String pid=Integer.toString(id);
          String did1=Integer.toString(did);
          pat[i][0]=pid;
          pat[i][1]=name1;
          pat[i][2]=did1;
          i++;
      }
      
      patList=new JTable(pat,colHeads);
    patList.setBounds(70,100,350,250);
    add(patList);
    
    jsp=new JScrollPane(patList);
    jsp.setBounds(70,100,350,250);
    add(jsp);
    
    rs1.close();
    rs.close();
    c.commit();
    stmt.close();
    c.close();
    
    }
    
    catch (Exception e)
    {
     e.printStackTrace();
     System.err.println(e.getClass().getName()+": "+e.getMessage());
    }
    
   

   
     bok=new JButton("OK",new ImageIcon("images/ok.png"));
     bok.setBounds(200,400,80,30);
     add(bok);
     
      bok.addActionListener(new ViewPatientListIn.ok());
    
    }
    
    
    class ok implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  ViewPatientOptionsIn(name);
            setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent ae)
    {}
    
   
}
      

   