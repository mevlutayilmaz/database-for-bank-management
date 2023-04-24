package kullanicilar;

public class Talep {
	protected int tid;
	private int mid;
	private String talep;
	private int hid;
	private double miktar;
	private int Vade;
	private String Para_Birimi;
	
	public Talep(int tid, int mid, String talep, int hid, double miktar, int vade,String parabirimi) {
		super();
		this.tid = tid;
		this.mid = mid;
		this.talep = talep;
		this.hid = hid;
		this.miktar = miktar;
		this.Vade = vade;
		this.Para_Birimi = parabirimi;
	}
	
	public Talep() {}
	
	
	public String getPara_Birimi() {
		return Para_Birimi;
	}
	public void setPara_Birimi(String para_Birimi) {
		Para_Birimi = para_Birimi;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getTalep() {
		return talep;
	}
	public void setTalep(String talep) {
		this.talep = talep;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public double getMiktar() {
		return miktar;
	}
	public void setMiktar(double miktar) {
		this.miktar = miktar;
	}
	public int getVade() {
		return Vade;
	}
	public void setVade(int vade) {
		Vade = vade;
	}
}
