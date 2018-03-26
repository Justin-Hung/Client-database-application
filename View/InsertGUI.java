package View;
import Client.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Insert view GUI for inserting new clients into application 
 * @author Justin, Robert 
 */
public class InsertGUI extends JFrame {
	
	/**
	 * unique id list which stores all unique id and generates new ones 
	 */
	private UniqueId idList;
	
	/**
	 * container for insert view 
	 */
	private JPanel calcPanel = new JPanel(); 
	
	/**
	 * first name label 
	 */
	private JLabel firstNameLabel = new JLabel ("Enter First Name");
	
	/**
	 * last name label 
	 */
	private JLabel lastNameLabel = new JLabel ("Enter Last Name");
	
	/**
	 * address label
	 */
	private JLabel addressLabel = new JLabel ("Enter Address");
	
	/**
	 * postal code label 
	 */
	private JLabel postalLabel = new JLabel ("Postal Code");
	
	/**
	 * phone number label 
	 */
	private JLabel phoneLabel = new JLabel ("Phone Number");
	
	/**
	 * client type label 
	 */
	private JLabel typeLabel = new JLabel ("Client Type");
	
	/**
	 * first name text field 
	 */
	private JTextField firstName = new JTextField(10);
	
	/**
	 * last name text field 
	 */
	private JTextField lastName = new JTextField(10);
	
	/**
	 * address text field 
	 */
	private JTextField address = new JTextField(10);
	
	/**
	 * postal code text field 
	 */
	private JTextField postalCode = new JTextField(10);
	
	/**
	 * phone number text field 
	 */
	private JTextField phone = new JTextField(10);
	
	/**
	 * character type text field
	 */
	private JTextField type = new JTextField(10);
	
	/**
	 * insert button 
	 */
	private JButton insertButton = new JButton("Insert");
	
	/**
	 * exit button 
	 */
	private JButton leaveButton = new JButton("return to Main Window");
	
	/**
	 * contructs insert view for application 
	 */
	public InsertGUI() 
	{
		setTitle ( "Insert");
		JPanel subPanel1 = new JPanel();
		subPanel1.add( "South", insertButton);
		subPanel1.add( "South", leaveButton);
		add(subPanel1, BorderLayout.SOUTH);
		setSize(500, 200);

		calcPanel.add(firstNameLabel);
		calcPanel.add(firstName);
		calcPanel.add(lastNameLabel);
		calcPanel.add(lastName);
		calcPanel.add(addressLabel);
		calcPanel.add(address);
		calcPanel.add(postalLabel);
		calcPanel.add(postalCode);
		calcPanel.add(phoneLabel);
		calcPanel.add(phone);
		calcPanel.add(typeLabel);
		calcPanel.add(type);	
		add(calcPanel);
		setVisible(true);
	}
	
	/**
	 * turn insertview off 
	 */
	public void turnOff() {
		setVisible(false);
	}
	
	/**
	 * add insert action listener 
	 * @param listenForInsertButton 
	 */
	public void addInsertActionListener(ActionListener listenForInsertButton) {
		insertButton.addActionListener(listenForInsertButton);
	}
	
	/**
	 * check postal code format 
	 * @return false if wrong format, true if correct format 
	 */
	public boolean checkPostal() 
	{
		if(postalCode.getText().length() != 7)
		{
			return false; 
		}
		
		String checkPostal = postalCode.getText();
		checkPostal = checkPostal.replaceAll("\\s+", "");

		for (int i = 0; i < checkPostal.length(); i=i+2)
		{
			if (!Character.isAlphabetic(checkPostal.charAt(i))) 
			{
				System.out.println(checkPostal.charAt(i));
				return false;
			}
		}	
		for (int j = 1; j < checkPostal.length(); j=j+2)
		{
			if (!Character.isDigit(checkPostal.charAt(j)))
			{
				System.out.println(checkPostal.charAt(j));
				return false;
			}
		}
	
		return true;
	}
		
	/**
	 * check phone number format 
	 * @return false if wrong format, true if correct format 
	 */
	public boolean checkPhone() 
	{
		String checkPhone = phone.getText();

		int i = 0; 
		while (true)
		{
			if (!Character.isDigit(checkPhone.charAt(i))) 
			{
				return false;
			}
			if ( i == 11) {
				break;
			}
			i++; 
			if (i==3 || i==7) {
				i++;
			}
		}
		
		if (checkPhone.charAt(3) != '-' || checkPhone.charAt(7) != '-') {
			return false; 
		}
		
		if (checkPhone.length() != 12)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * check empty fields 
	 * @return return true if one field empty, false if nothing empty 
	 */
	public boolean checkEmptyFields()
	{
		boolean checkEmpty = (firstName.getText().equals("") || lastName.getText().equals("") 
				|| address.getText().equals("") || postalCode.getText().equals("") || phone.getText().equals("") || type.getText().equals(""));
		return checkEmpty; 
	}
	
	/**
	 * check client type for format 
	 * @return true if wrong format, false if correct format 
	 */
	public boolean checkClientType() 
	{
		if (type.getText().length() != 1 )
		{
			return true; 
		}
		boolean checkClient = (!type.getText().equals("C") && !type.getText().equals("R"));
		return checkClient; 
	}
	
	/**
	 * display error for wrong postalcode formating 
	 */
	public void displayPostalFieldError()
	{
		JOptionPane.showMessageDialog(null, "Postal must be in A1A 1A1 format",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * display error for wrong phone number formating 
	 */
	public void displayPhoneError()
	{
		JOptionPane.showMessageDialog(null, "Phone number must be in 111-111-1111 format",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * display error for empty fields 
	 */
	public void displayEmptyFieldError()
	{
		JOptionPane.showMessageDialog(null, "Please fill every field",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * display error for wrong client type formating 
	 */
	public void displayWrongClientError() 
	{
		JOptionPane.showMessageDialog(null, "Please make sure client type is either 'C' or 'R' ",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * display error for wrong length formating 
	 */
	public void displayLengthError() 
	{
		JOptionPane.showMessageDialog(null, "The maximum length of first name, last name, and address, are 20, 20, 50, \n" + 
				"respectively.",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * check length of first name, last name and address
	 * @return false if wrong format, true if correct 
	 */
	public boolean checkLength() {
		if(firstName.getText().length() > 20)
		{
			return false; 
		}
		if(lastName.getText().length() > 20)
		{
			return false; 
		}
		if(address.getText().length() > 50)
		{
			return false; 
		}
		return true;
	}
	
	/**
	 * set unique id list for insertGUI 
	 * @param list unique id list 
	 */
	public void setIdList (UniqueId list) {
		idList = list; 
	}
	
	/**
	 * create client from text field parameters, generate id and return client 
	 * @return client being inserted
	 */
	public Client getClient() {				
		try {
			Client theClient = new Client(idList.getUniqueId() , firstName.getText() , lastName.getText(), 
					address.getText() , postalCode.getText() , phone.getText() ,type.getText().charAt(0));
			firstName.setText("");
			lastName.setText("");
			address.setText("");
			postalCode.setText("");
			phone.setText("");
			type.setText("");
			setVisible(false);
			return theClient; 
		}		
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(null, "Please make sure client id is a number ",
					"Error", JOptionPane.PLAIN_MESSAGE);
			return null;
		}
	}
}
