package jsonBean;

public class realtime {
	public wind wind;
	public weather weather;
	public String city_name;
	public String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public weather getWeather() {
		return weather;
	}

	public void setWeather(weather weather) {
		this.weather = weather;
	}

	public wind getWind() {
		return wind;
	}

	public void setWind(wind wind) {
		this.wind = wind;
	}

	public static class wind {
		public String windspeed, direct, power, offset;

		public String getWindspeed() {
			return windspeed;
		}

		public void setWindspeed(String windspeed) {
			this.windspeed = windspeed;
		}

		public String getDirect() {
			return direct;
		}

		public void setDirect(String direct) {
			this.direct = direct;
		}

		public String getPower() {
			return power;
		}

		public void setPower(String power) {
			this.power = power;
		}

		public String getOffset() {
			return offset;
		}

		public void setOffset(String offset) {
			this.offset = offset;
		}

	}

	public static class weather {
		public String info;
		public int humidity, temperature;

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public int getHumidity() {
			return humidity;
		}

		public void setHumidity(int humidity) {
			this.humidity = humidity;
		}

		public int getTemperature() {
			return temperature;
		}

		public void setTemperature(int temperature) {
			this.temperature = temperature;
		}
	}

}
