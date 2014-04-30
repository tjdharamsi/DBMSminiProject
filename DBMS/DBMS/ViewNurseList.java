import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ViewNurseList extends JFrame implements ActionListener
{
 JTable nurseList;
 JScrollPane jsp;
 JButton bok;
 JLabel lnurse;
 String name;
 String[] colHeads={"Nurse Id","Nurse name","Age"};
 String[][] nurse;
 int n=0;
 int i=0;
 
 
 ViewNurseList(String x)throws Exception
 {
   super("Logged in as " +x);
    name=x;
    setSize(500,500);
    setVisible(true);
    setLayout(null);
    
    lnurse=new JLabel("Nurse List");
    lnurse.setBounds(70,60,100,20);
    add(lnurse);
    
    try
    {    
      Connection c = null;
      Statement stmt = null;
      Class.forName("org.postgresql.Driver");
      c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      String sql="Select count(*) from nurse;";
      ResultSet rs=stmt.executeQuery(sql);
      while(rs.next())
      {
          n=rs.getInt("count");
      }
      nurse=new String[n][3];
      String sql1="Select nid,name,age from nurse;";
      ResultSet rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
          int id=rs1.getInt("nid");
          String name1=rs1.getString("name");
          int age=rs1.getInt("age");
          String nid=Integer.toString(id);
          String age1=Integer.toString(age);
          nurse[i][0]=nid;
          nurse[i][1]=name1;
          nurse[i][2]=age1;
          i++;
      }
      
    nurseList=new JTable(nurse,colHeads);
    nurseList.setBounds(70,100,350,250);
    add(nurseList);
    
    jsp=new JScrollPane(nurseList);
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
     
      bok.addActionListener(new ok());
    
    }
    
    
    class ok implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  ViewNurseOptions(name);
            setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent ae)
    {}
    
    /*public static void main(String[] args)
    {
     String n="xyz";
     new ViewDoctorList(n);
    }*/
}
      

   