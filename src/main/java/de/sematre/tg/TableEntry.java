package de.sematre.tg;

import java.io.Serializable;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableEntry implements Serializable, Cloneable {

	private static final long serialVersionUID = -7299985732840441286L;
	private String schoolClass = "";
	private String time = "";
	private String type = "";

	private String replacementSubject = "";
	private String subject = "";
	private String replacementRoom = "";
	private String room = "";
	private String text = "";

	public TableEntry() {}

	public TableEntry(String schoolClass, String time, String type, String replacementSubject, String subject, String replacementRoom, String room, String text) {
		this.schoolClass = schoolClass;
		this.time = time;
		this.type = type;
		this.replacementSubject = replacementSubject;
		this.subject = subject;
		this.replacementRoom = replacementRoom;
		this.room = room;
		this.text = text;
	}

	public String getSchoolClass() {
		return schoolClass;
	}

	public TableEntry setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
		return this;
	}

	public String getTime() {
		return time;
	}

	public TableEntry setTime(String time) {
		this.time = time;
		return this;
	}

	public String getType() {
		return type;
	}

	public TableEntry setType(String type) {
		this.type = type;
		return this;
	}

	public String getReplacementSubject() {
		return replacementSubject;
	}

	public TableEntry setReplacementSubject(String replacementSubject) {
		this.replacementSubject = replacementSubject;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public TableEntry setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getReplacementRoom() {
		return replacementRoom;
	}

	public TableEntry setReplacementRoom(String replacementRoom) {
		this.replacementRoom = replacementRoom;
		return this;
	}

	public String getRoom() {
		return room;
	}

	public TableEntry setRoom(String room) {
		this.room = room;
		return this;
	}

	public String getText() {
		return text;
	}

	public TableEntry setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TableEntry other = (TableEntry) obj;
		if (replacementRoom == null) {
			if (other.replacementRoom != null) return false;
		} else if (!replacementRoom.equals(other.replacementRoom)) return false;
		if (replacementSubject == null) {
			if (other.replacementSubject != null) return false;
		} else if (!replacementSubject.equals(other.replacementSubject)) return false;
		if (room == null) {
			if (other.room != null) return false;
		} else if (!room.equals(other.room)) return false;
		if (schoolClass == null) {
			if (other.schoolClass != null) return false;
		} else if (!schoolClass.equals(other.schoolClass)) return false;
		if (subject == null) {
			if (other.subject != null) return false;
		} else if (!subject.equals(other.subject)) return false;
		if (text == null) {
			if (other.text != null) return false;
		} else if (!text.equals(other.text)) return false;
		if (time == null) {
			if (other.time != null) return false;
		} else if (!time.equals(other.time)) return false;
		if (type == null) {
			if (other.type != null) return false;
		} else if (!type.equals(other.type)) return false;
		return true;
	}

	public boolean equalsIgnoreTime(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TableEntry other = (TableEntry) obj;
		if (replacementRoom == null) {
			if (other.replacementRoom != null) return false;
		} else if (!replacementRoom.equals(other.replacementRoom)) return false;
		if (replacementSubject == null) {
			if (other.replacementSubject != null) return false;
		} else if (!replacementSubject.equals(other.replacementSubject)) return false;
		if (room == null) {
			if (other.room != null) return false;
		} else if (!room.equals(other.room)) return false;
		if (schoolClass == null) {
			if (other.schoolClass != null) return false;
		} else if (!schoolClass.equals(other.schoolClass)) return false;
		if (subject == null) {
			if (other.subject != null) return false;
		} else if (!subject.equals(other.subject)) return false;
		if (text == null) {
			if (other.text != null) return false;
		} else if (!text.equals(other.text)) return false;
		if (type == null) {
			if (other.type != null) return false;
		} else if (!type.equals(other.type)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"schoolClass\":\"" + schoolClass + "\", \"time\":\"" + time + "\", \"type\":\"" + type + "\", \"replacementSubject\":\"" + replacementSubject + "\", \"subject\":\"" + subject + "\", \"replacementRoom\":\"" + replacementRoom + "\", \"room\":\"" + room + "\", \"text\":\"" + text + "\"}";
	}

	public static TableEntry fromElement(Element element) {
		Elements elements = element.children();
		TableEntry tableEntry = new TableEntry();
		tableEntry.setSchoolClass(elements.get(0).text());
		tableEntry.setTime(elements.get(1).text());
		tableEntry.setType(elements.get(2).text());
		tableEntry.setSubject(elements.get(3).text());
		tableEntry.setReplacementSubject(elements.get(4).text());
		tableEntry.setRoom(elements.get(5).text());
		tableEntry.setReplacementRoom(elements.get(6).text());
		tableEntry.setText(elements.get(7).text());

		return tableEntry;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}