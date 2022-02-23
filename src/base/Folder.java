package base;

import java.util.ArrayList;

public class Folder{
	public java.util.ArrayList<Note> notes;
	public String name;
	
	public Folder(String name) {
		this.notes = new ArrayList<Note>();
		this.name = name;
	}
	public void addNote(Note note) {
		notes.add(note);
	}
	public String getName(){
		return this.name;
	}
	public java.util.ArrayList<Note> getNotes() {
		return this.notes;
	}
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (int i=0; i<notes.size(); i++) {
			if (notes.get(i) instanceof TextNote)
				nText++;
			else
				nImage++;
		}
		return name + ":" + nText + ":" + nImage;
	}
	public boolean equals(Object name) {
		return this.name.equals(name);
	}
}

