package de.sematre.tg;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class News implements Serializable, Cloneable {

	private static final long serialVersionUID = -1189342983314281518L;

	private UUID uuid = null;
	private Date date = null;

	private String title = null;
	private String text = null;

	public News(UUID uuid, Date date, String title, String text) {
		this.uuid = uuid;
		this.date = date;
		this.title = title;
		this.text = text;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		News other = (News) obj;
		if (date == null) {
			if (other.date != null) return false;
		} else if (!date.equals(other.date)) return false;
		if (text == null) {
			if (other.text != null) return false;
		} else if (!text.equals(other.text)) return false;
		if (title == null) {
			if (other.title != null) return false;
		} else if (!title.equals(other.title)) return false;
		if (uuid == null) {
			if (other.uuid != null) return false;
		} else if (!uuid.equals(other.uuid)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"uuid\": \"" + uuid + "\", \"date\": \"" + date + "\", \"title\": \"" + title + "\", \"text\": \"" + text + "\"}";
	}
}