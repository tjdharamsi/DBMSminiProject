
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PatOptions extends JFrame implements ActionListener
{
	JButton badd,bmod,bview,bback,bexit,bdelete;
	JLabel linfo,linfo1,linfo2,linfo3,linfo4;
	String name;
	
    PatOptions(String x)
	{
		super("Logged in as " +x);
		name=x;
		setSize(1024,768);
		setVisible(true);
		setLayout(null);

		linfo=new JLabel("Patient's   Information");
		linfo.setBounds(445,30,210,20);
		add(linfo);

		linfo1=new JLabel("1.  Add  Patients  Information");
		linfo1.setBounds(200,150,210,20);
		add(linfo1);

		badd=new JButton("Add Data",new ImageIcon("images/add.gif"));
		badd.setBounds(340,180,180,30);
		add(badd);

		linfo2=new JLabel("2.  Modify  Patients  Information");
		linfo2.setBounds(200,250,210,20);
		add(linfo2);

		bmod=new JButton("Modify Data",new ImageIcon("images/bModify.png"));
		bmod.setBounds(340,280,180,30);
		add(bmod);
		
		linfo3=new JLabel("3. View  Patients  Information");
		linfo3.setBounds(200,350,210,30);
		add(linfo3);

		bview=new JButton("View Data",new ImageIcon("images/search.png"));
		bview.setBounds(340,380,180,30);
		add(bview);
                
                linfo4=new JLabel("4. Delete Patient");
                linfo4.setBounds(200,450,210,30);
                add(linfo4);
                
                bdelete=new JButton("Delete Data",new ImageIcon("images/Delemp.gif"));
                bdelete.setBounds(340,480,180,30);
                add(bdelete);
		
		bback=new JButton("BACK",new ImageIcon("images/restore.png"));
		bback.setBounds(400,545,100,30);
		add(bback);
		
		badd.addActionListener(new add());
		bmod.addActionListener(new mod());
		bview.addActionListener(new view());
		bback.addActionListener(new back());
                bdelete.addActionListener(new delete());
				
	}

	public void actionPerformed(ActionEvent ae)
		{}

	class back implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new  PatientType(name);
			setVisible(false);
		}
	}



	class add implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
					new PatientAdd(name);
					setVisible(false);
				}
		}

	class mod implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
					new PatientModify(name);
					setVisible(false);
				}
		}

        
        class delete implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
					new PatientDelete(name);
					setVisible(false);
				}
		}
        
        
	class view implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
					new ViewPatientOptions(name);
					setVisible(false);
				}
		}


	
}






