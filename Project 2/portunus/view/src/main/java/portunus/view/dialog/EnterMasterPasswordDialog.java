package portunus.view.dialog;

import portunus.view.image.ImageLibrary;
import portunus.view.javafx.OKCancelDialog;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EnterMasterPasswordDialog extends OKCancelDialog<String> {
	private PasswordField passwordField;
	
	@Override
	protected String createTitle() {
		return "Enter Master Password";
	}
	
	@Override
	protected Image createImage() {
		return ImageLibrary.getInstance().getMasterPasswordImage();
	}

	@Override
	protected Pane createContentPane() {
		BorderPane pane = new BorderPane();
		
		passwordField = new PasswordField();
		pane.setCenter(passwordField);
		
		return pane;
	}

	@Override
	protected Control getInitialFocusControl() {
		return passwordField;
	}
	
	@Override
	protected String getOKResult() {
		return passwordField.getText();
	}

	@Override
	protected void initializeControl() {
		super.initializeControl();
		
		Pane dialogPane = getDialogPane();
		dialogPane.setPrefWidth(300);
	}
}
