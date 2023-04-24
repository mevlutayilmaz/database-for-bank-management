
package kullanicilar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metotlar.DBbaglanti;

public class Kullanici {
    protected int id;
    private String Ad_Soyad;
    private String Telefon;
    private String TC_No;
    private String Adres;
    private String Eposta;
    
    public DBbaglanti conn = new DBbaglanti();
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public Kullanici(int id, String Ad_Soyad, String Telefon, String TC_No, String Adres, String Eposta){
        this.id = id;
        this.Ad_Soyad = Ad_Soyad;
        this.Telefon = Telefon;
        this.TC_No = TC_No;
        this.Adres = Adres;
        this.Eposta = Eposta;
    }
    
    public Kullanici(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd_Soyad() {
        return Ad_Soyad;
    }

    public void setAd_Soyad(String Ad_Soyad) {
        this.Ad_Soyad = Ad_Soyad;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public String getTC_No() {
        return TC_No;
    }

    public void setTC_No(String TC_No) {
        this.TC_No = TC_No;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String Adres) {
        this.Adres = Adres;
    }

    public String getEposta() {
        return Eposta;
    }

    public void setEposta(String Eposta) {
        this.Eposta = Eposta;
    }
    
	public Hesap hesapBul(int hid) throws ClassNotFoundException {
		Hesap hesap = null;
		
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Hesaplar Where hid = '"+hid+"'");
            
            while(rs.next()){
                    hesap = new Hesap(rs.getInt("hid"),rs.getInt("mid"),rs.getString("Para_Birimi"),rs.getDouble("Bakiye"),rs.getDouble("Gelir"),rs.getDouble("Gider"));                  
            }
  
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return hesap;
		
	}
	
	public Borclar borcBul(int hid) throws ClassNotFoundException {
		Borclar borc = null;
		
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Borçlar Where hid = '"+hid+"'");
            
            while(rs.next()){
            	borc = new Borclar(rs.getInt("bid"),rs.getInt("hid"),rs.getDouble("Kredi_Borcu"),rs.getInt("Vade"),rs.getDate("Son_Ödeme_Tarihi"),rs.getDouble("Aylýk_Borç"));                  
            }
            
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return borc;
		
	}
	
	public ArrayList<Degerler> degerListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Degerler> list = new ArrayList<>();
        Degerler deger;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Degerler");
            
            while(rs.next()){
            	deger = new Degerler(rs.getDouble("Maas"),rs.getFloat("Faiz_Oraný"),rs.getFloat("Gecikme_Faiz_Oraný"),rs.getDate("Tarih"));
                list.add(deger);
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public ArrayList<Kur> kurListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Kur> list = new ArrayList<>();
        Kur kur;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Kur");
            
            while(rs.next()){
            	kur = new Kur(rs.getString("Para_Birimi"),rs.getString("Para_Birimi2"),rs.getFloat("Kur_Deðeri"));
                list.add(kur);
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
    
}
