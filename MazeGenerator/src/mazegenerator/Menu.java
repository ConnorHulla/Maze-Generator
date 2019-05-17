//Connor Hulla
package mazegenerator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Menu extends JFrame {
    
    private JButton submit;                       //enter button
    private JTextField rowField, columnField;     //text field for rows/columns
    private JLabel rowLabel, columnLabel;
    
    Menu() {
        createGUI();
        
         //set the properties of our GUI
        setTitle("Enter the rows/columns");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void createGUI() {
        
        //create the panel (Flow layout)
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        
        //create the row/column labels
        rowLabel = new JLabel("Enter the rows:        ");
        columnLabel = new JLabel("Enter the columns: ");
        
        //create the textfields
        rowField = new JTextField();
        rowField.setPreferredSize(new Dimension(100, 20));
        columnField = new JTextField();
        columnField.setPreferredSize(new Dimension(100, 20));
        
        //add the label and the textfields to our panel
        panel.add(rowLabel);
        panel.add(rowField);
        panel.add(columnLabel);
        panel.add(columnField);
        
        //add the button and put it on our panel
        submit = new JButton("Submit");
        submit.addActionListener(new SubmitListener());
        panel.add(submit);

    }
    
    public static void main(String[] args) {
                 //this will invoke our constructor
        SwingUtilities.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                new Menu().setVisible(true);
            }
            
        });
        
    }
    
     private class SubmitListener implements ActionListener {
       String r, c;
       int rownum = 0, colnum = 0;
       @Override
       public void actionPerformed(ActionEvent e) {
           r = rowField.getText();
           c = columnField.getText(); //.getText() returns the text in our field
           
           
           /*if Integer.parseInt() throws an exception error, handle it with
           an error screen */
           
           try { 
               rownum = Integer.parseInt(r);
               colnum = Integer.parseInt(c);
           }
           catch(NumberFormatException E){
               JOptionPane.showMessageDialog(new JFrame(),
               "Enter Integer Values in the text fields", "Error",
                JOptionPane.WARNING_MESSAGE);
               rownum = 0;
               colnum = 0;
               return;
           }
           
           
           
           /*a maze with rows/columns less than 2 is worthless, don't allow
           the user to enter a maze smaller than 2x2 */
           if(rownum < 2 || colnum < 2) {
                JOptionPane.showMessageDialog(new JFrame(),
                 "Your row/column numbers must be greater than 1");
                
                return;
           }
               
               
           
           System.out.println("Rows: " + rownum + " Columns: " + colnum);
       }
    }
    
}
