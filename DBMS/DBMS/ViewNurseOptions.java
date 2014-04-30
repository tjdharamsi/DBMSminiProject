import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ViewNurseOptions extends JFrame implements ActionListener
{
 JButton bback,bNurseList,bsearchNurse,bhome;
 JLabel lNurseList,lsearchNurse,linfo;
 String name;
 
 ViewNurseOptions(String x)
 {
  super("Logged in as " +x);
  name=x;
  setSize(600,600);
  setVisible(true);
  setLayout(null);

  linfo=new JLabel("SELECT THE APPROPRIATE OPTION");
  linfo.setBounds(100,30,210,20);
  add(linfo);

  lNurseList=new JLabel("1.  View all Nurse List");
  lNurseList.setBounds(80,80,210,20);
  add(lNurseList);
  
  bNurseList=new JButton("View",new ImageIcon("images/4111.gif"));
  bNurseList.setBounds(180,130,180,35);
  add(bNurseList);
  
  lsearchNurse=new JLabel("2. Search particular Nurse details");
  lsearchNurse.setBounds(80,180,210,20);
  add(lsearchNurse);
  
  bsearchNurse=new JButton("Search",new ImageIcon("images/4642.gif"));
  bsearchNurse.setBounds(180,230,180,35);
  add(bsearchNurse);
  
  bback=new JButton("BACK",new ImageIcon("images/restore.png"));
  bback.setBounds(200,350,100,30);
  add(bback);
  
  bhome=new JButton("HOME",new ImageIcon("images/branch.png"));
    bhome.setBounds(320,350,100,30);
    add(bhome);
  
  bsearchNurse.addActionListener(new ViewNurseOptions.searchNurse());
  bNurseList.addActionListener(new ViewNurseOptions.NurseList());
  bback.addActionListener(new ViewNurseOptions.back());
  bhome.addActionListener(new ViewNurseOptions.home());
  
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
    
    class home implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new Input(name);
            setVisible(false);
        }
    }

    
    class searchNurse implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            new  NurseDetails(name);
            setVisible( false);
        }
    }
    
    class NurseList implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            try
            {
                new ViewNurseList(name);
                setVisible(false);
            }
            catch(Exception e)
            {}
        }
    }
    
    
}
