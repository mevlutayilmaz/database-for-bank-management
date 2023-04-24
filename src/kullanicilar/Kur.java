package kullanicilar;

public class Kur {
	String paraBirimi1;
	String paraBirimi2;
	double kurfarki;
	public Kur(String paraBirimi1, String paraBirimi2, double kurfarki) {
		super();
		this.paraBirimi1 = paraBirimi1;
		this.paraBirimi2 = paraBirimi2;
		this.kurfarki = kurfarki;
	}
	
	public Kur() {}	
	
	public String getParaBirimi1() {
		return paraBirimi1;
	}
	public void setParaBirimi1(String paraBirimi1) {
		this.paraBirimi1 = paraBirimi1;
	}
	public String getParaBirimi2() {
		return paraBirimi2;
	}
	public void setParaBirimi2(String paraBirimi2) {
		this.paraBirimi2 = paraBirimi2;
	}
	public double getKurfarki() {
		return kurfarki;
	}
	public void setKurfarki(double kurfarki) {
		this.kurfarki = kurfarki;
	}
	
}
