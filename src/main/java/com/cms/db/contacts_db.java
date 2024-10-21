package com.cms.db;

public class contacts_db {
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String name;
	
	private String phone;
	
	private String email;
	
	private byte[] profilePhoto;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getProfilePhoto() {
	     return profilePhoto; 
	}
	    
	public void setProfilePhoto(byte[] profilePhoto) {
	     this.profilePhoto = profilePhoto; 
	}
}
