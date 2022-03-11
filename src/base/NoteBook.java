package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		return insertNote(folderName, new TextNote(title));
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		return insertNote(folderName, new TextNote(title, content));
	}
	
	public boolean createImageNote(String folderName, String title) {
		return insertNote(folderName, new ImageNote(title));
	}
	
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		
		Folder found = null;
		for (Folder f: folders) {
			if (f.getName() == folderName) found = f;
		}
		
		if (found == null) {
			found = new Folder(folderName);
			folders.add(found);
		}
		
		for (Note n: found.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note " + note.getTitle()
			+ " under folder " + folderName + " failed");
				return false;
			}
		}
		
		found.addNote(note);
		return true;
	}
	
	public void sortFolders() {
		for (Folder f: folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		for (Folder f: folders) {
			result.addAll(f.searchNotes(keywords));
		}
		return result;
	}
	
}
