package Client;

import java.util.ArrayList;

/**
 * Class to store all unique id's for program and generate new unique id through 
 * incrementing. 
 * @author Justin, Robert 
 */
public class UniqueId {
	
	/**
	 * stores unique ID of program 
	 */
	ArrayList<Integer> idList;

	/**
	 * Constructs unique ID list 
	 */
	public UniqueId() {
		idList = new ArrayList<>(); 
		idList.add(0);
	}
	
	/**
	 * check if id is unique and add it to list
	 * @param id id being checked and added 
	 * @return true if id is unique, false if id is not 
	 */
	public boolean addUniqueId(int id) {
		if (checkUniqueId(id)) {
			idList.add(id);
			return true;
		}
		return false; 
	}
	
	/**
	 * generate unique id by incrementing and add it to id list to keep track of 
	 * id's 
	 * @return unique Id 
	 */
	public int getUniqueId() {
		int newId = idList.get(idList.size()-1) + 1;
		idList.add(newId); 
		return newId; 
	}
	
	/**
	 * check if id is unique 
	 * @param key id being checked for uniqueness
	 * @return true if unique, false if it already exists in database 
	 */
	public boolean checkUniqueId(int key) {
		for (int i = 0; i < idList.size(); i++) 
		{
			if (key == idList.get(i)) 
			{
				return false; 
			}
		}
		return true; 
	}
}
