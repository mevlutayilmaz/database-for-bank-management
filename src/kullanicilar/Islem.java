package kullanicilar;

public class Islem {
	protected int id;
	private int Kaynak_ID;
	private int Hedef_ID;
    private String islem;
    private double Tutar;
    private double Kaynak_Bakite;
    private double Hedef_Bakiye;
    private String Tarih;
    
	public Islem(int id, int kaynak_ID, int hedef_ID, String islem, double tutar, double kaynak_Bakite,double hedef_Bakiye, String tarih) {
		this.id = id;
		Kaynak_ID = kaynak_ID;
		Hedef_ID = hedef_ID;
		this.islem = islem;
		Tutar = tutar;
		Kaynak_Bakite = kaynak_Bakite;
		Hedef_Bakiye = hedef_Bakiye;
		Tarih = tarih;
	}
	
	public Islem() {}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKaynak_ID() {
		return Kaynak_ID;
	}
	public void setKaynak_ID(int kaynak_ID) {
		Kaynak_ID = kaynak_ID;
	}
	public int getHedef_ID() {
		return Hedef_ID;
	}
	public void setHedef_ID(int hedef_ID) {
		Hedef_ID = hedef_ID;
	}
	public String getIslem() {
		return islem;
	}
	public void setIslem(String islem) {
		this.islem = islem;
	}
	public double getTutar() {
		return Tutar;
	}
	public void setTutar(double tutar) {
		Tutar = tutar;
	}
	public double getKaynak_Bakite() {
		return Kaynak_Bakite;
	}
	public void setKaynak_Bakite(double kaynak_Bakite) {
		Kaynak_Bakite = kaynak_Bakite;
	}
	public double getHedef_Bakiye() {
		return Hedef_Bakiye;
	}
	public void setHedef_Bakiye(double hedef_Bakiye) {
		Hedef_Bakiye = hedef_Bakiye;
	}
	public String getTarih() {
		return Tarih;
	}
	public void setTarih(String tarih) {
		Tarih = tarih;
	}
    
}
