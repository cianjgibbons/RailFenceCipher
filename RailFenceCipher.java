package ie.gmit.dip;

/**
 * RailFenceCipher is a concrete class that contains and implements the methods
 * responsible for the encryption and decryption functionality.
 * 
 * @author Cian Gibbons
 * @version 1.0
 */
public class RailFenceCipher {

	private boolean verify;
	private int row;
	private int col;
	private int position;
	private int width;
	private int index;
	private char[][] matrix;
	private StringBuilder encryptedText;
	private StringBuilder decryptedText;

	/**
	 * Encrypts the given text using a rail fence of a user defined size and start
	 * position.
	 * 
	 * @param text  the text to be encrypted.
	 * @param key   the value of the rail fence key.
	 * @param start the start position of the rail fence.
	 * @return the encrypted text.
	 */
	public String encryption(String text, int key, int start) {

		this.encryptedText = new StringBuilder();
		this.verify = false;
		this.position = start;
		this.row = key;
		this.col = text.length();
		this.matrix = new char[row][col];

		// For loop to iterate up and down the rows of the rail fence
		// (two-dimensional char array).
		for (int i = 0; i < col; i++) {
			// Changes direction based on position of character on the rail fence row.
			if (position == 0 || this.position == key - 1)
				verify = !verify;
			// Inserts character at given position on fence.
			matrix[position][i] = text.charAt(i);

			if (verify)
				position++;
			else
				position--;
		}

		// Traverses the columns of array line by line and appends the characters to
		// the encryptedText StringBuilder.
		for (int i = 0; i < row; i++) {
			for (width = 0; width < col; width++) {
				if (matrix[i][width] != 0) {
					encryptedText.append(matrix[i][width]);

				}
			}
		}
		return encryptedText.toString();
	}

	/**
	 * Decrypts the encrypted text using a rail fence of a user defined size and
	 * start position.
	 * 
	 * @param text  the text to be decrypted.
	 * @param key   the value of the rail fence key.
	 * @param start the start position of the rail fence.
	 * @return the decrypted text.
	 */
	public String decryption(String text, int key, int start) {

		this.decryptedText = new StringBuilder();
		this.verify = false;
		this.position = start;
		this.row = key;
		this.col = text.length();
		this.matrix = new char[row][col];

		// Two-dimensional char array again used to create rail fence, except this time
		// '*' is inserted in each position.
		for (int i = 0; i < col; i++) {
			if (position == 0 || position == key - 1)
				verify = !verify;

			matrix[position][i] = '*';

			if (verify)
				position++;
			else
				position--;
		}
		// index variable used to traverse across characters in the encryptedText String.
		this.index = 0;
		verify = false;

		// For loop to traverse across the array, and replace each '*' symbol with the
		// character at the relevant index position of the encryptedText String.
		for (int i = 0; i < row; i++) {
			for (width = 0; width < col; width++) {
				if (matrix[i][width] == '*' && index < col) {
					matrix[i][width] = text.charAt(index++);
				}
			}
		}
		// Traverses the rail fence and appends each character to the decryptedText
		// StringBuilder.
		this.position = start;
		for (int i = 0; i < col; i++) {
			if (position == 0 || position == key - 1)
				verify = !verify;

			decryptedText.append(matrix[position][i]);

			if (verify)
				position++;
			else
				position--;
		}
		return decryptedText.toString();
	}

	/**
	 * Displays the Rail Fence.
	 * 
	 * @param text  the text to be encrypted.
	 * @param key   the value of the rail fence key.
	 * @param start the start position of the rail fence.
	 * @return the Rail Fence.
	 */
	public String displayRailFence(String text, int key, int start) {
		this.encryptedText = new StringBuilder();
		this.verify = false;
		this.position = start;
		this.row = key;
		this.col = text.length();
		this.matrix = new char[row][col];

		for (int i = 0; i < col; i++) {
			if (position == 0 || position == key - 1)
				verify = !verify;

			matrix[position][i] = text.charAt(i);

			if (verify)
				position++;
			else
				position--;
		}
		for (int i = 0; i < row; i++) {
			for (width = 0; width < col; width++) {
				System.out.print(matrix[i][width] + " ");
			}
			System.out.println();
		}
		return encryptedText.toString();
	}
}
