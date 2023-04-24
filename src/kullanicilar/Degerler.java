package kullanicilar;

import java.util.Date;

public class Degerler {
	private double maas;
	private float faizOraný;
	private float gecikmeFaizOraný;
	private Date Tarih;
	
	public Degerler(double maas, float faizOraný, float gecikmeFaizOraný, Date tarih) {
		this.maas = maas;
		this.faizOraný = faizOraný;
		this.gecikmeFaizOraný = gecikmeFaizOraný;
		Tarih = tarih;
	}
	
	public Degerler() {}
	

	public Date getTarih() {
		return Tarih;
	}

	public void setTarih(Date tarih) {
		Tarih = tarih;
	}

	public double getMaas() {
		return maas;
	}

	public void setMaas(double maas) {
		this.maas = maas;
	}

	public float getFaizOraný() {
		return faizOraný;
	}

	public void setFaizOraný(float faizOraný) {
		this.faizOraný = faizOraný;
	}

	public float getGecikmeFaizOraný() {
		return gecikmeFaizOraný;
	}

	public void setGecikmeFaizOraný(float gecikmeFaizOraný) {
		this.gecikmeFaizOraný = gecikmeFaizOraný;
	}
}
