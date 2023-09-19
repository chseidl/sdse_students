package portunus.view.dialog;

import portunus.core.IPasswordGroup;
import portunus.view.javafx.AddEditDialog;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class AddEditPasswordGroupDialog extends AddEditDialog<IPasswordGroup> {
	private TextField titleTextField;
	
	public AddEditPasswordGroupDialog(IPasswordGroup passwordGroup) {
		super(passwordGroup);
	}
	
	@Override
	protected void assembleContentPane(GridPane gridPane) {
		titleTextField = new TextField();

		gridPane.add(new Label("Title:"), 0, 0);
		gridPane.add(titleTextField, 1, 0);
	}
	
	@Override
	protected void initializeControl() {
		super.initializeControl();
		
		Pane dialogPane = getDialogPane();
		dialogPane.setPrefWidth(300);
	}
	
	@Override
	protected Control getInitialFocusControl() {
		return titleTextField;
	}
	
	@Override
	protected void updateViewFromModel() {
		IPasswordGroup passwordGroup = getModel();
		
		titleTextField.setText(passwordGroup.getTitle());
	}
	
	@Override
	protected void updateModelFromView() {
		IPasswordGroup passwordGroup = getModel();
		
		passwordGroup.setTitle(titleTextField.getText());
	}
}
