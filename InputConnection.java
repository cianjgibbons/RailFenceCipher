package ie.gmit.dip;

import java.net.*;
import java.io.*;

/**
 * InputConnection is a concrete class that implements the methods responsible
 * for processing the contents of the URL/.txt file to a String of text.
 * 
 * @author Cian Gibbons
 * @version 1.0
 */
public class InputConnection {

	private String line;
	private StringBuilder urlContents;
	private StringBuilder fileContents;
	private URL url;
	private URLConnection urlConnection;
	private BufferedReader bufferedReader;
	private File file;
	private FileReader fileReader;

	/**
	 * Processes the contents of the URL input by the user to a String of text.
	 * 
	 * @param myURL sets the value of the URL.
	 * @return the contents of the URL as a String.
	 */
	public String getUrlContents(String myURL) {

		// Creates a new StringBuilder object.
		this.urlContents = new StringBuilder();
		try {
			// Creates a new URL object.
			this.url = new URL(myURL);

			// Creates a URLConnection object.
			this.urlConnection = url.openConnection();

			// Wraps the URLConnection in a BufferedReader.
			this.bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			// Reads from the URLConnection via the BufferedReader and appends to the
			// StringBuilder.
			while ((this.line = bufferedReader.readLine()) != null) {
				urlContents.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("[ERROR] Unable to close URL");
		}
		return urlContents.toString();
	}

	/**
	 * Processes the contents of the .txt file input by the user to a String of
	 * text.
	 * 
	 * @param myFile sets the value of the file.
	 * @return the contents of the .txt file as a String.
	 */
	public String getFileContents(String myFile) {

		// Creates a new StringBuilder object.
		this.fileContents = new StringBuilder();

		// Creates a new File object.
		this.file = new File(myFile);

		try {
			// Creates a FileReader object.
			this.fileReader = new FileReader(file);

			// Wraps the FileReader in a BufferedReader.
			this.bufferedReader = new BufferedReader(fileReader);

			// Reads from the FileReader via the BufferedReader and appends to the String.
			while ((this.line = bufferedReader.readLine()) != null) {
				fileContents.append(line + "\n");
			}
			// If file cannot be located.
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] File not found: " + file.toString());
			// If file cannot be read.
		} catch (IOException e) {
			System.out.println("[ERROR] Unable to read file: " + file.toString());
		}

		try {
			bufferedReader.close();
			// If file cannot be closed.
		} catch (IOException e) {
			System.out.println("[ERROR] Unable to close file: " + file.toString());
			// If file contains a null value.
		} catch (NullPointerException ex) {
			System.out.println("[ERROR] " + file.toString() + "contains no information.");
		}
		return fileContents.toString();
	}
}