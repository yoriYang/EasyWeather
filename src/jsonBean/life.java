package jsonBean;

import java.util.ArrayList;

public class life {
	public info info;

	public info getInfo() {
		return info;
	}

	public void setInfo(info info) {
		this.info = info;
	}

	public static class info {
		public ArrayList<String> kongtiao;
		public ArrayList<String> yundong;
		public ArrayList<String> ziwaixian;
		public ArrayList<String> ganmao;
		public ArrayList<String> xiche;
		public ArrayList<String> wuran;
		public ArrayList<String> chuanyi;
		
		public ArrayList<String> getChuanyi() {
			return chuanyi;
		}

		public void setChuanyi(ArrayList<String> chuanyi) {
			this.chuanyi = chuanyi;
		}

		public ArrayList<String> getKongtiao() {
			return kongtiao;
		}

		public void setKongtiao(ArrayList<String> kongtiao) {
			this.kongtiao = kongtiao;
		}

		public ArrayList<String> getYundong() {
			return yundong;
		}

		public void setYundong(ArrayList<String> yundong) {
			this.yundong = yundong;
		}

		public ArrayList<String> getZiwaixian() {
			return ziwaixian;
		}

		public void setZiwaixian(ArrayList<String> ziwaixian) {
			this.ziwaixian = ziwaixian;
		}

		public ArrayList<String> getGanmao() {
			return ganmao;
		}

		public void setGanmao(ArrayList<String> ganmao) {
			this.ganmao = ganmao;
		}

		public ArrayList<String> getXiche() {
			return xiche;
		}

		public void setXiche(ArrayList<String> xiche) {
			this.xiche = xiche;
		}

		public ArrayList<String> getWuran() {
			return wuran;
		}

		public void setWuran(ArrayList<String> wuran) {
			this.wuran = wuran;
		}

	}
}
