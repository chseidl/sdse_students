package portunus.view.contextmenu;

import java.util.List;

import portunus.view.image.ImageLibrary;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PasswordEntryContainerTreeViewContextMenu extends ContextMenu {
	private MenuItem addPasswordGroupMenuItem;
	private MenuItem editPasswordGroupMenuItem;
	private MenuItem deletePasswordGroupMenuItem;
	
	public PasswordEntryContainerTreeViewContextMenu() {
		initializeControl();
	}
	
	private void initializeControl() {
		addPasswordGroupMenuItem = createAndAddMenuItem("Add Group...", ImageLibrary.getInstance().getAddPasswordGroupImage());
		editPasswordGroupMenuItem = createAndAddMenuItem("Edit Group...", ImageLibrary.getInstance().getEditPasswordGroupImage());
		deletePasswordGroupMenuItem = createAndAddMenuItem("Delete Group", ImageLibrary.getInstance().getDeletePasswordGroupImage());
	}

	private MenuItem createAndAddMenuItem(String text, Image image) {
		List<MenuItem> menuItems = getItems();
		MenuItem menuItem = new MenuItem(text);
        menuItems.add(menuItem);
        
        if (image != null) {
        	menuItem.setGraphic(new ImageView(image));
        }
        
        return menuItem;
	}

	public MenuItem getAddPasswordGroupMenuItem() {
		return addPasswordGroupMenuItem;
	}

	public MenuItem getEditPasswordGroupMenuItem() {
		return editPasswordGroupMenuItem;
	}

	public MenuItem getDeletePasswordGroupMenuItem() {
		return deletePasswordGroupMenuItem;
	}
}
