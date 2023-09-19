package portunus.view.control;

import portunus.core.IPasswordRecord;
import portunus.view.image.ImageLibrary;
import portunus.view.javafx.ReadOnlyCellValueFactory;
import javafx.scene.image.ImageView;

public class PasswordRecordIconCellValueFactory extends ReadOnlyCellValueFactory<IPasswordRecord, ImageView> {
	@Override
	protected ImageView formatValue(IPasswordRecord value) {
		return new ImageView(ImageLibrary.getInstance().getPasswordRecordImage());
	}
}
