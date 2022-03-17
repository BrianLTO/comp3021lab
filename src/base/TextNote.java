package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note implements java.io.Serializable{
	
	private String content;
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
			File file = new File(absolutePath);
			FileInputStream fInput = new FileInputStream(file);
			InputStreamReader sReader = new InputStreamReader (fInput);
			BufferedReader bReader = new BufferedReader(sReader);
			result = bReader.readLine();
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		pathFolder.replace(" ", "_");
		if (pathFolder == "") pathFolder = ".";
		try {
			File file = new File(pathFolder + File.separator + getTitle().replace(" ", "_") + ".txt");
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(this.content);
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
}
