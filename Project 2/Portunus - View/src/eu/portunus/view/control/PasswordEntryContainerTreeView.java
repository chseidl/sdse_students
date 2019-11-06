package eu.portunus.view.control;

import eu.portunus.core.IPasswordEntryContainer;
import javafx.scene.control.TreeView;

public class PasswordEntryContainerTreeView extends TreeView<IPasswordEntryContainer> {
	public PasswordEntryContainerTreeView() {
		initializeControl();
	}
	
	private void initializeControl() {
		setShowRoot(false);
	}
}
