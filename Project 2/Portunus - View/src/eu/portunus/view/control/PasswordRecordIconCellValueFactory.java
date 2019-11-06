package eu.portunus.view.control;

import eu.portunus.core.IPasswordRecord;
import eu.portunus.view.image.ImageLibrary;
import javafx.scene.control.ReadOnlyCellValueFactory;
import javafx.scene.image.ImageView;

public class PasswordRecordIconCellValueFactory extends ReadOnlyCellValueFactory<IPasswordRecord, ImageView> {
	@Override
	protected ImageView formatValue(IPasswordRecord value) {
		return new ImageView(ImageLibrary.getInstance().getPasswordRecordImage());
	}
}
