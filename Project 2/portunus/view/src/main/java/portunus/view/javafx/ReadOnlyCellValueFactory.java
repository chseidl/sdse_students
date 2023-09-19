package portunus.view.javafx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;

public abstract class ReadOnlyCellValueFactory<S, T> implements CellValueFactory<S, T> {
	@Override
	public ObservableValue<T> call(CellDataFeatures<S, T> param) {
		S value = param.getValue();
		T formattedValue = formatValue(value);
		
		return new ReadOnlyObjectWrapper<T>(formattedValue);
	}

	protected abstract T formatValue(S value);
}
