package View;

import Client.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 * GUI for main view of application. 
 * @author Justin, Robert
 */
public class GUI extends JFrame {
	
	/**
	 * unique id list which stores all unique id and generates new ones 
	 */
	private UniqueId idList; 
	
	/**
	 * remember old client information for updates 
	 */
	private Client oldClient = null;
	
	/**
	 * Interactive client display list for application
	 */
	private JList<Client> list = new JList<>();
	
	/**
	 * Current model being stored in Jlist and being displayed 
	 */
	private DefaultListModel<Client> currentModel = new DefaultListModel<>(); 
	
	/**
	 * holds full list model for JList 
	 */
	private DefaultListModel<Client> fullModel = new DefaultListModel<>();

	/** 
	 * search by id radio button 
	 */
	private JRadioButton byId = new JRadioButton("Client ID", true);
	
	/**
	 * search by last name radio button 
	 */
	private JRadioButton bylastname = new JRadioButton("Last Name", false);
	
	/**
	 * search by type radio button 
	 */
	private JRadioButton bytype = new JRadioButton("Client Type", false);
	
	/**
	 * radio button group 
	 */
	private ButtonGroup checkGroup = new ButtonGroup(); 
	
	/**
	 * search results label
	 */
	private JLabel l = new JLabel("Search Results: ");
	
	/**
	 * client id label 
	 */
	private JLabel labelId = new JLabel("Client ID                                 ");
	
	/**
	 * first name label 
	 */
	private JLabel labelFirstName = new JLabel("First Name                                 ");
	
	/**
	 * last name label
	 */
	private JLabel labelLastName = new JLabel("Last Name                                 ");
	
	/**
	 * address label 
	 */
	private JLabel labelAdrdress = new JLabel("Address                                 ");
	
	/**
	 * postal code label
	 */
	private JLabel labelPost = new JLabel("postal code                                 ");
	
	/**
	 * phone number label
	 */
	private JLabel labelPhoneNum = new JLabel("Phone number                                 ");
	
	/**
	 * Client type label 
	 */
	private JLabel labelType = new JLabel("Client Type (either C or R)     ");
	
	/**
	 * search parameter label 
	 */
	private JLabel param = new JLabel("Enter the seach parameter here");

	/**
	 * id text first 
	 */
	private JTextField id = new JTextField(10);
	
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
	private JTextField postal = new JTextField(10);
	
	/** 
	 * phone number text field 
	 */
	private JTextField Phonenum = new JTextField(10);
	
	/**
	 * type text field 
	 */
	private JTextField type = new JTextField(10);

	/**
	 * search param text field 
	 */
	private JTextField searchParam = new JTextField(10);

	/**
	 * scroll pane for JList display 
	 */
	private JScrollPane ta = new JScrollPane();
	
	/**
	 * save button for updating client information
	 */
	private JButton save = new JButton("Save");
	
	/**
	 * delete button for deleting client information 
	 */
	private JButton delete = new JButton("Delete");
	
	/**
	 * clear button for clearing client information parameters 
	 */
	private JButton clear = new JButton("Clear");
	
	/**
	 * Search button for searching by search parameter 
	 */
	private JButton search = new JButton("Search");
	
	/**
	 * clear search button for displaying full list 
	 */
	private JButton clearSearch = new JButton("Clear search");
	
	/**
	 * insert button for inserting new client 
	 */
	private JButton insert = new JButton("Insert New");
	
	/*
	 * The constructor of the GUI that takes the dimensions of the frame and creates
	 * the GUI
	 * 
	 * @param widthInPixels is the width of the frame
	 * @param heightInPixels is the height of the frame
	 */
	public GUI(int widthInPixels, int heightInPixels) throws BadLocationException {
		checkGroup.add(byId);
		checkGroup.add(bylastname);
		checkGroup.add(bytype);
		
		list.setModel(currentModel);
		JScrollPane scroll = new JScrollPane(list);
		
		setTitle("Application to maintain customers");
		setSize(widthInPixels, heightInPixels);
		setLayout(new BorderLayout());
		JPanel subsubPanel = new JPanel();
		subsubPanel.setLayout(new GridLayout(3, 1));
		subsubPanel.add(byId);
		subsubPanel.add(bylastname);
		subsubPanel.add(bytype);
		JPanel subPanel = new JPanel();
		JPanel subPanelcent = new JPanel();
		subPanelcent.setLayout(new BorderLayout());

		subPanelcent.add(param, BorderLayout.NORTH);
		subPanelcent.add(searchParam, BorderLayout.CENTER);
		JPanel ToManyPanels = new JPanel();

		ToManyPanels.add(search);
		ToManyPanels.add(clearSearch);
		ToManyPanels.add(insert);
		subPanelcent.add(ToManyPanels, BorderLayout.SOUTH);

		scroll.setPreferredSize(new Dimension(250, 350));
		JPanel WeNeverLearnedthisInClass = new JPanel();
		WeNeverLearnedthisInClass.setLayout(new BorderLayout());
		WeNeverLearnedthisInClass.add(l, BorderLayout.NORTH);
		WeNeverLearnedthisInClass.add(scroll, BorderLayout.SOUTH);
		subPanel.setLayout(new BorderLayout());

		subPanel.add(WeNeverLearnedthisInClass, BorderLayout.SOUTH);
		subPanel.add(subsubPanel, BorderLayout.NORTH);
		subPanel.add(subPanelcent, BorderLayout.CENTER);
		ta.setSize(new Dimension(20, 20));

		JPanel subPanelright = new JPanel();
		subPanelright.add(labelId);
		subPanelright.add(id);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelFirstName);
		subPanelright.add(firstName);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelLastName);
		subPanelright.add(lastName);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelAdrdress);
		subPanelright.add(address);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelPost);
		subPanelright.add(postal);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelPost);
		subPanelright.add(postal);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelPhoneNum);
		subPanelright.add(Phonenum);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(labelType);
		subPanelright.add(type);
		subPanelright.add(Box.createVerticalStrut(60));
		subPanelright.add(save);
		subPanelright.add(delete);
		subPanelright.add(clear);
		add(subPanel, BorderLayout.WEST);
		add(subPanelright);
	}
	
	/**
	 * display clients by client type 
	 */
	public void displayClientType()
	{
		DefaultListModel<Client> tempModel = new DefaultListModel<>();
		char clientType = searchParam.getText().charAt(0);
		for (int i = 0; i < fullModel.size(); i++)
		{
			if (fullModel.get(i).getType() == clientType)
			{
				tempModel.addElement(fullModel.get(i));
			}
		}
		currentModel = tempModel; 
		list.setModel(currentModel);
		setVisible(true);
	}
	
	/**
	 * display clients by id 
	 */
	public void displayIdType()
	{
		DefaultListModel<Client> tempModel = new DefaultListModel<>();
		int key = Integer.parseInt(searchParam.getText());
		for (int i = 0; i < fullModel.size(); i++)
		{
			if (fullModel.get(i).getId() == key)
			{
				tempModel.addElement(fullModel.get(i));
			}
		}
		currentModel = tempModel; 
		list.setModel(currentModel);
		setVisible(true);
	}
	
	/**
	 * display clients by last name 
	 */
	public void displayLastType()
	{
		DefaultListModel<Client> tempModel = new DefaultListModel<>();
		String key = searchParam.getText(); 
		for (int i = 0; i < fullModel.size(); i++)
		{
			if (fullModel.get(i).getlName().equals(key))
			{
				tempModel.addElement(fullModel.get(i));
			}
		}
		currentModel = tempModel; 
		list.setModel(currentModel);
		setVisible(true);
	}
	
	/**
	 * display full list 
	 */
	public void displayFull() 
	{
		currentModel = fullModel; 
		list.setModel(currentModel);
		setVisible(true);
	}
	
	/**
	 * check by lastname radio button is selected 
	 * @return true if selected, false if not 
	 */
	public boolean checkLastType()
	{
		if(bylastname.isSelected()) 
		{
			return true;
		}
		return false; 
	}
	
	/**
	 * check proper client type in search parameter 
	 * @return true if proper format, false if wrong 
	 */
	public boolean checkClientType()
	{
		if((searchParam.getText().equals("C") || searchParam.getText().equals("R"))
			&& bytype.isSelected()) 
		{
			return true;
		}
		return false; 
	}
	
	/**
	 * check proper id type in search parameter 
	 * @return true if id is proper, false if wrong 
	 */
	public boolean checkIdType()
	{
		try {
			int num = Integer.parseInt(searchParam.getText());
			if (byId.isSelected()) {
				return true; 
			}
			return false;
		}
		catch (Exception e)
		{
			return false; 
		}
	}
	
	/**
	 * set unique id list 
	 * @param list unique id list 
	 */
	public void setIdList(UniqueId list) {
		idList = list;
	}
	
	/**
	 * add client into jList model for display
	 * @param theClient client being added 
	 */
	public void addClient(Client theClient)
	{
		currentModel.addElement(theClient);
		fullModel.addElement(theClient);
		setVisible(true);
	}
	
	/**
	 * update client information in jlist model for display 
	 * @param theClient new client information
	 * @param oldClient old client information 
	 */
	public void updateClient(Client theClient, Client oldClient)
	{
		currentModel.set(currentModel.indexOf(oldClient), theClient);
		fullModel.set(fullModel.indexOf(oldClient), theClient);
		setVisible(true);
	}

	/**
	 * return client information in text fields 
	 * @return client information 
	 */
	public Client getClientInformation() 
	{
		int theId = Integer.parseInt(id.getText());
		if (oldClient.getId() != theId)
		{
			if (!idList.addUniqueId(theId))
			{
				JOptionPane.showMessageDialog(null, "Please make sure client id is unique",
					"Error", JOptionPane.PLAIN_MESSAGE);
				return null;
			}
		}
		if (type.getText().length() != 1) 
		{
			JOptionPane.showMessageDialog(null, "Make sure client type is one letter",
				"Error", JOptionPane.PLAIN_MESSAGE);
			return null;
		}
		Client theClient = new Client(theId, firstName.getText()
				,lastName.getText(), address.getText(), postal.getText(), Phonenum.getText(), type.getText().charAt(0));
		return theClient;
	}
	
	/**
	 * fill Jlist with clients from `clients.txt` file
	 * @param clientList client array list for displaying 
	 */
	public void fillList(ArrayList<Client> clientList) { 
		for (int i = 0; i < clientList.size(); i++)
		{
			currentModel.addElement(clientList.get(i));
			fullModel.addElement(clientList.get(i));
		}
		setVisible(true); 
	}
	
	/**
	 * delete client from displayed jlist  
	 * @param deleteClient client being deleted 
	 */
	public void deleteClient(Client deleteClient) {
		currentModel.remove(currentModel.indexOf(deleteClient));
		fullModel.remove(fullModel.indexOf(deleteClient));
		oldClient = null; 
		setVisible(true);
	}

	/**
	 * add insert button action listener 
	 * @param listenForInsButton
	 */
	public void addInsertListener(ActionListener listenForInsButton) {
		insert.addActionListener(listenForInsButton);
	}

	/**
	 * add clear button action listener 
	 * @param listenForInsButton
	 */
	public void addClearListener(ActionListener listenForInsButton) {
		clear.addActionListener(listenForInsButton);
	}

	/**
	 * add save button action listener 
	 * @param listenForInsButton
	 */
	public void addSaveListener(ActionListener listenForInsButton) {
		save.addActionListener(listenForInsButton);
	}

	/**
	 * add delete button action listener 
	 * @param listenForInsButton
	 */
	public void addDeleteListener(ActionListener listenForInsButton) {
		delete.addActionListener(listenForInsButton);
	}

	/**
	 * add search button action listener 
	 * @param listenForInsButton
	 */
	public void addsearchListener(ActionListener listenForInsButton) {
		search.addActionListener(listenForInsButton);
	}

	/**
	 * add clear button action listener 
	 * @param listenForInsButton
	 */
	public void addClearSearchListener(ActionListener listenForInsButton) {
		clearSearch.addActionListener(listenForInsButton);
	}
	
	/**
	 * add byId button item listener 
	 * @param listenForCheck
	 */
	public void addByIdListener(ItemListener listenForCheck) {
		byId.addItemListener(listenForCheck);
	}
	
	/**
	 * add by last name button item listener 
	 * @param listenForCheck
	 */
	public void addByLastNameListener(ItemListener listenForCheck) {
		bylastname.addItemListener(listenForCheck);
	}
	
	/**
	 * add by client button item listener 
	 * @param listenForCheck
	 */
	public void addByClientListener(ItemListener listenForCheck) {
		bytype.addItemListener(listenForCheck);
	}
	
	/**
	 * clear client information parameters 
	 */
	public void clearClientParameters()
	{
		id.setText("");
		firstName.setText("");
		lastName.setText("");
		address.setText("");
		postal.setText("");
		Phonenum.setText("");
		type.setText("");
	}

	/**
	 * get old client information from before update 
	 * @return old client 
	 */
	public Client getOldClient() 
	{
		return oldClient; 
	}
	
	/**
	 * add list listener 
	 */
	public void addListListener()
	{
			list.getSelectionModel().addListSelectionListener(e -> {
				try {
				Client theClient = list.getSelectedValue(); 
				id.setText(Integer.toString(theClient.getId()));
				firstName.setText(theClient.getfName());
				lastName.setText(theClient.getlName());
				address.setText(theClient.getaddress());
				postal.setText(theClient.getpost());
				Phonenum.setText(theClient.getphone());
				type.setText(Character.toString(theClient.getType()));
				oldClient = theClient; 
				}
				catch (Exception ex) 
				{
				}
		});
	}
	
	/**
	 * update formating error display
	 */
	public void updateFormatErrorDisplay() {
		JOptionPane.showMessageDialog(null, "Error with update formatting",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	

}
