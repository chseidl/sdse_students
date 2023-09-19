package portunus.view.control;

import portunus.core.IPasswordEntryContainer;
import portunus.view.image.ImageLibrary;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class PasswordEntryContainerTreeItem extends TreeItem<IPasswordEntryContainer> {
	public PasswordEntryContainerTreeItem(IPasswordEntryContainer passwordEntryContainer) {
		super(passwordEntryContainer, new ImageView(ImageLibrary.getInstance().getPasswordGroupImage()));
	}
}
