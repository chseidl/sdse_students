package portunus.view.dialog;

import java.util.List;
import java.util.Optional;

import portunus.core.IPasswordRecord;
import portunus.core.IPortunusFactory;
import portunus.view.javafx.AddEditDialog;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public abstract class AddEditPasswordRecordDialog extends AddEditDialog<IPasswordRecord> {
	private TextField titleTextField;
	private TextField userTextField;
	private PasswordField passwordPasswordField;
	private Button generatePasswordButton;
	private TextField urlTextField;
	private TextArea notesTextArea;
	
	private IPortunusFactory factory;
	
	public AddEditPasswordRecordDialog(IPasswordRecord passwordRecord, IPortunusFactory factory) {
		super(passwordRecord);
		
		this.factory = factory;
	}
	
	@Override
	protected void configureColumns(GridPane contentPane) {
		super.configureColumns(contentPane);
		
		//Add a third column.
		List<ColumnConstraints> columnConstraints = contentPane.getColumnConstraints();
		columnConstraints.add(new ColumnConstraints());
	}

	@Override
	protected void assembleContentPane(GridPane gridPane) {
		titleTextField = new TextField();
		userTextField = new TextField();
		passwordPasswordField = new PasswordField();
		generatePasswordButton = new Button("Generate...");
		urlTextField = new TextField();
		notesTextArea = new TextArea();

		gridPane.add(new Label("Title:"), 0, 0);
		gridPane.add(titleTextField, 1, 0, 2, 1);
		
		gridPane.add(new Label("User:"), 0, 1);
		gridPane.add(userTextField, 1, 1, 2, 1);
		
		gridPane.add(new Label("Password:"), 0, 2);
		gridPane.add(passwordPasswordField, 1, 2);
		gridPane.add(generatePasswordButton, 2, 2);
		
		gridPane.add(new Label("URL:"), 0, 3);
		gridPane.add(urlTextField, 1, 3, 2, 1);
		
		Label notesLabel = new Label("Notes:");
		gridPane.add(notesLabel, 0, 4);
		GridPane.setValignment(notesLabel, VPos.TOP);
		gridPane.add(notesTextArea, 1, 4, 2, 1);
		
		GridPane.setVgrow(notesTextArea, Priority.ALWAYS);
		
		registerListeners();
	}
	
	@Override
	protected Control getInitialFocusControl() {
		return titleTextField;
	}
	
	private void registerListeners() {
		generatePasswordButton.setOnAction(e -> {
		    GeneratePasswordDialog generatePasswordDialog = new GeneratePasswordDialog(factory);
		    
		    Optional<String> result = generatePasswordDialog.showAndWait();
		    
		    if (result.isPresent()) {
		    	String generatedPassword = result.get();
		    	passwordPasswordField.setText(generatedPassword);
		    }
		});
	}
	
	@Override
	protected void updateViewFromModel() {
		IPasswordRecord passwordRecord = getModel();
		
		titleTextField.setText(passwordRecord.getTitle());
		userTextField.setText(passwordRecord.getUser());
		passwordPasswordField.setText(passwordRecord.getPassword());
		urlTextField.setText(passwordRecord.getUrl());
		notesTextArea.setText(passwordRecord.getNotes());
	}
	
	@Override
	protected void updateModelFromView() {
		IPasswordRecord passwordRecord = getModel();
		
		passwordRecord.setTitle(titleTextField.getText());
		passwordRecord.setUser(userTextField.getText());
		passwordRecord.setPassword(passwordPasswordField.getText());
		passwordRecord.setUrl(urlTextField.getText());
		passwordRecord.setNotes(notesTextArea.getText());
	}
}
