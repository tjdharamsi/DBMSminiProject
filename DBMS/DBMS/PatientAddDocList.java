import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PatientAddDocList extends JFrame implements ActionListener
{
 JTable docList;
 JScrollPane jsp;
 JButton bok;
 JLabel ldoc;
 String name;
 String[] colHeads={"Doctor Id","Doctor name","Specilization"};
 String[][] doc;
 int n=0;
 int i=0;
 
 
 PatientAddDocList(String x)
 {
   super("Logged in as " +x);
    name=x;
    setSize(500,500);
    setVisible(true);
    setLayout(null);
    
    ldoc=new JLabel("Doctor List");
    ldoc.setBounds(70,60,100,20);
    add(ldoc);
    
    try
    {    
      Connection c = null;
      Statement stmt = null;
      Class.forName("org.postgresql.Driver");
      c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      String sql="Select count(*) from doctor;";
      ResultSet rs=stmt.executeQuery(sql);
      while(rs.next())
      {
          n=rs.getInt("count");
      }
      doc=new String[n][3];
      String sql1="Select did,name,specialization from doctor;";
      ResultSet rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
          int id=rs1.getInt("did");
          String name1=rs1.getString("name");
          String special=rs1.getString("specialization");
          String did=Integer.toString(id);
          doc[i][0]=did;
          doc[i][1]=name1;
          doc[i][2]=special;
          i++;
      }
      
      docList=new JTable(doc,colHeads);
    docList.setBounds(70,100,350,250);
    add(docList);
    
    jsp=new JScrollPane(docList);
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
     
      bok.addActionListener(new PatientAddDocList.ok());
    
    }
    
    
    class ok implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent ae)
    {}
    
   
}
      

   