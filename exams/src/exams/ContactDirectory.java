package exams;

import java.io.*;

import java.util.*;

public class ContactDirectory {
	ArrayList<Contact> contacts; 
	
	ContactDirectory(){
	 contacts = new ArrayList<Contact>();
	}
	
	public void addContact(Contact c) {
		contacts.add(c);
	}
	
	public void addFromFile(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner fileReader = new Scanner(f);
		while(fileReader.hasNext()) {
			String name = fileReader.next();
			name = name.substring(0, name.indexOf(','));
			String phoneNumber = fileReader.next();
			contacts.add(new Contact(name, phoneNumber));
		}                     
		fileReader.close();
	}
}
