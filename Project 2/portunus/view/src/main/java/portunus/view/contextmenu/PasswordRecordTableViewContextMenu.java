package portunus.view.contextmenu;

import java.util.List;

import portunus.view.image.ImageLibrary;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class PasswordRecordTableViewContextMenu extends ContextMenu {
	private MenuItem copyUserMenuItem;
	private MenuItem copyPasswordMenuItem;
	
	private MenuItem addPasswordRecordMenuItem;
	private MenuItem editPasswordRecordMenuItem;
	private MenuItem deletePasswordRecordMenuItem;
	
	public PasswordRecordTableViewContextMenu() {
		initializeControl();
	}
	
	private void initializeControl() {
		copyUserMenuItem = createAndAddMenuItem("Copy User to Clipboard", ImageLibrary.getInstance().getCopyUserImage());
		copyUserMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
		
		copyPasswordMenuItem = createAndAddMenuItem("Copy Password to Clipboard", ImageLibrary.getInstance().getCopyPasswordImage());
		copyPasswordMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
		
		addMenuItem(new SeparatorMenuItem());
		
		addPasswordRecordMenuItem = createAndAddMenuItem("Add Record...", ImageLibrary.getInstance().getAddPasswordRecordImage());
		editPasswordRecordMenuItem = createAndAddMenuItem("Edit/View Record...", ImageLibrary.getInstance().getEditPasswordRecordImage());
		deletePasswordRecordMenuItem = createAndAddMenuItem("Delete Record", ImageLibrary.getInstance().getDeletePasswordRecordImage());
	}

	
	private MenuItem createAndAddMenuItem(String text, Image image) {
		MenuItem menuItem = new MenuItem(text);
        
        if (image != null) {
        	menuItem.setGraphic(new ImageView(image));
        }
        
        addMenuItem(menuItem);
        
        return menuItem;
	}
	
	private void addMenuItem(MenuItem menuItem) {
		List<MenuItem> menuItems = getItems();
        menuItems.add(menuItem);
	}
	
	public MenuItem getCopyUserMenuItem() {
		return copyUserMenuItem;
	}
	
	public MenuItem getCopyPasswordMenuItem() {
		return copyPasswordMenuItem;
	}
	
	public MenuItem getAddPasswordRecordMenuItem() {
		return addPasswordRecordMenuItem;
	}

	public MenuItem getEditPasswordRecordMenuItem() {
		return editPasswordRecordMenuItem;
	}

	public MenuItem getDeletePasswordRecordMenuItem() {
		return deletePasswordRecordMenuItem;
	}
}
