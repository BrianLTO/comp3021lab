package base;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, java.io.Serializable{
	
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean equals(Note note) {
		if (this.title == note.getTitle()) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title);
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
		return Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		if (date.before(o.date)) return 1;		//created later than target
		else if (date.after(o.date)) return -1;	//created earlier than target
		else return 0;							//created same time as target
	}

	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
	
}
