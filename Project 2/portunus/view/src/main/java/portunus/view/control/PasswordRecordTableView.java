package portunus.view.control;

import java.util.List;

import portunus.core.IPasswordRecord;
import portunus.view.javafx.CellValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PasswordRecordTableView extends TableView<IPasswordRecord> {
	public PasswordRecordTableView() {
		initializeControl();
	}
	
	private void initializeControl() {
		addColumn("", 22, new PasswordRecordIconCellValueFactory());
		addColumn("Title", 150, "title");
		addColumn("User", 150, "user");
		addColumn("Password", 150, new PasswordRecordPasswordCellValueFactory());
		addColumn("URL", 150, "url");	
	}
	
	private TableColumn<IPasswordRecord, ?> addColumn(String name, int preferredWidth, String propertyName) {
		TableColumn<IPasswordRecord, String> column = new TableColumn<>(name);
		column.setPrefWidth(preferredWidth);
		column.setCellValueFactory(new PropertyValueFactory<IPasswordRecord, String>(propertyName));
		
		List<TableColumn<IPasswordRecord, ?>> columns = getColumns();
		columns.add(column);
		
		return column;
	}
	
	private <T> TableColumn<IPasswordRecord, T> addColumn(String name, int preferredWidth, CellValueFactory<IPasswordRecord, T> cellValueFactory) {
		TableColumn<IPasswordRecord, T> column = new TableColumn<>(name);
		column.setPrefWidth(preferredWidth);
		column.setCellValueFactory(cellValueFactory);
		
		List<TableColumn<IPasswordRecord, ?>> columns = getColumns();
		columns.add(column);
		
		return column;
	}
}
