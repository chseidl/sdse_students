package portunus.view.javafx;

import java.util.List;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public abstract class AddEditDialog<T> extends OKCancelDialog<T> {
	private T model;
	
	public AddEditDialog(T model) {
		this.model = model;
		
		updateViewFromModel();
	}
	
	@Override
	protected GridPane createContentPane() {
		GridPane gridPane = new GridPane();
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		configureColumns(gridPane);
		
		return gridPane;
	}
	
	protected void configureColumns(GridPane contentPane) {
		List<ColumnConstraints> columnConstraints = contentPane.getColumnConstraints();
		
		columnConstraints.clear();
		
		columnConstraints.add(new ColumnConstraints());
		
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setHgrow(Priority.ALWAYS);
		columnConstraints.add(column2);
	}
	
	@Override
	protected void assembleControl() {
		super.assembleControl();
		
		GridPane gridPane = getContentPane();
		assembleContentPane(gridPane);
	}
	
	@Override
	protected GridPane getContentPane() {
		return (GridPane) super.getContentPane();
	}
	
	protected abstract void assembleContentPane(GridPane gridPane);
	
	@Override
	protected T getOKResult() {
		updateModelFromView();
		return model;
	}
	
	protected abstract void updateViewFromModel();
	protected abstract void updateModelFromView();

	protected T getModel() {
		return model;
	}
}
