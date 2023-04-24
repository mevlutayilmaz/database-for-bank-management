package kullanicilar;

import java.util.Date;

public class Borclar {
	protected int bid;
	private int hid;
	private double Kredi_Borcu;
	private int Vade;
	private Date Son_Ödeme_Tarihi;
	private double Aylýk_Borç;
	
	public Borclar(int bid, int hid, double kredi_Borcu, int vade, Date son_Ödeme_Tarihi, double aylýk_Borç) {
		this.bid = bid;
		this.hid = hid;
		Kredi_Borcu = kredi_Borcu;
		Vade = vade;
		Son_Ödeme_Tarihi = son_Ödeme_Tarihi;
		Aylýk_Borç = aylýk_Borç;
	}
	
	public Borclar() {}
	

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public double getKredi_Borcu() {
		return Kredi_Borcu;
	}

	public void setKredi_Borcu(double kredi_Borcu) {
		Kredi_Borcu = kredi_Borcu;
	}

	public int getVade() {
		return Vade;
	}

	public void setVade(int vade) {
		Vade = vade;
	}

	public Date getSon_Ödeme_Tarihi() {
		return Son_Ödeme_Tarihi;
	}

	public void setSon_Ödeme_Tarihi(Date son_Ödeme_Tarihi) {
		Son_Ödeme_Tarihi = son_Ödeme_Tarihi;
	}

	public double getAylýk_Borç() {
		return Aylýk_Borç;
	}

	public void setAylýk_Borç(double aylýk_Borç) {
		Aylýk_Borç = aylýk_Borç;
	}
	
	
}
