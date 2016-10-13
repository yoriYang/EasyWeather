package jsonBean;

public class Weather {
	private String date;

	private Info info;

	private String week;

	private String nongli;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Info getInfo() {
		return this.info;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWeek() {
		return this.week;
	}

	public void setNongli(String nongli) {
		this.nongli = nongli;
	}

	public String getNongli() {
		return this.nongli;
	}
}
