package mynote;

public class Note {
	private Integer idNote;
	private String noteText;
	private String tag;
	private String color;
	private String icon;
	private Integer idUser;
	public Integer getIdNote() {
		return idNote;
	}
	public void setIdNote(Integer idNote) {
		this.idNote = idNote;
	}
	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

}