package portunus.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import portunus.core.IPasswordEntry;
import portunus.core.IPasswordEntryContainer;
import portunus.core.IPasswordGroup;
import portunus.core.IPasswordLibrary;
import portunus.core.IPasswordRecord;
import portunus.core.IPortunusFactory;
import portunus.util.finder.IPasswordRecordFinder;
import portunus.view.OverviewPane;
import portunus.view.contextmenu.PasswordEntryContainerTreeViewContextMenu;
import portunus.view.contextmenu.PasswordRecordTableViewContextMenu;
import portunus.view.control.PasswordEntryContainerTreeItem;
import portunus.view.control.PasswordEntryContainerTreeView;
import portunus.view.control.PasswordRecordTableView;
import portunus.view.dialog.AddPasswordGroupDialog;
import portunus.view.dialog.AddPasswordRecordDialog;
import portunus.view.dialog.EditPasswordGroupDialog;
import portunus.view.dialog.EditPasswordRecordDialog;
import portunus.view.util.ErrorUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class OverviewController {
	private IPasswordLibrary passwordLibrary;
	private OverviewPane overviewPane;

	private IPortunusFactory factory;
	
	public OverviewController(IPasswordLibrary passwordLibrary, OverviewPane overviewPane, IPortunusFactory factory) {
		this.passwordLibrary = passwordLibrary;
		this.overviewPane = overviewPane;
		this.factory = factory;
	}

	public void control() {
		registerListeners();
		initializeContent();
		initializeChildControllers();
	}
	
	protected void registerListeners() {
		PasswordEntryContainerTreeView passwordEntryContainerTreeView = overviewPane.getPasswordEntryContainerTreeView();
		
		passwordEntryContainerTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<IPasswordEntryContainer>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<IPasswordEntryContainer>> observable, TreeItem<IPasswordEntryContainer> oldValue, TreeItem<IPasswordEntryContainer> newValue) {
				IPasswordGroup selectedPasswordGroup = getSelectedPasswordGroup();
				updatePasswordRecordTableView(selectedPasswordGroup);
			}
		});
		
		
		overviewPane.getSearchButton().setOnAction(e -> {
			searchPasswordEntries();
		});
		
		
		TableView<IPasswordRecord> passwordRecordTableView = overviewPane.getPasswordRecordTableView();
		
		passwordRecordTableView.setRowFactory(tv -> {
		    TableRow<IPasswordRecord> row = new TableRow<>();
		    
		    row.setOnMouseClicked(event -> {
		    	//Detect double clicks
		        if (event.getClickCount() == 2 && (!row.isEmpty())) {
		        	IPasswordRecord passwordRecord = row.getItem();
		        	editPasswordRecord(passwordRecord);
		        }
		    });
		    
		    return row ;
		});
	}
	
	protected void initializeContent() {
		updatePasswordEntryContainerTreeView();
		
		//Set selection to first (visible) password group.
		List<IPasswordEntry> passwordEntries = passwordLibrary.getEntries();
		
		if (!passwordEntries.isEmpty()) {
			IPasswordEntry firstPasswordEntry = passwordEntries.get(0);
			
			if (firstPasswordEntry instanceof IPasswordGroup) {
				IPasswordGroup firstPasswordGroup = (IPasswordGroup) firstPasswordEntry;
				selectPasswordGroup(firstPasswordGroup);
			}
		}
	}
	
	protected void initializeChildControllers() {
		PasswordRecordTableViewContextMenu passwordRecordContextMenu = new PasswordRecordTableViewContextMenu();
		PasswordRecordTableViewContextMenuController passwordRecordTableViewContextMenuController = new PasswordRecordTableViewContextMenuController(passwordRecordContextMenu, this);
		passwordRecordTableViewContextMenuController.control();
		
		PasswordEntryContainerTreeViewContextMenu passwordEntryContainerTreeViewContextMenu = new PasswordEntryContainerTreeViewContextMenu();
		PasswordEntryContainerTreeViewContextMenuController passwordEntryContainerTreeViewContextMenuController = new PasswordEntryContainerTreeViewContextMenuController(passwordEntryContainerTreeViewContextMenu, this);
		passwordEntryContainerTreeViewContextMenuController.control();
	}
	
	
	public void searchPasswordEntries() {
		TextField searchTextField = overviewPane.getSearchTextField();
		String partialString = searchTextField.getText();
		doSearchPasswordEntries(partialString);
	}
	
	public void copyUserToClipboard() {
		IPasswordRecord selectedPasswordRecord = getSelectedPasswordRecord();
		
		if (selectedPasswordRecord == null) {
			return;
		}
		
		String user = selectedPasswordRecord.getUser();
		copyToClipboard(user);
	}
	
	public void copyPasswordToClipboard() {
		IPasswordRecord selectedPasswordRecord = getSelectedPasswordRecord();
		
		if (selectedPasswordRecord == null) {
			return;
		}
		
		String password = selectedPasswordRecord.getPassword();
		copyToClipboard(password);
	}
	
	private void copyToClipboard(String text) {
		Clipboard clipboard = Clipboard.getSystemClipboard();
	    ClipboardContent content = new ClipboardContent();
	    content.putString(text);
	    clipboard.setContent(content);
	}
	
	public void addPasswordGroup(IPasswordEntryContainer parentContainer) {
		IPasswordGroup passwordGroup = factory.createPasswordGroup("New Group");
		
		if (passwordGroup == null) {
			ErrorUtil.showFactoryMethodError(IPasswordGroup.class);
			return;
		}
		
		AddPasswordGroupDialog dialog = new AddPasswordGroupDialog(passwordGroup);
		Optional<IPasswordGroup> result = dialog.showAndWait();

		if (result.isPresent()) {
			IPasswordGroup editedPasswordGroup = result.get();
			IPasswordGroup selectedPasswordGroup = getSelectedPasswordGroup();
			doAddPasswordGroup(editedPasswordGroup, selectedPasswordGroup);
		}
	}
	
	public void editPasswordGroup(IPasswordGroup passwordGroup) {
		if (passwordGroup == null) {
			return;
		}
		
		EditPasswordGroupDialog dialog = new EditPasswordGroupDialog(passwordGroup);
		Optional<IPasswordGroup> result = dialog.showAndWait();

		if (result.isPresent()) {
			IPasswordGroup editedPasswordGroup = result.get();
			updatePasswordGroup(editedPasswordGroup);
		}
	}
	
	public void deletePasswordGroup(IPasswordGroup passwordGroup) {
		boolean reallyDelete = showDeleteConfirmationDialog("group", passwordGroup.getTitle());
		
		if (reallyDelete) {
			doDeletePasswordGroup(passwordGroup);
		}
	}
	
	
	
	public void addPasswordRecord() {
		IPasswordRecord passwordRecord = factory.createPasswordRecord("New Record");
		
		if (passwordRecord == null) {
			ErrorUtil.showFactoryMethodError(IPasswordRecord.class);
			return;
		}
		
		AddPasswordRecordDialog dialog = new AddPasswordRecordDialog(passwordRecord, factory);
		Optional<IPasswordRecord> result = dialog.showAndWait();

		if (result.isPresent()) {
			IPasswordRecord editedPasswordRecord = result.get();
			doAddPasswordRecord(editedPasswordRecord);
		}
	}
	
	public void editPasswordRecord(IPasswordRecord passwordRecord) {
		if (passwordRecord == null) {
			return;
		}
		
		EditPasswordRecordDialog dialog = new EditPasswordRecordDialog(passwordRecord, factory);
		Optional<IPasswordRecord> result = dialog.showAndWait();

		if (result.isPresent()) {
			IPasswordRecord editedPasswordRecord = result.get();
			doAddPasswordRecord(editedPasswordRecord);
		}
	}
	
	public void deletePasswordRecord(IPasswordRecord passwordRecord) {
		boolean reallyDelete = showDeleteConfirmationDialog("record", passwordRecord.getTitle());
		
		if (reallyDelete) {
			doDeletePasswordRecord(passwordRecord);
		}
	}
	
	
	protected boolean showDeleteConfirmationDialog(String elementTypeName, String elementName) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete " + elementTypeName + "?");
		alert.setHeaderText(null);
		alert.setContentText("Do you really want to delete the " + elementTypeName + " \"" + elementName + "\"?");

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK) {
			return true;
		}
		
		return false;
	}
	
	
	
	protected void doAddPasswordGroup(IPasswordGroup passwordGroup, IPasswordEntryContainer parentContainer) {
		if (parentContainer == null) {
			parentContainer = passwordLibrary;
		}
		
		parentContainer.addEntry(passwordGroup);
		updatePasswordEntryContainerTreeView();
		selectPasswordGroup(passwordGroup);
	}
	
	protected void doDeletePasswordGroup(IPasswordGroup passwordGroup) {
		IPasswordEntryContainer container = findPasswordEntryContainer(passwordGroup);
		
		if (container != null) {
			container.removeEntry(passwordGroup);
			updatePasswordEntryContainerTreeView();
		}
	}
	
	private IPasswordEntryContainer findPasswordEntryContainer(IPasswordEntry searchedEntry) {
		return findPasswordEntryContainer(searchedEntry, passwordLibrary);
	}
	
	private IPasswordEntryContainer findPasswordEntryContainer(IPasswordEntry searchedEntry, IPasswordEntryContainer currentContainer) {
		List<IPasswordEntry> childEntries = currentContainer.getEntries();
		
		if (childEntries.contains(searchedEntry)) {
			return currentContainer;
		}
		
		for (IPasswordEntry childEntry : childEntries) {
			if (childEntry instanceof IPasswordEntryContainer) {
				IPasswordEntryContainer childContainer = (IPasswordEntryContainer) childEntry;
				IPasswordEntryContainer foundContainer = findPasswordEntryContainer(searchedEntry, childContainer);
				
				if (foundContainer != null) {
					return foundContainer;
				}
			}
		}
		
		return null;
	}

	private TreeItem<IPasswordEntryContainer> findPasswordEntryContainerTreeItem(IPasswordEntryContainer searchedPasswordEntryContainer) {
		PasswordEntryContainerTreeView passwordEntryContainerTreeView = overviewPane.getPasswordEntryContainerTreeView();
		
		TreeItem<IPasswordEntryContainer> rootTreeItem = passwordEntryContainerTreeView.getRoot();
		
		return findPasswordEntryContainerTreeItem(searchedPasswordEntryContainer, rootTreeItem);
	}
	
	private TreeItem<IPasswordEntryContainer> findPasswordEntryContainerTreeItem(IPasswordEntryContainer searchedPasswordEntryContainer, TreeItem<IPasswordEntryContainer> currentTreeItem) {
		IPasswordEntryContainer currentPasswordEntryContainer = currentTreeItem.getValue();
		
		if (currentPasswordEntryContainer == searchedPasswordEntryContainer) {
			return currentTreeItem;
		}
		
		List<TreeItem<IPasswordEntryContainer>> childTreeItems = currentTreeItem.getChildren();
		
		for (TreeItem<IPasswordEntryContainer> childTreeItem : childTreeItems) {
			TreeItem<IPasswordEntryContainer> foundTreeItem = findPasswordEntryContainerTreeItem(searchedPasswordEntryContainer, childTreeItem);
			
			if (foundTreeItem != null) {
				return foundTreeItem;
			}
		}
		
		return null;
	}
	
	
	private void doSearchPasswordEntries(String partialString) {
		IPasswordRecordFinder finder = factory.createPasswordRecordFinder();
		
		if (finder == null) {
			ErrorUtil.showFactoryMethodError(IPasswordRecordFinder.class);
			return;
		}
		
		//Deselect password group
		overviewPane.getPasswordEntryContainerTreeView().getSelectionModel().clearSelection();
		
		//Find matching password records
		List<IPasswordRecord> matchingPasswordRecords = finder.findMatchingPasswordRecords(partialString, passwordLibrary);
		
		//Fill table with found entries
		updatePasswordRecordTableView(matchingPasswordRecords);
	}
	
	protected void doAddPasswordRecord(IPasswordRecord passwordRecord) {
		IPasswordGroup selectedPasswordGroup = getSelectedPasswordGroup();
		
		if (selectedPasswordGroup == null) {
			return;
		}
		
		selectedPasswordGroup.addEntry(passwordRecord);
		updatePasswordGroup(selectedPasswordGroup);
		selectPasswordRecord(passwordRecord);
	}
	
	protected void doDeletePasswordRecord(IPasswordRecord passwordRecord) {
		IPasswordGroup selectedPasswordGroup = getSelectedPasswordGroup();
		
		if (selectedPasswordGroup == null) {
			return;
		}
		
		selectedPasswordGroup.removeEntry(passwordRecord);
		updatePasswordGroup(selectedPasswordGroup);
	}
	
	
	
	protected void updatePasswordGroup(IPasswordGroup passwordGroup) {
		if (passwordGroup == null) {
			return;
		}
		
		IPasswordGroup selectedPasswordGroup = getSelectedPasswordGroup();
		
		//TODO: Update tree view more subtly
		updatePasswordEntryContainerTreeView();
		
		//Restore selection
		selectPasswordGroup(selectedPasswordGroup);
		
		
		if (passwordGroup == selectedPasswordGroup) {
			updatePasswordRecordTableView(passwordGroup);
		}
	}
	
	protected void updatePasswordRecord(IPasswordRecord passwordRecord) {
//		TableView<PasswordRecord> passwordRecordTableView = view.getPasswordRecordTableView();
		IPasswordRecord selectedPasswordRecord = getSelectedPasswordRecord();
		
		//TODO: something less crude
		IPasswordGroup selectedPasswordGroup = getSelectedPasswordGroup();
		updatePasswordRecordTableView(selectedPasswordGroup);
		
		//Restore previously selected record.
		selectPasswordRecord(selectedPasswordRecord);
	}
	
	
	
	protected void selectPasswordGroup(IPasswordGroup passwordGroup) {
		TreeItem<IPasswordEntryContainer> treeItem = findPasswordEntryContainerTreeItem(passwordGroup);
		
		if (treeItem != null) {
			PasswordEntryContainerTreeView passwordEntryContainerTreeView = overviewPane.getPasswordEntryContainerTreeView();
			passwordEntryContainerTreeView.getSelectionModel().select(treeItem);
		}
	}
	
	public IPasswordGroup getSelectedPasswordGroup() {
		PasswordEntryContainerTreeView passwordEntryContainerTreeView = overviewPane.getPasswordEntryContainerTreeView();
		TreeItem<IPasswordEntryContainer> selectedPasswordEntryContainerTreeItem = passwordEntryContainerTreeView.getSelectionModel().getSelectedItem();
		
		if (selectedPasswordEntryContainerTreeItem == null) {
			return null;
		}
		
		IPasswordEntryContainer selectedPasswordEntryContainer = selectedPasswordEntryContainerTreeItem.getValue();
		
		if (selectedPasswordEntryContainer instanceof IPasswordGroup) {
			return (IPasswordGroup) selectedPasswordEntryContainer;
		}
		
		return null;
	}

	
	protected void selectPasswordRecord(IPasswordRecord passwordRecord) {
		TableView<IPasswordRecord> passwordRecordTableView = overviewPane.getPasswordRecordTableView();
		passwordRecordTableView.getSelectionModel().select(passwordRecord);
	}
	
	public IPasswordRecord getSelectedPasswordRecord() {
		PasswordRecordTableView passwordRecordTableView = overviewPane.getPasswordRecordTableView();
		return passwordRecordTableView.getSelectionModel().getSelectedItem();
	}
	
	public List<IPasswordRecord> getSelectedPasswordRecords() {
		PasswordRecordTableView passwordRecordTableView = overviewPane.getPasswordRecordTableView();
		return passwordRecordTableView.getSelectionModel().getSelectedItems();
	}
	
	
	

	
	
	
	private void updatePasswordEntryContainerTreeView() {
		PasswordEntryContainerTreeView passwordEntryContainerTreeView = overviewPane.getPasswordEntryContainerTreeView();

		PasswordEntryContainerTreeItem rootTreeItem = createPasswordEntryTreeViewItem(passwordLibrary);

		passwordEntryContainerTreeView.setRoot(rootTreeItem);
		
		expandTreeView(passwordEntryContainerTreeView);
	}

	private PasswordEntryContainerTreeItem createPasswordEntryTreeViewItem(IPasswordEntryContainer passwordEntryContainer) {
		PasswordEntryContainerTreeItem treeViewItem = new PasswordEntryContainerTreeItem(passwordEntryContainer);

		List<TreeItem<IPasswordEntryContainer>> children = treeViewItem.getChildren();
		List<IPasswordEntry> entries = passwordEntryContainer.getEntries();

		for (IPasswordEntry entry : entries) {
			if (entry instanceof IPasswordGroup) {
				IPasswordGroup childPasswordGroup = (IPasswordGroup) entry;

				PasswordEntryContainerTreeItem childTreeViewItem = createPasswordEntryTreeViewItem(childPasswordGroup);
				children.add(childTreeViewItem);
			}
		}

		return treeViewItem;
	}

	private static void expandTreeView(TreeView<?> treeView) {
		TreeItem<?> treeItem = treeView.getRoot();
		expandTreeViewItem(treeItem);
	}
	
	private static void expandTreeViewItem(TreeItem<?> treeItem) {
		if (treeItem != null && !treeItem.isLeaf()) {
			treeItem.setExpanded(true);
			for (TreeItem<?> child : treeItem.getChildren()) {
				expandTreeViewItem(child);
			}
		}
	}

	
	
	private void updatePasswordRecordTableView(IPasswordGroup selectedPasswordGroup) {
		if (selectedPasswordGroup != null) {
			List<IPasswordEntry> entries = selectedPasswordGroup.getEntries();
			
			List<IPasswordRecord> records = new ArrayList<>();
			
			for (IPasswordEntry entry : entries) {
				if (entry instanceof IPasswordRecord) {
					IPasswordRecord record = (IPasswordRecord) entry;
					records.add(record);
				}
			}
			
			updatePasswordRecordTableView(records);
		}
	}
	
	private void updatePasswordRecordTableView(List<IPasswordRecord> records) {
		TableView<IPasswordRecord> passwordRecordTableView = overviewPane.getPasswordRecordTableView();
		List<IPasswordRecord> tableItems = passwordRecordTableView.getItems();
		
		tableItems.clear();
		tableItems.addAll(records);
	}
	
	public IPasswordLibrary getModel() {
		return passwordLibrary;
	}

	public OverviewPane getView() {
		return overviewPane;
	}
}
