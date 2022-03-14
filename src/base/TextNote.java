package base;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextNote extends Note {

	String content;
	
	public TextNote(String title){
		super(title);
	}
	public String getContent() {
		return content;
	}
	public boolean tsearch(String key) {
		return content.toLowerCase().contains(key.toLowerCase());
	}
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	private String getTextFromFile(String absolutePath) {
		String result = "";
		BufferedReader rd = null;
        try {
            rd = new BufferedReader(new FileReader(new File(absolutePath)));
            String inputLine = null;
            while((inputLine = rd.readLine()) != null)
                result += inputLine+"\n";
            rd.close();
		} catch (IOException e) {
            e.printStackTrace();
			return null;
		}
		return result;
	}
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	public void exportTextToFile(String pathFolder) {
		if (pathFolder =="")
			pathFolder = ".";
		File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
		BufferedWriter wr = null;
        try {
            wr = new BufferedWriter(new FileWriter(file));
            wr.write(content);
            wr.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
	}
}