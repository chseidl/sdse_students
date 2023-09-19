package portunus.view.util;

import portunus.core.IPortunusFactory;
import portunus.view.image.ImageLibrary;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ErrorUtil {
	public static void showFactoryMethodError(Class<?> interfaceToInstantiate) {
		showFactoryMethodError(interfaceToInstantiate, null);
	}
	
	public static void showFactoryMethodError(Class<?> interfaceToInstantiate, String additum) {
		String factoryInterfaceName = IPortunusFactory.class.getCanonicalName();
		String interfaceToInstantiateName = interfaceToInstantiate.getCanonicalName();
		
		String message = "";
		message += "Instance of \"" + interfaceToInstantiateName + "\" could not be created.\n\n";
		message += "Did you implement the respective factory method of \"" + factoryInterfaceName + "\"?";
		
		if (additum != null) {
			message += "\n\n" + additum;
		}
		
		showError(message);
	}
	
	public static void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().addAll(ImageLibrary.getInstance().getApplicationImages());
		
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
}
