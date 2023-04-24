package kullanicilar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Mudur extends Kullanici{
	Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
	
	public Mudur(){}
	
	public ArrayList<Islem> islemListesi(int top) throws SQLException, ClassNotFoundException{
        ArrayList<Islem> list = new ArrayList<>();
        Islem islem;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT TOP "+top+" * FROM Ýslemler ORDER BY Ýslem_No desc");
            
            while(rs.next()){
            	islem = new Islem(rs.getInt("Ýslem_No"),rs.getInt("Kaynak_ID"),rs.getInt("Hedef_ID"),rs.getString("Ýslem"),rs.getDouble("Tutar"),rs.getInt("Kaynak_Bakiye"),rs.getInt("Hedef_Bakiye"),rs.getString("Tarih"));
                list.add(islem); 
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public ArrayList<Musteri> musteriListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Musteri> list = new ArrayList<>();
        Musteri musteri;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Musteriler WHERE mid != 0");
            
            while(rs.next()){
                    musteri = new Musteri(rs.getInt("mid"),rs.getString("Ad_Soyad"),rs.getString("Telefon"),rs.getString("TC_No"),rs.getString("Adres"),rs.getString("Eposta"),rs.getInt("cid"));
                    list.add(musteri); 
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public ArrayList<Temsilci> temsilciListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Temsilci> list = new ArrayList<>();
        Temsilci temsilci;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Calisanlar WHERE cid != 0");
            
            while(rs.next()){
                    temsilci = new Temsilci(rs.getInt("cid"),rs.getString("Ad_Soyad"),rs.getString("Telefon"),rs.getString("TC_No"),rs.getString("Adres"),rs.getString("Eposta"));
                    list.add(temsilci); 
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public ArrayList<Hesap> hesapListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Hesap> list = new ArrayList<>();
        Hesap hesap;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Hesaplar");
            
            while(rs.next()){
            	hesap = new Hesap(rs.getInt("hid"),rs.getInt("mid"),rs.getString("Para_Birimi"),rs.getDouble("Bakiye"),rs.getDouble("Gelir"),rs.getDouble("Gider"));
                list.add(hesap);
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public ArrayList<Borclar> borcListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Borclar> list = new ArrayList<>();
        Borclar borc;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Borçlar");
            
            while(rs.next()){
            	borc = new Borclar(rs.getInt("bid"),rs.getInt("hid"),rs.getDouble("Kredi_Borcu"),rs.getInt("Vade"),rs.getDate("Son_Ödeme_Tarihi"),rs.getDouble("Aylýk_Borç"));
                list.add(borc);
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public boolean musteriEkle(String ad, String tel, String tc, String adres, String eposta) throws SQLException {
		int cid = temsilciAta();
		String query = "INSERT INTO Musteriler(Ad_Soyad,Telefon,TC_No,Adres,Eposta,cid) VALUES (?,?,?,?,?,?)";
		Connection con;
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
					
			ps.setString(1, ad);
			ps.setString(2, tel);
			ps.setString(3, tc);
			ps.setString(4, adres);
			ps.setString(5, eposta);
			ps.setInt(6, cid);
			ps.executeUpdate();
			key = true;
			
			con.close(); st.close(); ps.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		if(key)
			return true;
		else
			return false;
	}
	
	public int temsilciAta() throws SQLException {
		String query = "SELECT cid, COUNT(cid) as 'Musteri_Sayýsý' FROM Musteriler WHERE cid != 0 GROUP BY cid";
		Connection con;
		int musteri = 1000,cid = 0;
		
		try {
			con = conn.conDB();
			st = con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
	            if(rs.getInt("Musteri_Sayisi") < musteri) {
	            	musteri = rs.getInt("Musteri_Sayisi");
	            	cid = rs.getInt("cid");
	            } 
	        }
			
			con.close(); st.close(); rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cid;
	}
	
	public boolean degerGuncelle(double maas, float faizOraný, float gecikmeOraný, Date tarih) throws SQLException {
		String query = "UPDATE Degerler SET Maas='"+maas+"' ,Faiz_Oraný='"+faizOraný+"' ,Gecikme_Faiz_Oraný='"+gecikmeOraný+"', Tarih = '"+tarih+"'";
		Connection con;
		boolean key = false;
		
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.execute();
			key = true;
			
			con.close(); st.close(); ps.close();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		if(key)
			return true;
		else
			return false;
	}
	
	public boolean kurGuncelle(String parabirimi, String parabirimi2, float kur) throws SQLException {
		String query = "UPDATE Kur SET Kur_Deðeri='"+kur+"' WHERE Para_Birimi = '"+parabirimi+"' and Para_Birimi2 = '"+parabirimi2+"'";
		Connection con;
		boolean key = false;
		
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.execute();
			key = true;
			
			con.close(); st.close(); ps.close();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		if(key)
			return true;
		else
			return false;
	}
	
	public boolean kurEkle(String para, String para2, float kur) throws SQLException {
		String query = "INSERT INTO Kur(Para_Birimi,Para_Birimi2,Kur_Deðeri) VALUES (?,?,?)";
		Connection con;
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
					
			ps.setString(1, para);
			ps.setString(2, para2);
			ps.setFloat(3, kur);
			
			ps.executeUpdate();
			key = true;
			
			con.close(); st.close(); ps.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		if(key)
			return true;
		else
			return false;
	}
	
}
