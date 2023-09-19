package portunus.view.dialog;

import portunus.core.IPasswordGroup;
import portunus.view.image.ImageLibrary;
import javafx.scene.image.Image;

public class AddPasswordGroupDialog extends AddEditPasswordGroupDialog {
	public AddPasswordGroupDialog(IPasswordGroup passwordGroup) {
		super(passwordGroup);
	}
	
	@Override
	protected String createTitle() {
		return "Add Group...";
	}
	
	@Override
	protected Image createImage() {
		return ImageLibrary.getInstance().getAddPasswordGroupImage();
	}
}
