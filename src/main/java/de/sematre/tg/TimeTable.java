package de.sematre.tg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TimeTable implements Serializable, Cloneable {

	private static final long serialVersionUID = -5995362166596672307L;
	private Date date = null;
	private ArrayList<Table> tables = null;

	public TimeTable(Date date, ArrayList<Table> tables) {
		this.date = date;
		this.tables = tables;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public TimeTable summarize() {
		ArrayList<Table> summarizedList = new ArrayList<>();
		for (Table table : tables) {
			summarizedList.add(table.summarize());
		}

		tables = summarizedList;
		return this;
	}

	public TimeTable split() {
		ArrayList<Table> splittedList = new ArrayList<>();
		for (Table table : tables) {
			splittedList.add(table.split());
		}

		tables = splittedList;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TimeTable other = (TimeTable) obj;
		if (date == null) {
			if (other.date != null) return false;
		} else if (!date.equals(other.date)) return false;
		if (tables == null) {
			if (other.tables != null) return false;
		} else if (!tables.equals(other.tables)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"date\":\"" + date.getTime() + "\", \"list\":" + tables.toString() + "}";
	}
}