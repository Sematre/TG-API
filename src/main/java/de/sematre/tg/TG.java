package de.sematre.tg;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.sematre.dsbmobile.DSBMobile;

public class TG implements Serializable, Cloneable {

	private static final long serialVersionUID = -6062100032432580842L;
	private DSBMobile dsbMobile = null;

	public TG(DSBMobile dsbMobile) {
		this.dsbMobile = dsbMobile;
	}

	public TG(String username, String password) {
		this(new DSBMobile(username, password));
	}

	public TimeTable getTimeTable() {
		try {
			de.sematre.dsbmobile.TimeTable dsbTable = dsbMobile.getTimeTables().get(0);
			Document document = Jsoup.connect(dsbTable.getUrl()).get();

			ArrayList<Table> tables = new ArrayList<>();
			Elements elements = document.select(".mon_title,.mon_list");
			for (Integer index = 0; index < elements.size(); index += 2) {
				String[] dateAndWeek = elements.get(index).text().split(", Woche ");
				Date date = new SimpleDateFormat("d.M.yyyy EEEE", Locale.GERMAN).parse(dateAndWeek[0]);
				Week week = Week.getWeek(dateAndWeek[1]);
				if (week == null) week = Week.OTHER.setLetter(dateAndWeek[1]);

				ArrayList<TableEntry> tableEntries = new ArrayList<>();
				for (Element element : elements.get(index + 1).select(".list.odd,.list.even")) {
					tableEntries.add(TableEntry.fromElement(element));
				}

				tables.add(new Table(date, week, tableEntries));
			}

			Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN).parse(dsbTable.getDate());
			return new TimeTable(date, tables);
		} catch (IOException e) {
			throw new RuntimeException("Probably an Jsoup error", e);
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse the date", e);
		}
	}

	public DSBMobile getDsbMobile() {
		return dsbMobile;
	}
}