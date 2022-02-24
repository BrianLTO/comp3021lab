package base;

import java.util.Date;
import java.util.Objects;

public class Note {
	
	private Date date;
	private String string;
	
	public Note(String title) {
		this.string = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return string;
	}
	
	public boolean equals(Note note) {
		if (this.string == note.getTitle()) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(string);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(string, other.string);
	}
}
