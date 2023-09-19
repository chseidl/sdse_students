package portunus.view.dialog;

import java.util.ArrayList;
import java.util.List;

import portunus.core.IPortunusFactory;
import portunus.util.passwordgenerator.CharacterSet;
import portunus.util.passwordgenerator.IPasswordGenerator;
import portunus.view.image.ImageLibrary;
import portunus.view.javafx.OKCancelDialog;
import portunus.view.util.ErrorUtil;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GeneratePasswordDialog extends OKCancelDialog<String> {
	private Spinner<Integer> lengthSpinner;
	
	private CheckBox upperCaseCheckBox;
	private CheckBox lowerCaseCheckBox;
	private CheckBox digitsCheckBox;
	
	private CheckBox minusCheckBox;
	private CheckBox underscoreCheckBox;
	private CheckBox spaceCheckBox;
	
	private IPasswordGenerator passwordGenerator;
	
	public GeneratePasswordDialog(IPortunusFactory factory) {
		passwordGenerator = factory.createPasswordGenerator();
	}
	
	@Override
	protected String createTitle() {
		return "Generate Password...";
	}


	@Override
	protected Image createImage() {
		return ImageLibrary.getInstance().getGeneratePasswordImage();
	}
	
	@Override
	protected Pane createContentPane() {
		VBox contentPane = new VBox();
		contentPane.setSpacing(10);
		
		lengthSpinner = new Spinner<>(0, 200, 16);
//		lengthSpinner.setEditable(true); //Does not properly update the value
		
		upperCaseCheckBox = new CheckBox("Upper Case (\"A\", \"B\", ...)");
		upperCaseCheckBox.setSelected(true);
		lowerCaseCheckBox = new CheckBox("Lower Case (\"a\", \"b\", ...)");
		lowerCaseCheckBox.setSelected(true);
		digitsCheckBox = new CheckBox("Digits (\"0\", \"1\", ...)");
		digitsCheckBox.setSelected(true);
		
		minusCheckBox = new CheckBox("Minus (\"-\")");
		underscoreCheckBox = new CheckBox("Underscore (\"_\")");
		spaceCheckBox = new CheckBox("Space (\" \")");
		
		
		BorderPane lengthPane = new BorderPane();
		lengthPane.setLeft(new Label("Length:"));
		lengthPane.setCenter(lengthSpinner);
		lengthPane.setRight(new Label("characters"));
		
		
		GridPane characterSetsPane = new GridPane();
		
		characterSetsPane.setHgap(10);
		characterSetsPane.setVgap(10);
		
		characterSetsPane.addColumn(0, upperCaseCheckBox, lowerCaseCheckBox, digitsCheckBox);
		characterSetsPane.addColumn(1, minusCheckBox, underscoreCheckBox, spaceCheckBox);
		
		contentPane.getChildren().addAll(lengthPane, characterSetsPane);
		
		return contentPane;
	}

	@Override
	protected Control getInitialFocusControl() {
		return lengthSpinner;
	}
	
	
	@Override
	protected String getOKResult() {
		if (passwordGenerator == null) {
			ErrorUtil.showFactoryMethodError(IPasswordGenerator.class);
			
			return "";
		}
		
		int length = lengthSpinner.getValue();
		
		List<CharacterSet> characterSets = new ArrayList<CharacterSet>();
		
		if (upperCaseCheckBox.isSelected()) {
			characterSets.add(CharacterSet.UPPER_CASE);
		}

		if (lowerCaseCheckBox.isSelected()) {
			characterSets.add(CharacterSet.LOWER_CASE);
		}
		
		if (digitsCheckBox.isSelected()) {
			characterSets.add(CharacterSet.DIGITS);
		}
		
		if (minusCheckBox.isSelected()) {
			characterSets.add(CharacterSet.MINUS);
		}
		
		if (underscoreCheckBox.isSelected()) {
			characterSets.add(CharacterSet.UNDERSCORE);
		}
		
		if (spaceCheckBox.isSelected()) {
			characterSets.add(CharacterSet.SPACE);
		}
		
		return passwordGenerator.generatePassword(length, characterSets);
	}
}
