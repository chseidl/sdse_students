package portunus.view.javafx;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class OKCancelDialog<T> extends Dialog<T> {
	
	public OKCancelDialog() {
		assembleControl();
		initializeControl();
	}
	
	protected abstract String createTitle();
	
	protected Image createImage() {
		return null;
	}
	
	protected abstract Pane createContentPane();
	
	protected void assembleControl() {
		setTitle(createTitle());
		setResizable(true);
		
		
		setIcon();

		DialogPane dialogPane = getDialogPane();
		
		Pane contentPane = createContentPane();
		dialogPane.setContent(contentPane);
		
		addButtons();
		
		Callback<ButtonType, T> resultConverter = createResultConverter();
		setResultConverter(resultConverter);
	}
	
	protected void initializeControl() {
		Control initialFocusControl = getInitialFocusControl();
		
		if (initialFocusControl != null) {
			Platform.runLater(() -> initialFocusControl.requestFocus());
		}
	}
	
	protected Callback<ButtonType, T> createResultConverter() {
		return new Callback<ButtonType, T>() {
			@Override
			public T call(ButtonType buttonType) {
			    if (buttonType == ButtonType.OK) {
			    	return getOKResult();
			    }
			    
			    if (buttonType == ButtonType.CANCEL) {
			    	return getCancelResult();
			    }
			    
			    return null;
			}
		};
	}
	
	protected Control getInitialFocusControl() {
		return null;
	}
	
	protected abstract T getOKResult();
	
	protected T getCancelResult() {
		return null;
	}
	
	protected Pane getContentPane() {
		DialogPane dialogPane = getDialogPane();
		return (Pane) dialogPane.getContent();
	}
	
	private void setIcon() {
		//Set icon if provided.
		Image image = createImage();
		
		if (image != null) {
			Stage stage = (Stage) getDialogPane().getScene().getWindow();
			List<Image> icons = stage.getIcons();
			icons.add(image);
		}
	}
	
	private void addButtons() {
		DialogPane dialogPane = getDialogPane();
		List<ButtonType> buttonTypes = dialogPane.getButtonTypes();
		buttonTypes.add(ButtonType.OK);
		buttonTypes.add(ButtonType.CANCEL);
	}
}
