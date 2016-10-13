package jsonBean;

import java.util.List;

public class JsonBean {
	public result result;

	public static class result {
		public data data;

		public data getData() {
			return data;
		}

		public void setData(data data) {
			this.data = data;
		}

		public static class data {
			public realtime realtime;
			public life life;
			private List<Weather> weather;

			public life getLife() {
				return life;
			}

			public void setLife(life life) {
				this.life = life;
			}

			public realtime getRealtime() {
				return realtime;
			}

			public void setRealtime(realtime realtime) {
				this.realtime = realtime;
			}

			public void setWeather(List<Weather> weather) {
				this.weather = weather;
			}

			public List<Weather> getWeather() {
				return this.weather;
			}

		}
	}

	public result getResult() {
		return result;
	}

	public void setResult(result result) {
		this.result = result;
	}
}
