package portunus.core;

public interface IPasswordRecord extends IPasswordEntry {
	public String getUser();
	public void setUser(String user);

	public String getPassword();
	public void setPassword(String password);

	public String getUrl();
	public void setUrl(String url);

	public String getNotes();
	public void setNotes(String notes);
}