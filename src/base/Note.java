package base;

import java.util.Objects;
import java.util.Date;

public class Note implements Comparable<Note>, java.io.Serializable{
	
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date();
	}
	public String getTitle() {
		return title;
	}
	public boolean equals(Note note) {
		return title.equals(note.title);
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
	public int compareTo(Note o) {
		if (date.after(o.date))
			return 1;
		if (date.before(o.date))
			return -1;
		return 0;
	}
	public boolean nsearch(String key) {
		return title.toLowerCase().contains(key.toLowerCase());
	}
	public String toString() {
		return date.toString() + "\t" + title;
	}
}

