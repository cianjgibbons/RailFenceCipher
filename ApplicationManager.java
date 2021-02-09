package ie.gmit.dip;
import java.util.*;

/**
 * ApplicationManager is a concrete class that contains and implements the
 * methods required for the correct function of the application.
 * 
 * @author Cian Gibbons
 * @version 1.0
 */
public class ApplicationManager {

	private Scanner scanner;
	private boolean keepRunning = true;
	private int choice;
	private String input;
	private String originalText;
	private int keyEntered;
	private int validKey;
	private int startPositionEntered;
	private int validStartPosition;
	private String encryptedMessage;
	private String decryptedMessage;
	private String railFence;
	private InputConnection iC;
	private RailFenceCipher rFC;
	
	/**
	 * Initializes the application.
	 */
	public void start() {
		
		// This ensures the program stays running until the user requests it to stop.
		while (keepRunning) {
			
			showOptions();
			
			this.scanner = new Scanner(System.in);
			this.setChoice(scanner.nextInt());
			
			// Relevant function selected based on user input. Try/catch exception handling used to handle any potential errors.
			if (this.getChoice() == 1) {
				selectFileOrURL();
			} else if (this.getChoice() == 2) {
				enterRailFenceKey();
			} else if (this.getChoice() == 3) {
				try {
					encrypt();
				} catch (Exception e) {
					System.out.println("[ERROR] Please ensure File/URL and Rail Fence Key have been entered.");
				}
			} else if (this.getChoice() == 4) {
				try {
				decrypt();
				} catch (Exception e) {
					System.out.println("[ERROR] Please ensure File/URL and Rail Fence Key have been entered, and encryption has been carried out.");
				}
			} else if (this.getChoice() == 5) {
				try {
				displayRailFence();
				} catch (Exception e) {
					System.out.println("[ERROR] Please ensure File/URL and Rail Fence Key have been entered.");
				}
			} else if (this.getChoice() == 6) {
				System.out.println("[INFO] System shutting down...please wait...");
				// Shuts down and exits application.
				this.keepRunning = false;
			} else {
				System.out.println("[ERROR] Invalid input.");
			}
		}
	}
	
	/**
	 * Takes in the .txt file/URL to be encrypted and processes it based on user input.
	 */
	private void selectFileOrURL() {
		
		System.out.println("[INFO] Select File or URL:");
		
		// File/URL entered by user.
		this.setInput(scanner.next());
		
		// Statement to recognize that a URL has been entered and call getUrlContents method from InputConnection class.
		if (this.getInput().contains("http")) {
			this.iC = new InputConnection();
			this.setOriginalText(iC.getUrlContents(getInput()));
			
			// Statement to recognize and differentiate .txt file from a URL and call getFileContents method from InputConnection class.
		} else if (this.getInput().contains(".txt")) {
			this.iC = new InputConnection();
			this.setOriginalText(iC.getFileContents(getInput()));
		} else {
			System.out.println("[ERROR] Invalid input.");
		}
		System.out.println("[INFO] Input accepted.");
		 
	}
	
	/**
	 * Takes in and processes the rail fence key and start position based on user input.
	 */
	private void enterRailFenceKey() {
		
		System.out.println("[INFO] Enter Rail Fence Key between 2 & 10:");
		
		// Key entered by user.
		this.setKeyEntered(scanner.nextInt());
		System.out.println("[INFO] Enter Rail Fence Start Position between 1 and Key Length Selected:");
		this.setStartPositionEntered(scanner.nextInt());
		
		// Statement to ensure that key and start position are within range.
		if (getKeyEntered() >= 2 && getKeyEntered() <= 10 && getStartPositionEntered() >= 1 && getStartPositionEntered() <= getKeyEntered()) {
			this.setValidKey(getKeyEntered());
			this.setValidStartPosition(getStartPositionEntered() - 1);
		} else {
			System.out.println("[ERROR] Invalid Rail Fence Key/Start Position entered.");	
		}
		System.out.println("[INFO] Key accepted.");
	}
	
	/**
	 * Processes the encryption functionality.
	 */
	private void encrypt() {
		
		System.out.println("[INFO] Encrypt");
		
		// Creates new instance of the RailFenceCipher class and calls the encryption method.
		this.rFC = new RailFenceCipher();
		
		// Displays encryption based on URL/File, rail fence key and start position entered.
		this.setEncryptedMessage(rFC.encryption(getOriginalText(), getValidKey(), getValidStartPosition()));
		System.out.println(getEncryptedMessage());	
	}
	
	/**
	 * Processes the decryption functionality.
	 */
	private void decrypt() {
		
		System.out.println("[INFO] Decrypt");
		
		// Creates a new instance of the RailFenceCipher class and calls the decryption method.
		this.rFC = new RailFenceCipher();
		
		// Displays decryption based on URL/File and Rail Fence Key entered.
		this.setDecryptedMessage(rFC.decryption(getEncryptedMessage(), getValidKey(), getValidStartPosition()));
		System.out.println(getDecryptedMessage());
	}
	
	/**
	 * Displays the rail fence.
	 */
	private void displayRailFence() {
		
		System.out.println("[INFO] Display Rail Fence");
		
		// Creates a new instance of the RailFenceCipher class and calls the displayRailFence method.
		this.rFC = new RailFenceCipher();
		
		// Displays rail fence based on URL/File and rail fence key entered.
		this.setRailFence(rFC.displayRailFence(getOriginalText(), getValidKey(), getValidStartPosition()));
		System.out.println(getRailFence());
	}
	
	/**
	 * Displays a list of menu options.
	 */
	private void showOptions() {
		
		System.out.println(" ");
		System.out.println("###########################");
		System.out.println("#    Rail Fence Cipher    #");
		System.out.println("###########################");
		System.out.println("1. Select File or URL");
		System.out.println("2. Enter Rail Fence Key");
		System.out.println("3. Encrpyt");
		System.out.println("4. Decrypt");
		System.out.println("5. Display Rail Fence");
		System.out.println("6. Quit");
		System.out.println(" ");
		System.out.println("Select Option [1-6]>");
	}
	
	/**
	 * 
	 * @return the value of the menu choice
	 */
	public int getChoice() {
		return choice;
	}
	
	/**
	 * 
	 * @param choice sets the value of the menu choice.
	 */
	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	/**
	 * 
	 * @return the value of the file/URL input.
	 */
	public String getInput() {
		return input;
	}
	
	/**
	 * 
	 * @param input set the value of the file/URL input.
	 */
	public void setInput(String input) {
		this.input = input;
	}
	
	/**
	 * 
	 * @return the value of the text from the file/URL.
	 */
	public String getOriginalText() {
		return originalText;
	}
	
	/**
	 * 
	 * @param originalText set the value of the text from the file/URL.
	 */
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	
	/**
	 * 
	 * @return the value of the key.
	 */
	public int getKeyEntered() {
		return keyEntered;
	}
	
	/**
	 * 
	 * @param keyEntered set the yalue of the key.
	 */
	public void setKeyEntered(int keyEntered) {
		this.keyEntered = keyEntered;
	}
	
	/**
	 * 
	 * @return the start position.
	 */
	public int getStartPositionEntered() {
		return startPositionEntered;
	}
	
	/**
	 * 
	 * @param startPositionEntered set the start position.
	 */
	public void setStartPositionEntered(int startPositionEntered) {
		this.startPositionEntered = startPositionEntered;
	}
	
	/**
	 * 
	 * @return the valid key value.
	 */
	public int getValidKey() {
		return validKey;
	}
	
	/**
	 * 
	 * @param validKey set the valid key value.
	 */
	public void setValidKey(int validKey) {
		this.validKey = validKey;
	}
	
	/**
	 * 
	 * @return the valid start position.
	 */
	public int getValidStartPosition() {
		return validStartPosition;
	}
	
	/**
	 * 
	 * @param validStartPosition set the valid start position.
	 */
	public void setValidStartPosition(int validStartPosition) {
		this.validStartPosition = validStartPosition;
	}
	
	/**
	 * 
	 * @return the encrypted message.
	 */
	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	/**
	 * 
	 * @param encryptedMessage set the encrypted method.
	 */
	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	/**
	 * 
	 * @return the decrypted message.
	 */
	public String getDecryptedMessage() {
		return decryptedMessage;
	}

	/**
	 * 
	 * @param decryptedMessage set the decrypted message.
	 */
	public void setDecryptedMessage(String decryptedMessage) {
		this.decryptedMessage = decryptedMessage;
	}

	/**
	 * 
	 * @return the rail fence.
	 */
	public String getRailFence() {
		return railFence;
	}

	/**
	 * 
	 * @param railFence set the value of the rail fence.
	 */
	public void setRailFence(String railFence) {
		this.railFence = railFence;
	}

}
