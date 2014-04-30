import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NurseOptions extends JFrame implements ActionListener
{
	JButton badd,bmod,bview,bback,bexit,bdelete;
	JLabel linfo,linfo1,linfo2,linfo3,linfo4;
	String name;
	
	NurseOptions(String x)
	{
		super("Logged in as " +x);
		name=x;
		setSize(1024,768);
		setVisible(true);
		setLayout(null);

		linfo=new JLabel("Nurse's   Information");
		linfo.setBounds(445,30,210,20);
		add(linfo);

		linfo1=new JLabel("1.  Add  Nurse  Information");
		linfo1.setBounds(200,150,210,20);
		add(linfo1);

		badd=new JButton("Add Data",new ImageIcon("images/add.gif"));
		badd.setBounds(340,180,180,30);
		add(badd);

		linfo2=new JLabel("2.  Modify  Nurse  Information");
		linfo2.setBounds(200,250,210,20);
		add(linfo2);

		bmod=new JButton("Modify Data",new ImageIcon("images/bModify.png"));
		bmod.setBounds(340,280,180,30);
		add(bmod);
		
		linfo3=new JLabel("3. View  Nurse  Information");
		linfo3.setBounds(200,350,210,20);
		add(linfo3);

		bview=new JButton("View Data",new ImageIcon("images/search.png"));
		bview.setBounds(340,380,180,30);
		add(bview);
                
                linfo4=new JLabel("4. Delete Nurse");
                linfo4.setBounds(200,450,200,20);
                add(linfo4);
                
                bdelete=new JButton("Delete data",new ImageIcon("images/Delemp.gif"));
                bdelete.setBounds(340,480,180,30);
                add(bdelete);
                
		
		bback=new JButton("BACK",new ImageIcon("images/restore.png"));
		bback.setBounds(400,560,100,30);
		add(bback);
		
		badd.addActionListener(new NurseOptions.add());
		bmod.addActionListener(new NurseOptions.mod());
		bview.addActionListener(new NurseOptions.view());
		bback.addActionListener(new NurseOptions.back());
                bdelete.addActionListener(new NurseOptions.delete());
				
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

        class delete implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new  NurseDelete(name);
			setVisible(false);
		}
	}


	class add implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
				   new	AddNurse(name);
					setVisible(false);
				}
		}

	class mod implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
				   new NurseModify(name);
					setVisible(false);
				}
		}

	class view implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
					new ViewNurseOptions(name);
					setVisible(false);
				}
		}


}





