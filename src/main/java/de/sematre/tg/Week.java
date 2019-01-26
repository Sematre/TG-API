package de.sematre.tg;

public enum Week {

	A("A"), B("B"), C("C"), D("D");

	private String letter = null;

	private Week(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
	}

	public static Week getWeek(String letter) {
		for (Week week : values()) {
			if (week.getLetter().equalsIgnoreCase(letter)) return week;
		}

		return null;
	}
}