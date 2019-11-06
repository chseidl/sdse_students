package eu.portunus.view.control;

import eu.portunus.core.IPasswordRecord;
import javafx.scene.control.ReadOnlyCellValueFactory;

public class PasswordRecordPasswordCellValueFactory extends ReadOnlyCellValueFactory<IPasswordRecord, String> {
	@Override
	protected String formatValue(IPasswordRecord passwordRecord) {
		String password = passwordRecord.getPassword();
		
		if (password.isEmpty()) {
			return "";
		} else {
			return "*****";
		}
	}
}
