package base;

import java.util.ArrayList;

public class NoteBook {
	
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, new TextNote(title));
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
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
	
}
