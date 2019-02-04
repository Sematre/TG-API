package de.sematre.tg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.helper.StringUtil;

public class Table implements Serializable, Cloneable {

	private static final long serialVersionUID = -5501312470841456719L;
	private Date date = null;
	private Week week = null;
	private ArrayList<TableEntry> tableEntries = null;

	public Table() {}

	public Table(Date date, Week week, ArrayList<TableEntry> tableEntries) {
		this.date = date;
		this.week = week;
		this.tableEntries = tableEntries;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public ArrayList<TableEntry> getTableEntries() {
		return tableEntries;
	}

	public void setTableEntries(ArrayList<TableEntry> tableEntries) {
		this.tableEntries = tableEntries;
	}

	public Table summarize() {
		ArrayList<TableEntry> summarizedList = new ArrayList<>();
		ArrayList<Integer> alreadyInUse = new ArrayList<>();
		for (Integer firstIndex = 0; firstIndex < tableEntries.size(); firstIndex++) {
			TableEntry firstEntry = tableEntries.get(firstIndex);
			if (!StringUtil.isNumeric(firstEntry.getTime())) {
				summarizedList.add(firstEntry);
				continue;
			} else if (alreadyInUse.contains(firstIndex)) continue;

			Integer firstNumber = Integer.valueOf(firstEntry.getTime());
			Integer secondIndex = null;
			for (secondIndex = 0; secondIndex < tableEntries.size(); secondIndex++) {
				if (firstIndex == secondIndex) continue;
				if (alreadyInUse.contains(secondIndex)) continue;

				TableEntry secondEntry = tableEntries.get(secondIndex);
				if (!StringUtil.isNumeric(secondEntry.getTime())) continue;
				if (!firstEntry.equalsIgnoreTime(secondEntry)) continue;

				Integer secondNumber = Integer.valueOf(secondEntry.getTime());
				if (firstNumber >= secondNumber) continue;

				firstEntry.setTime(firstNumber + " - " + secondNumber);
				if (summarizedList.contains(firstEntry)) continue;

				summarizedList.add(firstEntry);
				alreadyInUse.add(firstIndex);
				alreadyInUse.add(secondIndex);
				break;
			}

			if (secondIndex >= tableEntries.size()) summarizedList.add(firstEntry);
		}

		tableEntries = summarizedList;
		return this;
	}

	public Table split() {
		try {
			ArrayList<TableEntry> splittedList = new ArrayList<>();
			for (TableEntry tableEntry : tableEntries) {
				if (!tableEntry.getSchoolClass().contains(", ")) {
					splittedList.add(tableEntry);
					continue;
				}

				for (String schoolClass : tableEntry.getSchoolClass().split(", ")) {
					splittedList.add(((TableEntry) tableEntry.clone()).setSchoolClass(schoolClass));
				}
			}

			tableEntries = splittedList;
			return this;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Table other = (Table) obj;
		if (date == null) {
			if (other.date != null) return false;
		} else if (!date.equals(other.date)) return false;
		if (tableEntries == null) {
			if (other.tableEntries != null) return false;
		} else if (!tableEntries.equals(other.tableEntries)) return false;
		if (week != other.week) return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"date\":\"" + date.getTime() + "\", \"week\":\"" + week + "\", \"tableEntries\":" + tableEntries + "}";
	}
}