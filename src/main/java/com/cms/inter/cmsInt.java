package com.cms.inter;
import com.cms.db.contacts_db;
import com.cms.db.users;

public interface cmsInt {
	public boolean addUser(users user);
	public boolean userExist(String email,String password);
	public int checkId(String email);
	public boolean addContact(contacts_db contact , int userId);
	public boolean editContacts(contacts_db contacts_db,int userId);
}
