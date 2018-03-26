package Controller;

import Model.*;
import Client.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;

/**
 * Controller of client program, handles logic and interactions between main GUI,
 * insert GUI and database. 
 * @author Justin, Robert
 */
public class Controller {
	
	/**
	 * main view GUI
	 */
	private GUI mainView;
	
	/**
	 * insert view GUI
	 */
	private InsertGUI insertView;
	
	/**
	 * database to store client
	 */
	private DataBase dataBase;
	
	/**
	 * unique id list which stores all unique id and generates new ones 
	 */
	private UniqueId idList; 
	
	/*
	 * This is the constructor for the controller where the GUI, model and uniqueId list are set
	 * Listeners for all buttons are added here. 
	 * @param g is the GUI
	 * @param m is the model
	 * @param list is the uniqueId list 
	 */
	public Controller(GUI g, DataBase db, UniqueId list)
	{
		idList = list;
		dataBase = db; 
		mainView = g;
		mainView.fillList(dataBase.tableToList()); 
		mainView.addByIdListener(new ByIdListener());
		mainView.addByLastNameListener(new ByLastNameListener());
		mainView.addByClientListener(new ByClientListener());
		mainView.addInsertListener( new MyListener());
		mainView.addClearListener(new MyListener2());
		mainView.addSaveListener(new MyListener3());
		mainView.addDeleteListener(new MyListener4());
		mainView.addsearchListener(new MyListener5());
		mainView.addClearSearchListener(new MyListener6());	
		mainView.addListListener();
		mainView.setIdList(idList);
	}
	
	/**
	 * radio button by id listener 
	 */
	class ByIdListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent itemEvent) 
		{
		}
	}
	
	/**
	 * radio button by last name listener  
	 */
	class ByLastNameListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent itemEvent) 
		{
		}
	}
	
	/**
	 * radio button by client listener 
	 */
	class ByClientListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent itemEvent) 
		{
		}
	}
	
	/**
	 * insert new client button listener 
	 */
	class MyListener implements ActionListener  
	{
		/**
		 * when button pressed create insert view gui 
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			insertView = new InsertGUI(); 
			insertView.setIdList(idList);
			InsertListener insertListener = new InsertListener();
			insertView.addInsertActionListener(new InsertListener());	
			JButton leave = new JButton("return to Main Window");
			leave.addActionListener(new ActionListener()
			{
				/**
				 * if return to main window button pressed then exit insert view 
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					insertView.turnOff(); 
				}
			});
		}
		
		/**
		 * insert button listener for insert view 
		 */
		class InsertListener implements ActionListener
		{
			/**
			 * if insert button pressed then check formatting and insert into database
			 * and update mainView jList 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (insertView.checkEmptyFields()) {
					insertView.displayEmptyFieldError();
				}
				else if(insertView.checkClientType()) {
					insertView.displayWrongClientError();
				}
				else if(!insertView.checkPostal()) {
					insertView.displayPostalFieldError();
				}
				else if(!insertView.checkPhone()) {
					insertView.displayPhoneError();
				}
				else if(!insertView.checkLength()) {
					insertView.displayLengthError();  
				}
				else {
					Client theClient = insertView.getClient(); 
					dataBase.addItem(theClient);
					mainView.addClient(theClient); 
				}
			}
		}	
	}
	
	/**
	 * clear button listener 
	 */
	 public class MyListener2 implements ActionListener {

		 /**
		  * if clear pressed, clear client parameters 
		  */
		@Override
		public void actionPerformed(ActionEvent e) {
			mainView.clearClientParameters();
		} 
	 }
	 
	 /**
	  * update client parameters listener 
	  */
	 public class MyListener3 implements ActionListener {
		 /**
		  * if save pressed then check client parameters, update database and JList 
		  */
		@Override
		public void actionPerformed(ActionEvent e) {
			Client theClient = mainView.getClientInformation();
			Client oldClient = mainView.getOldClient();
			if (theClient.checkClientType() || !theClient.checkPhone() || !theClient.checkLength()
					|| !theClient.checkPostal() || theClient==null)
			{
				mainView.updateFormatErrorDisplay(); 
				return;
			}
			dataBase.update(theClient, oldClient.getId());
			mainView.updateClient(theClient, oldClient);
			mainView.clearClientParameters();
		}
	 }
	 
	 /**
	  * delete button listener 
	  */
	 public class MyListener4 implements ActionListener {
		 /**
		  * if delete pressed then delete client from database and jlist, if no client selected return
		  */
		@Override
		public void actionPerformed(ActionEvent e) {
			Client deleteClient = mainView.getOldClient();
			if (deleteClient == null)
			{
				return;
			}
			dataBase.delete(deleteClient.getId());
			mainView.deleteClient(deleteClient);	
			mainView.clearClientParameters();
		}	 	 
	 }
	 
	 /**
	  * search button listener 
	  */
	 public class MyListener5 implements ActionListener {
		 	
		 	/**
		 	 * if search pressed then check search parameters and radio buttons 
		 	 * then change model for jlist in main view 
		 	 */
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (mainView.checkClientType()) {
					mainView.displayClientType(); 
					mainView.addListListener();
				}
				else if (mainView.checkIdType()) {
					mainView.displayIdType(); 
					mainView.addListListener();
				}
				else if (mainView.checkLastType()) {
					mainView.displayLastType();
					mainView.addListListener();
				}
			}
	 }
	 
	 /**
	  * clear search listener 
	  */
	 public class MyListener6 implements ActionListener {
		 /**
		  * if pressed then display full database in mainview jlist 
		  */
		@Override
		public void actionPerformed(ActionEvent e) {
			mainView.displayFull();
			mainView.addListListener();
		}
	 }
	
	 /**
	  * Contructor of program 
	  * @param args 
	  * @throws BadLocationException
	  */
	public static void main(String[] args) throws BadLocationException {
		UniqueId uniqueIdList = new UniqueId();
		
		GUI gui = new GUI(650, 600);
		DataBase clientDB = new DataBase(); 
		clientDB.setIdList(uniqueIdList);
		clientDB.createDB();
		clientDB.removeTable();
		clientDB.createTable(); 
		clientDB.fillTable(); 
		Controller c = new Controller(gui,clientDB, uniqueIdList);
	}
	 
}

