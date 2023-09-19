package portunus.view;

import java.util.List;

import portunus.view.control.PasswordEntryContainerTreeView;
import portunus.view.control.PasswordRecordTableView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class OverviewPane extends BorderPane {
	private PasswordEntryContainerTreeView passwordEntryContainerTreeView;
	
	private TextField searchTextField;
	private Button searchButton;
	
	private PasswordRecordTableView passwordRecordTableView;
	
	public OverviewPane() {
		constructUI();
	}
	
	private void constructUI() {
		passwordEntryContainerTreeView = new PasswordEntryContainerTreeView();
		searchTextField = new TextField();
		searchButton = new Button("Search");
		passwordRecordTableView = new PasswordRecordTableView();
		
		
		SplitPane splitPane = new SplitPane();
		List<Node> splitPaneItems = splitPane.getItems();
		setCenter(splitPane);
		
		splitPaneItems.add(passwordEntryContainerTreeView);
		
		BorderPane rightPane = new BorderPane();
		
		BorderPane searchPane = new BorderPane(); 
		searchPane.setCenter(searchTextField);
		searchPane.setRight(searchButton);
		rightPane.setTop(searchPane);
		
		rightPane.setCenter(passwordRecordTableView);
		
		splitPaneItems.add(rightPane);
		
		splitPane.setDividerPosition(0, 0.3);
	}
	
	public PasswordEntryContainerTreeView getPasswordEntryContainerTreeView() {
		return passwordEntryContainerTreeView;
	}
	
	public TextField getSearchTextField() {
		return searchTextField;
	}
	
	public Button getSearchButton() {
		return searchButton;
	}

	public PasswordRecordTableView getPasswordRecordTableView() {
		return passwordRecordTableView;
	}
}
