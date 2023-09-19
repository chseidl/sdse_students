package portunus.core;

import java.util.List;

public interface IPasswordEntryContainer {
	public List<IPasswordEntry> getEntries();
	public boolean addEntry(IPasswordEntry entry);
	public boolean removeEntry(IPasswordEntry entry);
}
