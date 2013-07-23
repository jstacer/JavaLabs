package lab6;

import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentGUI extends JFrame {
	//instantiate JTextField objects for p2
	private JTextField id = new JTextField("must enter id #");
	private JTextField firstName = new JTextField();
	private JTextField lastName = new JTextField();
	private JTextField street = new JTextField();
	private JTextField city = new JTextField();
	private JTextField state = new JTextField();
	private JTextField zip = new JTextField();
	private JTextField email = new JTextField();
	private JTextField gpa = new JTextField();

	//instantiate JButton objects for p3
	private JButton find = new JButton("Find");
	private JButton insert = new JButton("Insert");
	private JButton delete = new JButton("Delete");
	private JButton update = new JButton("Update");
	private JButton exit = new JButton("Exit");
	
	//put with fields so setMessage() can access it
	public JLabel p3text;
	
	//constructor creates/positions panels; adds buttons and text fields
	public StudentGUI() {
		
		//JLabel goes in top part of form on p1
		JLabel label = new JLabel("Student Information");
		label.setFont(new Font("Georgia", Font.BOLD, 24));
		label.setForeground(Color.BLUE);	
		JPanel p1 = new JPanel();
		p1.add(label);
	
		//p2 adds textfields (w/tooltips) with corresponding JLabels
		JPanel p2 = new JPanel(new GridLayout(9, 2));
		
		p2.add(new JLabel("ID number"));
		p2.add(id);
		id.setToolTipText("Enter your student ID number");
		
		p2.add(new JLabel("First name"));
		p2.add(firstName);
		firstName.setToolTipText("Enter your first name");
		
		p2.add(new JLabel("Last name"));
		p2.add(lastName);
		lastName.setToolTipText("Enter your last name");
		
		p2.add(new JLabel("Street"));
		p2.add(street);
		street.setToolTipText("Enter your street address");
		
		p2.add(new JLabel("City"));
		p2.add(city);
		city.setToolTipText("Enter your city");
		
		p2.add(new JLabel("State"));
		p2.add(state);
		state.setToolTipText("Enter your street");
		
		p2.add(new JLabel("ZIP"));
		p2.add(zip);
		zip.setToolTipText("Enter your zip code");
		
		p2.add(new JLabel("Email"));
		p2.add(email);
		email.setToolTipText("Enter your email address");
		
		p2.add(new JLabel("GPA"));
		p2.add(gpa);
		gpa.setToolTipText("Isn't Java exciting?");
	
		//p3, which has two sections, p3text and p3buttons
		JPanel p3 = new JPanel(new BorderLayout());
		
		//p3text is for messages during use
		p3text = new JLabel(" ");
		p3text.setFont(new Font("Georgia", Font.PLAIN, 12));
		p3text.setHorizontalAlignment(SwingConstants.CENTER);
		p3text.setForeground(Color.RED);	
		//add p3text to p3
		p3.add(p3text, BorderLayout.NORTH);
		
		//p3buttons contains buttons
		JPanel p3buttons = new JPanel(new FlowLayout());
		p3buttons.add(find);
		p3buttons.add(insert);
		p3buttons.add(delete);
		p3buttons.add(update);
		p3buttons.add(exit);
		//add p3buttons to p3
		p3.add(p3buttons, BorderLayout.SOUTH);
		
		//add JPanels p1 p2 p3 to JFrame
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
	
		//register listener for Buttons
		find.addActionListener(new ButtonListener());
		insert.addActionListener(new ButtonListener());
		delete.addActionListener(new ButtonListener());
		update.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
	
	} //end constructor
	
	
	//accepts string to print to p3text JLabel
	public void setMessage(String msg){
		p3text.setText(msg);
	}
	
	
	//main method draws JFrame
	public static void main(String[] args) {
		StudentGUI frame = new StudentGUI();
		frame.pack();
		frame.setTitle("StudentGUI");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
	}//end main
	
	
	//nested listener class defines all button actions
	private class ButtonListener implements ActionListener {
		//Student class object to call methods with
		Student s1 = new Student();
		//stores message returned by Student methods
		String msg;
		//used to format GPA to 1 decimal place
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		
		//fills gui JTextFields with Student class fields
		public void fillFields(){
			id.setText(Integer.toString(s1.id));
			firstName.setText(s1.firstName);
			lastName.setText(s1.lastName);
			street.setText(s1.street);
			city.setText(s1.city);
			state.setText(s1.state);
			zip.setText(Integer.toString(s1.zip));
			email.setText(s1.email);
			gpa.setText(oneDigit.format(s1.gpa));
		}
		
		//empties all JTextfields
		public void clearFields(){
			id.setText("");
			firstName.setText("");
			lastName.setText("");
			street.setText("");
			city.setText("");
			state.setText("");
			zip.setText("");
			email.setText("");
			gpa.setText("");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//actions for exit button
			if(e.getSource() == exit){
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", 
						"Confirm Exit", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {}
			}
	
			//actions for find button
			if(e.getSource() == find){
				try{
					msg = s1.find(Integer.parseInt(id.getText()));
					//print message
					p3text.setText(msg);
					fillFields();
				}catch(NumberFormatException nfe){
					p3text.setText("ID must be an integer.");
				}
			}
			
			//actions for insert button
			if(e.getSource() == insert){
				try{
					msg = s1.insert(Integer.parseInt(id.getText()), firstName.getText(),
							lastName.getText(), street.getText(), city.getText(), state.getText(),
							Integer.parseInt(zip.getText()), email.getText(), 
							Double.parseDouble(gpa.getText()));
					
					p3text.setText(msg);
					}catch(NumberFormatException nfe){
						p3text.setText("ID must be an integer.");
					}
			}
			
			//actions for delete button
			if(e.getSource() == delete){
				try{
					msg = s1.deleteDB(Integer.parseInt(id.getText()));
					clearFields();
					p3text.setText(msg);
					}catch(NumberFormatException nfe){
						p3text.setText("ID must be an integer.");
					}
			}
			
			//actions for update button
			if(e.getSource() == update){
				try{
					msg = s1.updateDB(Integer.parseInt(id.getText()), firstName.getText(),
							lastName.getText(), street.getText(), city.getText(), state.getText(),
							Integer.parseInt(zip.getText()), email.getText(), 
							Double.parseDouble(gpa.getText()));
					
					p3text.setText(msg);
					}catch(NumberFormatException nfe){
						p3text.setText("ID must be an integer.");
					}
			}
			
		} //end actionPerformed()
		
	} //end ButtonListener nested class
	
} //end class StudentGUI