package portunus.controller.util;

import portunus.core.IPasswordGroup;
import portunus.view.contextmenu.PasswordEntryContainerTreeViewContextMenu;
import portunus.view.control.PasswordEntryContainerTreeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class PasswordEntryContainerTreeViewContextMenuController {
	private OverviewController parentController;
	private PasswordEntryContainerTreeViewContextMenu contextMenu;
	
	public PasswordEntryContainerTreeViewContextMenuController(PasswordEntryContainerTreeViewContextMenu contextMenu, OverviewController parentController) {
		this.contextMenu = contextMenu;
		this.parentController = parentController;
	}
	
	public void control() {
		initializeControls();
		registerListeners();
		initializeContent();
	}
	
	
	protected void initializeControls() {
		PasswordEntryContainerTreeView passwordEntryContainerTreeView = parentController.getView().getPasswordEntryContainerTreeView();
		passwordEntryContainerTreeView.setContextMenu(contextMenu);
	}
	
	protected void registerListeners() {
		contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				updateContextMenu();
			}
		});
		
		contextMenu.getAddPasswordGroupMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	IPasswordGroup selectedPasswordGroup = parentController.getSelectedPasswordGroup();
            	parentController.addPasswordGroup(selectedPasswordGroup);
            }
        });
		
		contextMenu.getEditPasswordGroupMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	IPasswordGroup selectedPasswordGroup = parentController.getSelectedPasswordGroup();
            	parentController.editPasswordGroup(selectedPasswordGroup);
            }
        });
		
		contextMenu.getDeletePasswordGroupMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	IPasswordGroup selectedPasswordGroup = parentController.getSelectedPasswordGroup();
            	parentController.deletePasswordGroup(selectedPasswordGroup);
            }
        });
	}
	
	protected void initializeContent() {
		updateContextMenu();
	}
	
	protected void updateContextMenu() {
		IPasswordGroup selectedPasswordGroup = parentController.getSelectedPasswordGroup();
		
		boolean addDisabled = false; //Can always add groups.
		boolean editDisabled = selectedPasswordGroup == null;
		boolean deleteDisabled = selectedPasswordGroup == null;
		
		contextMenu.getAddPasswordGroupMenuItem().setDisable(addDisabled);
		contextMenu.getEditPasswordGroupMenuItem().setDisable(editDisabled);
		contextMenu.getDeletePasswordGroupMenuItem().setDisable(deleteDisabled);
	}
}
