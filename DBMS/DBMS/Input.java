import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Input extends JFrame implements ActionListener
{

    JButton bpat,bdoc,bbill,bback,bexit,baddnurse,badduser;
    JLabel linfo,linfo1,linfo2,linfo3,linfo4,linfo5,loginname;
    String name;
    
    Input(String x)
    {
         super("Logged in as " +x);
         name=x;
        setSize(768,1024);
        setVisible(true);
        setLayout(null);
        
        linfo=new JLabel("SELECT THE APPROPRIATE OPTION");
        linfo.setBounds(30,137,210,20);
        add(linfo);

        linfo1=new JLabel("1. For Inserting,  Modifying,  Retrieving Patients related Data");
        linfo1.setBounds(50,205,335,20);
        add(linfo1);
        
        linfo2=new JLabel("2. For Inserting,  Modifying,  Retrieving Doctors related Data");
        linfo2.setBounds(50,275,335,20);
        add(linfo2);

        linfo3=new JLabel("3. Billing   Details");
        linfo3.setBounds(50,345,150,20);
        add(linfo3);
        
        linfo4=new JLabel("4. For Inserting,  Modifying,  Retrieving Nurse related Data");
        linfo4.setBounds(50,415,335,20);
        add(linfo4);
        
        linfo5=new JLabel("New User");
        linfo5.setBounds(500,60,100,20);
        add(linfo5);
        
        badduser=new JButton("Add",new ImageIcon("images/4109.gif"));
        badduser.setBounds(500,90,150,35);
        add(badduser);
        
        bpat=new JButton("Patient", new ImageIcon("images/Advances.png"));
        bpat.setBounds(430,200,180,30);
        add(bpat);

        bdoc=new JButton("Doctor",new ImageIcon("images/doctor.png"));
        bdoc.setBounds(430,270,180,30);
        add(bdoc);

        bbill=new JButton("Billing",new ImageIcon("images/Attendance.png"));
        bbill.setBounds(430,340,180,30);
        add(bbill);
        
        baddnurse=new JButton("Nurse",new ImageIcon("images/9234.gif"));
        baddnurse.setBounds(430,410,180,30);
        add(baddnurse);
        
        bback=new JButton("LogOut" ,new ImageIcon("images/LOGGOFF.png"));
        bback.setBounds(230,550,100,30);
        add(bback);
        
        bexit=new JButton("EXIT" ,new ImageIcon("images/exits.png"));
        bexit.setBounds(630,550,100,30);
        add(bexit);
        
        bpat.addActionListener(new patient());
        bdoc.addActionListener(new doctor());
        bbill.addActionListener(new billing());
        bexit.addActionListener(new exit());
        bback.addActionListener(new back());
        baddnurse.addActionListener(new nurse());
        badduser.addActionListener(new user());
                    
    }

    public void actionPerformed(ActionEvent ae)
        {}

    class back implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  LoginPage();
            setVisible(false);
        }
    }
    
    class user implements ActionListener
    {
      public void actionPerformed(ActionEvent ae)
      {
        new NewUser(name);
         setVisible(false);
      }
     }



    class patient implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
                {
                   
                    new PatientType(name);
                    setVisible(false);
                }
        }

    class doctor implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
                {
                    new DoctOptions(name);
                    setVisible(false);
                }
        }

    class billing implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
                {
                    new BillingInput(name);
                    setVisible(false);
                }
        }
    
    class nurse implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new NurseOptions(name);
            setVisible(false);
        }
    }


    class exit implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
                {
                  JOptionPane.showMessageDialog(null,"Have a nice day" , "Thank you!!!",JOptionPane.INFORMATION_MESSAGE);
                  System.exit(0);
                }
        }


}











