package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Folder implements Comparable<Folder>, java.io.Serializable{
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.notes = new ArrayList<Note>();
		this.name = name;
	}
	public void addNote(Note note) {
		if (note != null)
			notes.add(note);
	}
	public String getName(){
		return this.name;
	}
	public ArrayList<Note> getNotes() {
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
	public int compareTo(Folder o) {
		return name.compareTo(o.name);
	}
	public void sortNotes() {
		if (notes != null)
			Collections.sort(notes);
	}
	public List<Note> searchNotes(String keywords) {
		String[] keys = keywords.split(" ");
		List<Note> keyNotes = new ArrayList<>();
		String key = keys[0];
		for (int i=0;i<notes.size();i++) {
			TextNote tnote = null;
			boolean hit = true;
			if (notes.get(i) instanceof TextNote) {
				tnote = (TextNote) notes.get(i);
			}
			for(int k=0; k < keys.length ; k++) {
				key = keys[k];
				if (notes.get(i).nsearch(key) || tnote!=null && tnote.tsearch(key)) {
					if (k+1<keys.length && keys[k+1].toLowerCase().equals("or")) 
						k+=2;
					continue;
				}
				else if (k+1<keys.length && keys[k+1].toLowerCase().equals("or")) {
					k++; continue;
				}
				hit = false;
				break;
			}
			if (hit)
				keyNotes.add(notes.get(i));
        }
		return keyNotes;
	}
	public boolean removeNotes(String title) {
		for (Note n : notes){
			if (n.getTitle().equals(title)) {
				notes.remove(n);
				return true;
			}
		}
		return false;
	}
}

