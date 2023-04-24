package kullanicilar;

import java.util.Date;

public class Hesap {
	protected int hid;
    private int mid;
    private String Para_Birimi;
    private double Bakiye;
    private double Gelir;
    private double Gider;
    
	public Hesap(int hid, int mid, String para_Birimi, double bakiye, double gelir, double gider) {
		this.hid = hid;
		this.mid = mid;
		Para_Birimi = para_Birimi;
		Bakiye = bakiye;
		Gelir = gelir;
		Gider = gider;
	}
	
	public Hesap() {}


	public double getGelir() {
		return Gelir;
	}
	public void setGelir(double gelir) {
		Gelir = gelir;
	}
	public double getGider() {
		return Gider;
	}
	public void setGider(double gider) {
		Gider = gider;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getPara_Birimi() {
		return Para_Birimi;
	}
	public void setPara_Birimi(String para_Birimi) {
		Para_Birimi = para_Birimi;
	}
	public double getBakiye() {
		return Bakiye;
	}
	public void setBakiye(double bakiye) {
		Bakiye = bakiye;
	}

}
