package base;

import java.util.Date;

public class Note{
	
	public Date date;
	public String title;
	
	public Note(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public boolean equals(Note note) {
		return this.title.equals(note.title);
	}
	public boolean equals(Object title) {
		return this.title.equals(title);
	}
}

