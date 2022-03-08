package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook{
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		this.folders = new ArrayList<Folder>();
	}
	public boolean createTextNote(String name, String title) {
		if (name == null || title == null)
			return false;
		TextNote note = new TextNote(title);
		return insertNote(name, note);
	}
	public boolean createImageNote(String name, String title) {
		if (name == null || title == null)
			return false;
		ImageNote note = new ImageNote(title);
		return insertNote(name, note);
	}
	public boolean insertNote(String name, Note note) {
		if (name == null || note == null)
			return false;
		for (int i=0; i<folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				for (int k=0; k< folders.get(i).getNotes().size(); k++) {
					if (folders.get(i).getNotes().get(k).equals(note)){
						System.out.println("Creating note "+note.getTitle()+" under folder "+name+" failed");
						return false;
					}
				}
				folders.get(i).addNote(note);
				return true;
			}
		}
		Folder folder = new Folder(name);
		folder.addNote(note);
		folders.add(folder);
		return true;
	}
	public ArrayList<Folder> getFolders() {
		return folders;
	} 
	public void sortFolders() {
		if (folders != null)
			for (int i=0; i<folders.size();i++)
				folders.get(i).sortNotes();
			Collections.sort(folders);
	}
	public List<Note> searchNotes(String keywords){
		List<Note> notes = new ArrayList<>();
		for (int i=0;i<folders.size();i++) {
			notes.addAll(folders.get(i).searchNotes(keywords));
		}
		return notes;
	}
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title,content);
		return insertNote(folderName, note);
	}
}