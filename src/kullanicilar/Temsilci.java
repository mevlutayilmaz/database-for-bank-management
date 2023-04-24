package kullanicilar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Temsilci extends Kullanici{	
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
	public Temsilci(int id, String Ad_Soyad, String Telefon, String TC_No, String Adres, String Eposta) {
		super(id, Ad_Soyad, Telefon, TC_No, Adres, Eposta);
	}
	
	public Temsilci() {}
	
	public ArrayList<Musteri> temsilListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Musteri> list = new ArrayList<>();
        Musteri mus;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Musteriler Where cid = '"+this.id+"'");
            
            while(rs.next()){
                    mus = new Musteri(rs.getInt("mid"),rs.getString("Ad_Soyad"),rs.getString("Telefon"),rs.getString("TC_No"),rs.getString("Adres"),rs.getString("Eposta"),rs.getInt("cid"));
                    list.add(mus);
            }
            con.close(); st.close(); rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;     
    }
	
	public ArrayList<Islem> temsilci_islemler() throws SQLException, ClassNotFoundException{
        ArrayList<Islem> list = new ArrayList<>();
        Islem islem;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT DISTINCT i.Ýslem_No,i.Kaynak_ID,i.Hedef_ID,i.Ýslem,i.Tutar,i.Kaynak_Bakiye,i.Hedef_Bakiye,i.Tarih FROM islemler i, musteriler m,hesaplar h \r\n"
            		+ "WHERE (i.Hedef_ID = h.hid or\r\n"
            		+ "	i.Kaynak_ID = h.hid) and\r\n"
            		+ "	h.mid = m.mid and\r\n"
            		+ "	m.cid = '"+this.id+"'");
            
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
	
	public ArrayList<Talep> talepListesi() throws SQLException, ClassNotFoundException{
        ArrayList<Talep> list = new ArrayList<>();
        Talep talep;
        try {   
        	Connection con = conn.conDB();       	
            st = con.createStatement();
            rs = st.executeQuery("SELECT t.tid,t.mid,t.Talep,t.hid,t.Miktar, t.Vade,t.Para_Birimi FROM Musteriler m, Talepler t WHERE t.mid = m.mid and m.cid = '"+this.id+"'");
            
            while(rs.next()){
                    talep = new Talep(rs.getInt("tid"),rs.getInt("mid"),rs.getString("Talep"),rs.getInt("hid"),rs.getDouble("Miktar"),rs.getInt("Vade"),rs.getString("Para_Birimi"));
                    list.add(talep);
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
            rs = st.executeQuery("SELECT DISTINCT h.hid,h.mid,h.Para_Birimi,h.Bakiye,h.Gelir,h.Gider \r\n"
            		+ "FROM Musteriler m, Hesaplar h \r\n"
            		+ "WHERE m.mid = h.mid and m.cid = '"+this.id+"'");
            
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
            rs = st.executeQuery("SELECT b.bid,b.hid,b.Kredi_Borcu,b.Vade,b.Son_Ödeme_Tarihi,b.Aylýk_Borç\r\n"
            		+ "FROM Hesaplar h, Borçlar b, Calisanlar c, Musteriler m\r\n"
            		+ "WHERE b.hid = h.hid AND h.mid = m.mid AND c.cid = m.cid AND c.cid = '"+this.id+"'");
            
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
			ps.setInt(6, this.id);
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
	
	public boolean musteriGuncelle(int mid,String ad, String tel, String tc, String adres, String eposta) throws SQLException {
		String query = "UPDATE Musteriler SET Ad_Soyad='"+ad+"' ,Telefon='"+tel +"' ,TC_No='"+tc+"' ,Adres='"+adres+"' ,Eposta='"+eposta+"'WHERE mid='"+mid+"'";
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
	
	public boolean musteriSil(int id) throws SQLException {
		String query = "DELETE FROM Musteriler WHERE mid = ?";
		Connection con;
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
					
			ps.setInt(1, id);
			
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
	
	public boolean hesapSil(int hid) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM Hesaplar WHERE hid = '"+hid+"'";
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
	
	public boolean islemSil(int hid) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM Ýslemler WHERE Kaynak_ID = '"+hid+"' OR Hedef_ID = '"+hid+"'";
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
	
	public boolean talepSil(int tid) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM Talepler WHERE tid = '"+tid+"'";
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
	
	public boolean hesapEkle(int mid, String parabirimi) throws SQLException {
		String query = "INSERT INTO Hesaplar(mid,Para_Birimi) VALUES (?,?)";
		Connection con;
		boolean key = false;
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
					
			ps.setInt(1, mid);
			ps.setString(2, parabirimi);
			
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
	
	public boolean krediOlustur(int id, double miktar, int vade) throws SQLException, ClassNotFoundException {
		Date tarih = degerListesi().get(0).getTarih();
		tarih.setDate(degerListesi().get(0).getTarih().getDate()+30);
		
		String query = "UPDATE Hesaplar SET Bakiye = '"+(hesapBul(id).getBakiye()+miktar)+"' Where hid = '"+id+"'\r\n"
				+"INSERT INTO Borçlar(hid,Kredi_Borcu,Vade,Son_Ödeme_Tarihi,Aylýk_Borç) VALUES (?,?,?,?,?)";
			
		Connection con;
		boolean key = false;
		
		try {
			con = conn.conDB();
			st = con.createStatement();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.setDouble(2, miktar);
			ps.setInt(3, vade);
			ps.setDate(4, (java.sql.Date) tarih);
			ps.setDouble(5, ((miktar/vade)+((miktar/vade)*degerListesi().get(0).getFaizOraný()/100)));
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
