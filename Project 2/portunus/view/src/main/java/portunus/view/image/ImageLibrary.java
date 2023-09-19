package portunus.view.image;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class ImageLibrary {
	private static ImageLibrary instance;
	
	private List<Image> applicationImages;
	
	private Image masterPasswordImage;
	
	private Image passwordGroupImage;
	
	private Image addPasswordGroupImage;
	private Image editPasswordGroupImage;
	private Image deletePasswordGroupImage;
	
	
	private Image passwordRecordImage;
	
	private Image copyUserImage;
	private Image copyPasswordImage;
	
	private Image addPasswordRecordImage;
	private Image editPasswordRecordImage;
	private Image deletePasswordRecordImage;
	
	private Image generatePasswordImage;
	
	private ImageLibrary() {
		loadImages();
	}
	
	public static ImageLibrary getInstance() {
		if (instance == null) {
			instance = new ImageLibrary();
		}
		
		return instance;
	}
	
	private void loadImages() {
		applicationImages = new ArrayList<>();
		int[] applicationImageResolutions = new int[] {16, 32, 64, 128, 256};
		
		for (int applicationImageResolution : applicationImageResolutions) {
			Image applicationImage = loadApplicationImage("Application", applicationImageResolution, "png");
			applicationImages.add(applicationImage);
		}
		
		masterPasswordImage = loadLocalImage("MasterPassword.png");
		
		passwordGroupImage = loadLocalImage("PasswordGroup.png");
		
		addPasswordGroupImage = loadLocalImage("AddPasswordGroup.png");
		editPasswordGroupImage = loadLocalImage("EditPasswordGroup.png");
		deletePasswordGroupImage = loadLocalImage("DeletePasswordGroup.png");
		
		passwordRecordImage = loadLocalImage("PasswordRecord.png");
		
		copyUserImage = loadLocalImage("CopyUser.png");
		copyPasswordImage = loadLocalImage("CopyPassword.png");
		
		addPasswordRecordImage = loadLocalImage("AddPasswordRecord.png");
		editPasswordRecordImage = loadLocalImage("EditPasswordRecord.png");
		deletePasswordRecordImage = loadLocalImage("DeletePasswordRecord.png");
		
		generatePasswordImage = loadLocalImage("GeneratePassword.png");
	}
	
	private Image loadApplicationImage(String imageBaseName, int resolution, String imageExtension) {
		String imageName = imageBaseName + resolution + "x" + resolution + "." + imageExtension;
		return loadLocalImage(imageName);
	}
	
	private Image loadLocalImage(String imageName) {
		return new Image(imageName);
	}

	public List<Image> getApplicationImages() {
		return applicationImages;
	}

	public Image getMasterPasswordImage() {
		return masterPasswordImage;
	}
	
	public Image getPasswordGroupImage() {
		return passwordGroupImage;
	}

	public Image getAddPasswordGroupImage() {
		return addPasswordGroupImage;
	}

	public Image getEditPasswordGroupImage() {
		return editPasswordGroupImage;
	}

	public Image getDeletePasswordGroupImage() {
		return deletePasswordGroupImage;
	}

	public Image getPasswordRecordImage() {
		return passwordRecordImage;
	}

	public Image getCopyUserImage() {
		return copyUserImage;
	}
	
	public Image getCopyPasswordImage() {
		return copyPasswordImage;
	}
	
	public Image getAddPasswordRecordImage() {
		return addPasswordRecordImage;
	}

	public Image getEditPasswordRecordImage() {
		return editPasswordRecordImage;
	}

	public Image getDeletePasswordRecordImage() {
		return deletePasswordRecordImage;
	}

	public Image getGeneratePasswordImage() {
		return generatePasswordImage;
	}
}
