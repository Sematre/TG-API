package de.sematre.tg;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.sematre.dsbmobile.DSBMobile;

public class TG implements Serializable, Cloneable {

	private static final long serialVersionUID = -6062100032432580842L;
	private DSBMobile dsbMobile = null;

	public TG() {}

	public TG(DSBMobile dsbMobile) {
		this.dsbMobile = dsbMobile;
	}

	public TG(String username, String password) {
		this(new DSBMobile(username, password));
	}

	public TimeTable getTimeTable(InputStream inputStream, String charset, String baseUri, Date lastUpdate) {
		try {
			Document document = Jsoup.parse(inputStream, charset, baseUri);

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

			return new TimeTable(lastUpdate, tables);
		} catch (IOException e) {
			throw new RuntimeException("Probably an Jsoup error", e);
		} catch (ParseException e) {
			throw new RuntimeException("Date cannot be parsed", e);
		}
	}

	public TimeTable getTimeTable() {
		Objects.requireNonNull(dsbMobile, "dsbMobile is null!");

		try {
			de.sematre.dsbmobile.DSBMobile.TimeTable timeTable = dsbMobile.getTimeTables().get(0);
			Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN).parse(timeTable.getDate());
			return getTimeTable(new URL(timeTable.getDetail()).openStream(), "WINDOWS-1252", timeTable.getDetail(), date);
		} catch (IOException e) {
			throw new RuntimeException("URL stream cannot be opened", e);
		} catch (ParseException e) {
			throw new RuntimeException("Date cannot be parsed", e);
		}
	}

	public DSBMobile getDsbMobile() {
		return dsbMobile;
	}
}