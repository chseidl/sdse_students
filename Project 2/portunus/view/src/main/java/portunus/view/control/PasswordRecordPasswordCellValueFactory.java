package portunus.view.control;

import portunus.core.IPasswordRecord;
import portunus.view.javafx.ReadOnlyCellValueFactory;

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
