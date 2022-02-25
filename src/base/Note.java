package base;

import java.util.Objects;
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
	public int hashCode() {
		return Objects.hash(title);
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		if (title == null && other.title == null)
			return true;
		if (title == null || other.title == null)
			return false;
		return Objects.equals(title, other.title);
	}
}

