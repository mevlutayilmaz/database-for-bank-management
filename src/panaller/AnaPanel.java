package panaller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kullanicilar.Kullanici;
import kullanicilar.Mudur;
import kullanicilar.Musteri;
import kullanicilar.Temsilci;
import metotlar.DBbaglanti;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnaPanel extends JFrame {
	Kullanici kul = new Kullanici();

	private JPanel contentPane;
	private JTextField field_mudurkulad;
	private JTextField field_musid;
	private JTextField field_mustc;
	private JTextField field_temid;
	private JTextField field_temtc;
	private JPasswordField field_mudursifre;
	private DBbaglanti conn = new DBbaglanti();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaPanel frame = new AnaPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AnaPanel() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("KO\u00DC Bank'a Ho\u015Fgeldiniz...");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblNewLabel_2 = new JLabel(kul.degerListesi().get(0).getTarih().toString());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 960, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(348)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(347, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(844, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panel_musteri = new JPanel();
		tabbedPane.addTab("Musteri Girisi", null, panel_musteri, null);
		panel_musteri.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Musteri ID:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(28, 46, 109, 46);
		panel_musteri.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("TC_No:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(28, 116, 109, 46);
		panel_musteri.add(lblNewLabel_1_1_1);
		
		field_musid = new JTextField();
		field_musid.setColumns(10);
		field_musid.setBounds(147, 48, 585, 47);
		panel_musteri.add(field_musid);
		
		field_mustc = new JTextField();
		field_mustc.setColumns(10);
		field_mustc.setBounds(147, 115, 585, 47);
		panel_musteri.add(field_mustc);
		
		JButton button_musgiris = new JButton("Giris");
		button_musgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_musid.getText().length() == 0 || field_mustc.getText().length() == 0)
		            JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
		        else{
		            try {
		                Connection c = conn.conDB();
		                Statement st = c.createStatement();
		                ResultSet rs = st.executeQuery("SELECT * FROM Musteriler");
		                boolean kontrol = false;
		                while(rs.next()){
		                    if(field_musid.getText().equals(rs.getString("mid")) && field_mustc.getText().equals(rs.getString("mid"))){
		                        Musteri musteri = new Musteri();
		                        musteri.setId(rs.getInt("mid"));
		                        musteri.setAd_Soyad(rs.getString("Ad_Soyad"));
		                        musteri.setTelefon(rs.getString("Telefon"));
		                        musteri.setTC_No(rs.getString("TC_No"));
		                        musteri.setAdres(rs.getString("Adres"));
		                        musteri.setEposta(rs.getString("Eposta"));
		                        musteri.setCid(rs.getInt("cid"));
		                        
		                        MusteriPaneli muspanel = new MusteriPaneli(musteri);
		                        muspanel.setVisible(true);
		                        dispose();
		                        kontrol = true;
		                    }
		                }
		                if(!kontrol)
			            	JOptionPane.showMessageDialog(null, "Yanlýþ giriþ. Lütfen tekrar giriþ yapýnýz..!");
		                c.close(); st.close(); rs.close();
		            } catch (ClassNotFoundException ex) {
		                Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
		            } catch (SQLException ex) {
		                Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
			}
		});
		button_musgiris.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_musgiris.setBounds(513, 215, 219, 64);
		panel_musteri.add(button_musgiris);
		
		JPanel panel_temsilci = new JPanel();
		tabbedPane.addTab("Musteri Temsilcisi", null, panel_temsilci, null);
		panel_temsilci.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Temsilci ID:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(24, 44, 109, 46);
		panel_temsilci.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("TC_No:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(24, 114, 109, 46);
		panel_temsilci.add(lblNewLabel_1_1_2);
		
		field_temid = new JTextField();
		field_temid.setColumns(10);
		field_temid.setBounds(143, 46, 585, 47);
		panel_temsilci.add(field_temid);
		
		field_temtc = new JTextField();
		field_temtc.setColumns(10);
		field_temtc.setBounds(143, 113, 585, 47);
		panel_temsilci.add(field_temtc);
		
		JButton button_temgiris = new JButton("Giris");
		button_temgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_temid.getText().length() == 0 || field_temtc.getText().length() == 0)
		            JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
		        else{		  	
		            try {
		                Connection c = conn.conDB();
		                Statement st = c.createStatement();
		                ResultSet rs = st.executeQuery("SELECT * FROM Calisanlar");
		                boolean kontrol = false;
		                while(rs.next()){
		                    if(field_temid.getText().equals(rs.getString("cid")) && field_temtc.getText().equals(rs.getString("cid"))){
		                        Temsilci temsilci = new Temsilci();
		                        temsilci.setId(rs.getInt("cid"));
		                        temsilci.setAd_Soyad(rs.getString("Ad_Soyad"));
		                        temsilci.setTelefon(rs.getString("Telefon"));
		                        temsilci.setTC_No(rs.getString("TC_No"));
		                        temsilci.setAdres(rs.getString("Adres"));
		                        temsilci.setEposta(rs.getString("Eposta"));
		                        
		                        TemsilciPaneli tempanel = new TemsilciPaneli(temsilci);
		                        tempanel.setVisible(true);
		                        dispose();
		                        kontrol = true;
		                    }
		                }
		                if(!kontrol)
			            	JOptionPane.showMessageDialog(null, "Yanlýþ giriþ. Lütfen tekrar giriþ yapýnýz..!");
		                c.close(); rs.close();
		            } catch (ClassNotFoundException ex) {
		                Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
		            } catch (SQLException ex) {
		                Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }   	
			}
		});
		button_temgiris.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_temgiris.setBounds(509, 213, 219, 64);
		panel_temsilci.add(button_temgiris);
		
		JPanel panel_mudur = new JPanel();
		tabbedPane.addTab("Banka Muduru", null, panel_mudur, null);
		panel_mudur.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(21, 41, 109, 46);
		panel_mudur.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sifre:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(21, 111, 109, 46);
		panel_mudur.add(lblNewLabel_1_1);
		
		field_mudurkulad = new JTextField();
		field_mudurkulad.setBounds(140, 43, 585, 47);
		panel_mudur.add(field_mudurkulad);
		field_mudurkulad.setColumns(10);
		
		JButton button_mudurgiris = new JButton("Giris");
		button_mudurgiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_mudurkulad.getText().length() == 0 || field_mudursifre.getText().length() == 0)
		            JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
		        else{
		            if(field_mudurkulad.getText().equals("koubank123") && field_mudursifre.getText().equals("1234")){
		            	Mudur mudur = new Mudur();
		                MudurPaneli mudurpanel = null;
						try {
							mudurpanel = new MudurPaneli(mudur);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
		                mudurpanel.setVisible(true);
		                dispose();
		            }
		            else
		            	JOptionPane.showMessageDialog(null, "Yanlýþ giriþ. Lütfen tekrar giriþ yapýnýz..!");
		        } 
			}
		});
		button_mudurgiris.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_mudurgiris.setBounds(506, 210, 219, 64);
		panel_mudur.add(button_mudurgiris);
		
		field_mudursifre = new JPasswordField();
		field_mudursifre.setBounds(140, 111, 585, 47);
		panel_mudur.add(field_mudursifre);
		contentPane.setLayout(gl_contentPane);
	}
}
