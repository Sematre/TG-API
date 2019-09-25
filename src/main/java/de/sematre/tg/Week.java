package de.sematre.tg;

public enum Week {

	A("A"), B("B"), C("C"), D("D"), OTHER("");

	private String letter = null;

	private Week(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
	}

	public String getSimplifiedLetter() {
		return (this == Week.A || this == Week.C) ? A.getLetter() : B.getLetter();
	}

	public Week setLetter(String letter) {
		this.letter = letter;
		return this;
	}

	public static Week getWeek(String letter) {
		for (Week week : values()) {
			if (week.getLetter().equalsIgnoreCase(letter)) return week;
		}

		return null;
	}
}