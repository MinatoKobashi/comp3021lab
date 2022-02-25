package base;

import java.util.ArrayList;
import java.util.Objects;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		if (name == null && other.name == null)
			return true;
		if (name == null || other.name == null)
			return false;
		return Objects.equals(name, other.name);
	}
}

