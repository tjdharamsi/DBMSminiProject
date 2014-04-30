import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
class  LoginPage extends JFrame implements ActionListener
{
     static JFrame frame;
    private String username;
    private String password;
    private static JFrame loginFrame;
    private static JPanel panel1;
    private static JPanel panel2;
    private static JPanel panel3;
     private static JPanel panel4;
    private JButton loginBtn;
    private JButton exitBtn;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    String dialogmessage;
    String dialogs;
      private JLabel nameLbl;
       private JLabel nameLbl1;
    private JLabel userLbl;
    private JLabel loading;
    private JLabel passwordLbl;
    private static JTextField userTxt;
    private static JPasswordField passwordTxt;
    public String loginname;
     Container pane;
    public String loginpass;
   Dimension screen     =   Toolkit.getDefaultToolkit().getScreenSize();
   boolean check= false;
    LoginPage()
    {

        
      
    
    panel4 = new JPanel();
   panel4.setLayout(new FlowLayout());
   nameLbl1 = new JLabel("Admin Login Page");
    
   panel2 = new JPanel();
   panel2.setLayout(new GridLayout(2,2));
   userLbl = new JLabel("Username :");
   userTxt = new JTextField(20);
  
   passwordLbl = new JLabel("Password :");
   passwordTxt = new JPasswordField(20);
   
   panel3 = new JPanel();
   panel3.setLayout(new FlowLayout());
   
   loginBtn = new JButton("Login", new ImageIcon("images/key.gif"));
   loading = new JLabel (new ImageIcon("images/mini.gif"));
   loginBtn.addActionListener(this);
   exitBtn = new JButton("Exit", new ImageIcon("images/Delemp.gif"));
   
   exitBtn.addActionListener(this);
    panel4.add(nameLbl1);
    panel2.add(userLbl);
    panel2.add(userTxt);
    panel2.add(passwordLbl);
    panel2.add(passwordTxt);
   panel2.setOpaque(true);  //to make the text fields empty but it is not necessary also
    panel3.add(loginBtn);
    panel3.add(exitBtn);
    frame = new JFrame("PES Global Hospital");
       frame.setSize(300,200);
        
     pane = frame.getContentPane();   
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
    pane.add(panel4);
    pane.add(panel2);
    pane.add(panel3);
    frame.setLocation((screen.width - 500)/2,((screen.height-350)/2));  
    frame.setVisible(true);
    }
     
    public void actionPerformed(ActionEvent event)
    {
      Object source = event.getSource();
                 
                 
        if(source.toString().equals(loginBtn.toString()))
        {
         loginname = userTxt.getText().trim();
         loginpass = passwordTxt.getText().toString();
         Connection c=null;
         Statement stmt=null;
         String Do=null;
         try
         {
          Class.forName("org.postgresql.Driver");
          c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hospital","postgres", "tejasd");
          c.setAutoCommit(false);
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM Admin where name ilike '"+loginname+"';" );
          while(rs.next())
          {
           String pass=rs.getString("password");
           if(!(pass.equals(null)))
               Do=pass;
 
          }    
          rs.close();
          stmt.close();
          c.close();
        }
        
        catch (Exception e)
        {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());                
        }
            
         if(loginpass.equals(Do))
         {
          dialogmessage = "Welcome - " +loginname;
          dialogtype = JOptionPane.INFORMATION_MESSAGE;
          new Loading(loginname);
          setVisible(false);
          frame.dispose();
         }
                    
         else{
              JOptionPane.showMessageDialog(null,"Invaild User name and password" , "WARNING!!!",JOptionPane.ERROR_MESSAGE);
              userTxt.setText("");
              passwordTxt.setText("");
             }
                        
       } 
        
        else if(source.equals(exitBtn))
        {
         JOptionPane.showMessageDialog(null,"Have a nice day" , "Thank you!!!",JOptionPane.INFORMATION_MESSAGE);
         System.exit(0);
        }
       }
      
    public static void main(String[] args)
    {
        new LoginPage();
    }
}


