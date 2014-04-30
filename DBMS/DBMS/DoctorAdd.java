import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class DoctorAdd extends JFrame implements ActionListener
{
    
    JLabel lmain,lname,ltype,lspecial,lwork,lworkfrom,lworkto,lqual,larea,lcity,lnurse,lphone,lph1,lph2;
    JTextField tfname,tftype,tfworkf,tfworkt,tfqual,tfarea,tfcity,tfph1,tfph2;
    TextArea taspecial;
    Choice chnurse;
    JButton bsub,bclr,bback,bhome;
   String name1,type,workf,workt,special,ph1,ph2,nid;
    String name,area,city;
    String qualific ="MBBS, MD";
    int workhrs,nid1,userid;
    
    DoctorAdd(String x)
    {
    super("Logged in as " +x);
    name=x;
    setSize(768,768);
    setVisible(true);
    setLayout(null);

    lmain=new JLabel("Add Doctor Information");
    lmain.setBounds(40,35,160,15);
    add(lmain);


    lname=new JLabel("Name :");
    lname.setBounds(100,80,70,25);
    add(lname);

    tfname=new JTextField(30);
    tfname.setBounds(250,80,250,20);
    add(tfname);

    lspecial=new JLabel("Specialization :");
    lspecial.setBounds(100,130,140,20);
    add(lspecial);

    taspecial=new TextArea();
    taspecial.setBounds(250,130,250,100);
    add(taspecial);

    lqual=new JLabel("Qualification:");
    lqual.setBounds(100,250,100,20);
    add(lqual);

    tfqual=new JTextField(60);
    tfqual.setBounds(250,250,80,20);
    add(tfqual);
    
    
    ltype=new JLabel("Doctor Type:");
    ltype.setBounds(100,300,100,20);
    add(ltype);

    tftype=new JTextField(70);
    tftype.setBounds(250,300,80,20);
    add(tftype);

    lwork=new JLabel("Working hours(24hr Format) :");
    lwork.setBounds(100,350,200,20);
    add(lwork);

    lworkfrom=new JLabel("From :");
    lworkfrom.setBounds(300,350,40,20);
    add(lworkfrom);

    tfworkf=new JTextField(30);
    tfworkf.setBounds(350,350,30,20);
    add(tfworkf);
    
    lworkto=new JLabel("to :");
    lworkto.setBounds(390,350,40,20);
    add(lworkto);
    
    tfworkt=new JTextField(30);
    tfworkt.setBounds(450,350,30,20);
    add(tfworkt);
    
    larea=new JLabel("Area:");
    larea.setBounds(100,400,100,20);
    add(larea);
    
    tfarea=new JTextField(70);
    tfarea.setBounds(250,400,80,20);
    add(tfarea);
    
    lcity=new JLabel("City:");
    lcity.setBounds(100,450,100,20);
    add(lcity);
    
    tfcity=new JTextField(70);
    tfcity.setBounds(250,450,80,20);
    add(tfcity);
    
    lnurse=new JLabel("Assisted By Nurse ID:");
    lnurse.setBounds(100,500,150,20);
    add(lnurse);
     
    chnurse=new Choice();
    chnurse.setBounds(250,500,50,20);
    add(chnurse);
    
    lphone=new JLabel("Contact Information");
    lphone.setBounds(100,550,150,20);
    add(lphone);
    
    lph1=new JLabel("1.");
    lph1.setBounds(250,550,20,20);
    add(lph1);
            
    tfph1=new JTextField(70);
    tfph1.setBounds(270,550,100,20);
    add(tfph1);
    
    lph2=new JLabel("2.");
    lph2.setBounds(370,550,20,20);
    add(lph2);
    
    tfph2=new JTextField(70);
    tfph2.setBounds(390,550,100,20);
    add(tfph2);
    
    
    bsub=new JButton("ADD",new ImageIcon("images/add.gif"));
    bsub.setBounds(130,600,100,30);
    add(bsub);  

    bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
    bclr.setBounds(250,600,100,30);
    add(bclr);

    bback=new JButton("BACK",new ImageIcon("images/restore.png"));
    bback.setBounds(370,600,100,30);
    add(bback);
    
    bhome=new JButton("HOME",new ImageIcon("images/branch.png"));
    bhome.setBounds(490,600,100,30);
    add(bhome);


    bclr.addActionListener(new clear());
    bsub.addActionListener(new submit());
    bback.addActionListener(new back());
    bhome.addActionListener(new home());
    
    try
    {
        Connection c1 = null;
          Statement stmt1 = null;
          Class.forName("org.postgresql.Driver");
          c1=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
          c1.setAutoCommit(false);
          stmt1 = c1.createStatement();
          ResultSet rs = stmt1.executeQuery("SELECT (nid) FROM Nurse;");
          while(rs.next())
          {
           int j=rs.getInt("nid");
           String s=Integer.toString(j);
           chnurse.addItem(s);
          }    
          rs.close();
          stmt1.close();
          c1.commit();
          c1.close();
          
       }
    catch (Exception e)
        {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());                
        }

    }
    
    public void actionPerformed(ActionEvent ae)
    {}

class clear implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            
            tfname.setText("");
            tftype.setText("");
            tfworkf.setText("");
            tfworkt.setText("");
            taspecial.setText("");
            tfarea.setText("");
            tfcity.setText("");
            tfqual.setText("");
            
        }
    }


class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new DoctOptions(name);
            setVisible(false);
        }
    }
    
    class home implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new Input(name);
            setVisible(false);
        }
    }



class submit implements ActionListener
{
    public void actionPerformed(ActionEvent ae)
    {

      name1=tfname.getText().trim();
      type=tftype.getText().trim();
      qualific=tfqual.getText().trim();
      workf=tfworkf.getText().trim();
      workt=tfworkt.getText().trim();
      special=taspecial.getText().trim();
      area=tfarea.getText().trim();
      city=tfcity.getText().trim();
      ph1=tfph1.getText().trim();
      ph2=tfph2.getText().trim();
      nid=chnurse.getSelectedItem();
      nid1=Integer.parseInt(nid);
      
              try
              {
               if(name1.equals("")||(type.equals(""))||(qualific.equals(""))||(workf.equals(""))||(workt.equals(""))||(special.equals(""))||(area.equals(""))||(city.equals(""))||(ph1.equals("")))    
               {
                   new ErrorDialog1();
               }
               else
               {    
                int i=0;
                Connection c = null;
                Statement stmt = null;
                Class.forName("org.postgresql.Driver");
                c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
                c.setAutoCommit(false);
                stmt = c.createStatement();
                int a=Integer.parseInt(workt);
                int b=Integer.parseInt(workf);
                int hrs =b-a;
                if(hrs<0)
                hrs = hrs+24;
                ResultSet rs1 = stmt.executeQuery("SELECT (did) FROM Doctor where did=(select max(did) from Doctor);");
                while(rs1.next())
                {
                 int l=rs1.getInt("did");
                 if(l!=0)
                 i=l;
                }     
                int k=i+1;
                String sql ="INSERT INTO Doctor VALUES("+k+",'"+name1+"','"+type+"','"+qualific+"','"+special+"',"+hrs+",'"+area+"','"+city+"',"+nid1+");";
                stmt.executeUpdate(sql); 
                String sql1="Insert into Doc_phone values("+k+",'"+ph1+"');";
                stmt.executeUpdate(sql1);
                if(!(ph2.equals("")))
                {
                 String sql2="Insert into Doc_phone values("+k+",'"+ph2+"');";  
                 stmt.executeUpdate(sql2);
                }
                String sql3="Select (userid) from admin where name='"+name+"';";
                ResultSet rs2=stmt.executeQuery(sql3);
                while(rs2.next())
                {
                    userid=rs2.getInt("userid");
                }
                String sql4="Insert into assigns values("+userid+","+k+");";
                stmt.executeUpdate(sql4);
                JOptionPane.showMessageDialog(null,"Doctor added Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
                rs1.close();
                rs2.close();
                stmt.close();
                c.commit();
                c.close();
                new DoctOptions(name);
                setVisible(false);
               }
              }
              catch (Exception e)
              {
               e.printStackTrace();
               System.err.println(e.getClass().getName()+": "+e.getMessage());
               new DoctOptions(name);
                setVisible(false);
              }
        }
 }
}


 
        







