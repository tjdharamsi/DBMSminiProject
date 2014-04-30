import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.*;


class PatientAdd extends JFrame implements ActionListener
{

        JLabel lmain,lpi,lname,lcity,ldocname,lpid,lpno,ldoclist,larea,lmi,lbg,ldob,lhis,lcur,lph,lroom,ldate,lgender,ldtip,ldtip2,ldateadd,lph1,lph2;
	JTextField tfname,tfdob,tfroom,tfarea,tfcity,tfph1,tfph2;
        TextArea tahis,tacur;
	Choice chbg,chdoctor;
	CheckboxGroup cbmf;
	Checkbox cbm,cbf,gender;
	JButton bsub,bclr,bback,bhome,bdoclist;
	String name1,dob,ph1,ph2,dateadd,did,area,city,cur,bloodgp,gender1,doa;
        int userid,pid1,i;
	
		String dialogmessage;
    String dialogs;
     int dialogtype = JOptionPane.PLAIN_MESSAGE;
	 String name;



PatientAdd(String x)
{
	super("Logged in as " +x);
	name=x;
	setSize(1024,768);
	setVisible(true);
	setLayout(null);
	
// PERSONAL INFORMATION

	lmain=new JLabel("Add Patient Information");
	lmain.setBounds(40,40,160,15);
	add(lmain);
	
	lname=new JLabel("Name :");
	lname.setBounds(104,97,70,20);
	add(lname);

	tfname=new JTextField(30);
	tfname.setBounds(270,97,250,20);
	add(tfname);

	larea=new JLabel("Area :");
	larea.setBounds(104,137,70,20);
	add(larea);

	tfarea=new JTextField(70);
	tfarea.setBounds(270,138,180,20);
	add(tfarea);
        
        lcity=new JLabel("City  :");
        lcity.setBounds(104,178,70,20);
        add(lcity);
        
        tfcity=new JTextField(70);
        tfcity.setBounds(270,178,180,20);
        add(tfcity);

	lph=new JLabel("Contact :");
	lph.setBounds(575,138,80,20);
	add(lph);
        
        lph1=new JLabel("1.  ");
        lph1.setBounds(655,138,20,20);
        add(lph1);
        
        tfph1=new JTextField(70);
        tfph1.setBounds(675,138,100,20);
        add(tfph1);
        
        lph2=new JLabel("2.  ");
        lph2.setBounds(780,138,20,20);
        add(lph2);
        
        tfph2=new JTextField(70);
        tfph2.setBounds(800,138,100,20);
        add(tfph2);
		

	lpno=new JLabel("Patient No.:");
	lpno.setBounds(570,97,70,25);
	add(lpno);


	ldate=new JLabel("Date Of Admission :");
	ldate.setBounds(575,180,120,25);
	add(ldate);
	
	try
		{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);  
		 dateadd=df.format(cal.getTime());
		

		ldateadd=new JLabel(dateadd);
		ldateadd.setBounds(696,180,80,25);
		add(ldateadd);

	
		}
		catch (Exception e)
			{
				
			}
			
	ldtip2=new JLabel("(yyyy-mm-dd)");
	ldtip2.setBounds(782,180,100,25);
	add(ldtip2);

	
	lgender=new JLabel("Gender :");
	lgender.setBounds(596,223,50,15);
	add(lgender);


	
	cbmf=new CheckboxGroup();
	cbm=new Checkbox("Male",cbmf,true);
	cbf=new Checkbox("Female",cbmf,false);
	cbm.setBounds(698,223,50,15);
	add(cbm);
	cbf.setBounds(760,223,60,15);
	add(cbf);
	
	

	// End of personal information
	
	//Medical Information

	lmi=new JLabel("Add Medical Information");
	lmi.setBounds(40,268,160,15);
	add(lmi);
	
	lbg=new JLabel("Blood Group :");
	lbg.setBounds(104,306,79,15);
	add(lbg);

	chbg=new Choice();
	chbg.setBounds(270,306,53,15);
	chbg.addItem("A -ve");
	chbg.addItem("A +ve");
	chbg.addItem("B -ve");
	chbg.addItem("B +ve");
	chbg.addItem("AB -ve");
	chbg.addItem("AB +ve");
	chbg.addItem("O +ve");
	chbg.addItem("O -ve");
	add(chbg);
	
	ldob=new JLabel("Date of Birth :");
	ldob.setBounds(575,306,120,15);
	add(ldob);

	tfdob=new JTextField(15);
	tfdob.setBounds(696,305,80,20);
	add(tfdob);


	ldtip=new JLabel("(yyyy-mm-dd)");
	ldtip.setBounds(782,305,100,20);
	add(ldtip);

	lhis=new JLabel("History :");
	lhis.setBounds(104,365,50,15);
	add(lhis);

	tahis=new TextArea();
	tahis.setBounds(270,365,250,100);
	add(tahis);

	lcur=new JLabel("Current Problem :");
	lcur.setBounds(575,365,100,15);
	add(lcur);

	tacur=new TextArea();
	tacur.setBounds(720,365,250,100);
	add(tacur);		

	
	ldocname=new JLabel("Attending Doctor :");
	ldocname.setBounds(575,510,130,20);
	add(ldocname);
        
        chdoctor=new Choice();
        chdoctor.setBounds(710,510,100,20);
        add(chdoctor);
        
        ldoclist=new JLabel("View Doctor List  :");
        ldoclist.setBounds(575,580,200,20);
        add(ldoclist);
        
        bdoclist=new JButton("View",new ImageIcon("images/4111.gif"));
        bdoclist.setBounds(730,575,100,30);
        add(bdoclist);
        
        

//End of medical Information
        bhome=new JButton("HOME",new ImageIcon("images/branch.png"));
	bhome.setBounds(580,643,100,30);
	add(bhome);
	
	bsub=new JButton("ADD",new ImageIcon("images/add.gif"));
	bsub.setBounds(250,643,100,30);
	add(bsub);	

	bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
	bclr.setBounds(360,643,100,30);
	add(bclr);

	bback=new JButton("BACK",new ImageIcon("images/restore.png"));
	bback.setBounds(470,643,100,30);
	add(bback);	

	bclr.addActionListener(new clear());
	bsub.addActionListener(new submit());
	bback.addActionListener(new back());
	bhome.addActionListener(new home());
        bdoclist.addActionListener(new doclist());

	try
        {
          Connection c1 = null;
          Statement stmt1 = null;
          Class.forName("org.postgresql.Driver");
          c1=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
          c1.setAutoCommit(false);
          stmt1 = c1.createStatement();
          ResultSet rs = stmt1.executeQuery("SELECT (did) FROM Doctor;");
          while(rs.next())
          {
           int j=rs.getInt("did");
           String s=Integer.toString(j);
           chdoctor.addItem(s);
          }    
           ResultSet rs1 = stmt1.executeQuery("SELECT (pid) FROM Patient where pid=(select max(pid) from Patient);");
           while(rs1.next())
           {
            int l=rs1.getInt("pid");
            if(l!=0)
              i=l;
            }     
             pid1=i+1;
             String pid=Integer.toString(pid1); 
             lpid=new JLabel(pid);
             lpid.setBounds(650,100,70,20);
             add(lpid);
           rs1.close();
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

//End of constructor

	

public void actionPerformed(ActionEvent ae)
	{}



class clear implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{

			tfname.setText("");
			tfph1.setText("");
             tfph2.setText("");
			tfdob.setText("");
			tfarea.setText("");
            tfcity.setText("");
			tahis.setText("");
			tacur.setText("");
		}
	}


class back implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new PatOptions(name);
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

        
        class doclist implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new PatientAddDocList(name);
		}
	}

  
class submit implements ActionListener
{
	
	public void actionPerformed(ActionEvent ae)
	{
	
	  name1=tfname.getText().trim();
	  ph1=tfph1.getText().trim();
          ph2=tfph2.getText().trim();
	  dob=tfdob.getText().trim();
	  gender=cbmf.getSelectedCheckbox();
	  did=chdoctor.getSelectedItem();
	  area=tfarea.getText().trim();
          city=tfcity.getText().trim();
	  cur=tacur.getText().trim();
	  bloodgp=(String)chbg.getSelectedItem();
          if(gender.equals(cbm))
              gender1="Male";
          else
              gender1="Female";
	  try
          {
	    if((name1.equals(""))||(ph1.equals(""))||(dob.equals(""))||(area.equals(""))||(cur.equals(""))||(city.equals("")))
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
                String sql3="Select (userid) from admin where name='"+name+"';";
                ResultSet rs2=stmt.executeQuery(sql3);
                while(rs2.next())
                {
                    userid=rs2.getInt("userid");
                }
                
                String sql ="INSERT INTO Patient VALUES("+pid1+",'"+name1+"','"+bloodgp+"','"+dob+"','"+area+"','"+city+"',"+did+","+userid+",'"+dateadd+"','"+gender1+"');";
                stmt.executeUpdate(sql); 
                System.out.println(sql);
                String sql1="Insert into pat_phone values("+pid1+",'"+ph1+"');";
                stmt.executeUpdate(sql1);
                if(!(ph2.equals("")))
                {
                 String sql2="Insert into pat_phone values("+pid1+",'"+ph2+"');";  
                 stmt.executeUpdate(sql2);
                }
                String sql4 ="Insert Into Out_Patient values("+pid1+",'"+cur+"');";
                stmt.executeUpdate(sql4);
                System.out.println(sql4);
                JOptionPane.showMessageDialog(null,"Patient added Successfully!!!" , "Done!!!",JOptionPane.INFORMATION_MESSAGE);
                rs2.close();
                stmt.close();
                c.commit();
                c.close();
               new PatOptions(name);
	       setVisible(false);
               }
              }
              catch (Exception e)
              {
               e.printStackTrace();
               System.err.println(e.getClass().getName()+": "+e.getMessage());
               new PatOptions(name);
                setVisible(false);
              }
            
       
          }
       
	  }
	 }
	
	
	
		






