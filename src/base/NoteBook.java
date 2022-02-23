package base;

import java.util.ArrayList;

public class NoteBook{
	public ArrayList<Folder> folders;
	
	public NoteBook() {
		this.folders = new ArrayList<Folder>();
	}
	public boolean createTextNote(String name, String title) {
		TextNote note = new TextNote(title);
		return insertNote(name, note);
	}
	public boolean createImageNote(String name, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(name, note);
	}
	public boolean insertNote(String name, Note note) {
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
}