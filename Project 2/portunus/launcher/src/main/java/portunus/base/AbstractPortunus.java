package portunus.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import portunus.PortunusFactory;
import portunus.controller.util.OverviewController;
import portunus.core.IPasswordLibrary;
import portunus.core.IPortunusFactory;
import portunus.util.crypter.DecryptionFailedException;
import portunus.util.crypter.EncryptionFailedException;
import portunus.util.io.IPasswordLibraryLoader;
import portunus.util.io.IPasswordLibrarySaver;
import portunus.view.OverviewPane;
import portunus.view.image.ImageLibrary;
import portunus.view.util.ErrorUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class AbstractPortunus extends Application {
	private static File passwordLibraryFile = new File("PasswordLibrary.portunus");
	
	private String masterPassword;
	private IPasswordLibrary passwordLibrary;
	
	private IPortunusFactory factory;
	
	public AbstractPortunus() {
		factory = new PortunusFactory();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Add application icons
		List<Image> icons = stage.getIcons();
		icons.addAll(ImageLibrary.getInstance().getApplicationImages());
		
		boolean passwordLibraryLoaded = loadPasswordLibrary();

		if (!passwordLibraryLoaded) {
			return;
		}
		
		OverviewPane overviewPane = new OverviewPane();
		OverviewController overviewController = new OverviewController(passwordLibrary, overviewPane, factory);
		
		overviewController.control();
		
		Scene scene = new Scene(overviewPane, 900, 600);
		
		stage.setTitle("Portunus");
		stage.setScene(scene);
		stage.show();
		
		stage.setOnCloseRequest(event -> {
			savePasswordLibrary();
		});
	}

	protected abstract boolean loadPasswordLibrary();
	
	protected IPasswordLibrary doLoadPasswordLibrary() throws FileNotFoundException, DecryptionFailedException {
		IPasswordLibrary passwordLibrary = factory.createPasswordLibrary();
		
		if (passwordLibrary == null) {
			ErrorUtil.showFactoryMethodError(IPasswordLibrary.class, "Application will crash!");
			return null;
		}
		
		if (!passwordLibraryFile.exists()) {
			return passwordLibrary;
		}
		
		
//		ExamplePasswordLibraryCreator examplePasswordLibraryCreator = new ExamplePasswordLibraryCreator();
//		return examplePasswordLibraryCreator.createExampleLibrary(factory);
		
		IPasswordLibraryLoader loader = factory.createPasswordLibraryLoader();
		
		if (loader == null) {
			ErrorUtil.showFactoryMethodError(IPasswordLibraryLoader.class, "Password library will be empty.");
			return passwordLibrary;
		}
		
		return loader.load(passwordLibraryFile, masterPassword, passwordLibrary);
	}
	
	private void savePasswordLibrary() {
		IPasswordLibrarySaver saver = factory.createPasswordLibrarySaver();

		if (saver == null) {
			ErrorUtil.showFactoryMethodError(IPasswordLibrarySaver.class, "Password library will not be saved.");
			return;
		}
		
		try {
			saver.save(passwordLibrary, passwordLibraryFile, masterPassword);
		} catch (EncryptionFailedException e) {
			//Not going to happen in any realistic case.
			ErrorUtil.showError("Encryption of password library failed on save.");
		}
	}

	protected String getMasterPassword() {
		return masterPassword;
	}

	protected void setMasterPassword(String masterPassword) {
		this.masterPassword = masterPassword;
	}

	protected IPasswordLibrary getPasswordLibrary() {
		return passwordLibrary;
	}

	protected void setPasswordLibrary(IPasswordLibrary passwordLibrary) {
		this.passwordLibrary = passwordLibrary;
	}
}
