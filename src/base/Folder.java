package base;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Folder implements Comparable<Folder>, java.io.Serializable{

	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		String current = null;
		String last;
		List<Note> result = new ArrayList<Note>();
		result.addAll(notes);
		keywords = keywords.toLowerCase();
		
		while (keywords != null && keywords.indexOf(' ') != -1) {
			last = current;
			current = keywords.substring(0, keywords.indexOf(' '));
			keywords = keywords.substring(keywords.indexOf(' ')+1);
			
			if (keywords.indexOf(' ') == -1 || keywords.substring(0, keywords.indexOf(' ')).compareTo("or") != 0) {
				if (current.compareTo("or") == 0) {
					
					if (keywords.indexOf(' ') != -1) {
						current = keywords.substring(0, keywords.indexOf(' '));
						keywords = keywords.substring(keywords.indexOf(' ')+1);
					} else {
						current = keywords;
						keywords = null;
					}

					for (Iterator<Note> iterator = result.iterator(); iterator.hasNext();) {
						Note n = iterator.next();
						if (n.getTitle().toLowerCase().indexOf(current) == -1
								&& n.getTitle().toLowerCase().indexOf(last) == -1) {
							if (n.getClass().getTypeName() == "base.TextNote") {
								if (((TextNote) n).getContent().toLowerCase().indexOf(current) == -1
										&& ((TextNote) n).getContent().toLowerCase().indexOf(last) == -1)
									iterator.remove();						
							} else iterator.remove();
						}
					}
					
				} else {
					
					for (Iterator<Note> iterator = result.iterator(); iterator.hasNext();) {
						Note n = iterator.next();
						if (n.getTitle().toLowerCase().indexOf(current) == -1) {
							if (n.getClass().getTypeName() == "base.TextNote") {
								if (((TextNote) n).getContent().toLowerCase().indexOf(current) == -1) 
									iterator.remove();
							} else iterator.remove();
						}
					}
					
				}
			}
		}
		if (keywords == null) return result;
		
		for (Iterator<Note> iterator = result.iterator(); iterator.hasNext();) {
			Note n = iterator.next();
			if (n.getTitle().toLowerCase().indexOf(keywords) == -1) {
				if (n.getClass().getTypeName() == "base.TextNote") {
					if (((TextNote) n).getContent().toLowerCase().indexOf(keywords) == -1) 
						iterator.remove();
				} else iterator.remove();
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note n:notes) {
			if (n instanceof TextNote) nText++;
			else nImage++;
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		if (name.compareTo(o.name) > 0) return 1;		//name larger than target
		else if (name.compareTo(o.name) < 0) return -1;	//name smaller than target
		else return 0;									//name same as target
	}
	
	
	
}
