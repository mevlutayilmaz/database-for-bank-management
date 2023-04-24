package panaller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import kullanicilar.Islem;
import kullanicilar.Mudur;
import kullanicilar.Musteri;
import kullanicilar.Temsilci;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class MudurPaneli extends JFrame {
	static Mudur mudur = new Mudur();
	static Musteri musteri = new Musteri();
	
	Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
	
	private JPanel contentPane;
	private JTable table_banka;
	private JTable table_degerler;
	private JTextField field_xislem;
	private JTable table_islemler;
	private JTable table_musteriler;
	private JTextField field_ad;
	private JTextField field_telefon;
	private JTextField field_tc;
	private JTextField field_adres;
	private JTextField field_eposta;
	
	private DefaultTableModel tableModel_musteriler = null;
	private DefaultTableModel tableModel_islemler = null;
	private DefaultTableModel tableModel_banka = null;
	private DefaultTableModel tableModel_degerler = null;
	private DefaultTableModel tableModel_kur = null;
	private Object[] data = null;
	private Object[] data2 = null;
	private Object[] data3 = null;
	private Object[] data4 = null;
	private Object[] data5 = null;
	private JTable table_kur;
	private JTextField field_para;
	private JTextField field_para2;
	private JTextField field_kur;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MudurPaneli frame = new MudurPaneli(mudur);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MudurPaneli(Mudur mudur) throws SQLException, ClassNotFoundException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tableModel_musteriler = new DefaultTableModel();
        Object[] colName = new Object[7];
        colName[0] = "mid";
        colName[1] = "Ad_Soyad";
        colName[2] = "Telefon";
        colName[3] = "TC_No";
        colName[4] = "Adres";
        colName[5] = "Eposta";
        colName[6] = "cid";
        tableModel_musteriler.setColumnIdentifiers(colName);
        
        data = new Object[7];    
        
        for(int i = 0; i < mudur.musteriListesi().size(); i++){
            data[0] = mudur.musteriListesi().get(i).getId();
            data[1] = mudur.musteriListesi().get(i).getAd_Soyad();
            data[2] = mudur.musteriListesi().get(i).getTelefon();
            data[3] = mudur.musteriListesi().get(i).getTC_No();
            data[4] = mudur.musteriListesi().get(i).getAdres();
            data[5] = mudur.musteriListesi().get(i).getEposta();
            data[6] = mudur.musteriListesi().get(i).getCid();
            tableModel_musteriler.addRow(data);         
        }
        
        tableModel_banka = new DefaultTableModel();
        Object[] colName3 = new Object[4];
        colName3[0] = "Gelir";
        colName3[1] = "Gider";
        colName3[2] = "Kar";
        colName3[3] = "Toplam_Bakiye";
        tableModel_banka.setColumnIdentifiers(colName3);
        
        data3 = new Object[4];    

        data3[0] = mudur.hesapBul(0).getGelir();
        data3[1] = mudur.hesapBul(0).getGider();
        data3[2] = mudur.hesapBul(0).getGelir()-mudur.hesapBul(0).getGider();
        data3[3] = mudur.hesapBul(0).getBakiye();
        tableModel_banka.addRow(data3);         
        
        tableModel_degerler = new DefaultTableModel();
        Object[] colName4 = new Object[3];
        colName4[0] = "Maas";
        colName4[1] = "Faiz_Oraný";
        colName4[2] = "Gecikme_Faiz_Oraný";
        tableModel_degerler.setColumnIdentifiers(colName4);
        
        data4 = new Object[3];

        data4[0] = mudur.degerListesi().get(0).getMaas();
        data4[1] = mudur.degerListesi().get(0).getFaizOraný();
        data4[2] = mudur.degerListesi().get(0).getGecikmeFaizOraný();
        tableModel_degerler.addRow(data4);
        
        tableModel_kur = new DefaultTableModel();
        Object[] colName5 = new Object[3];
        colName5[0] = "Para_Birimi";
        colName5[1] = "Para_Birimi2";
        colName5[2] = "Kur_Deðeri";
        tableModel_kur.setColumnIdentifiers(colName5);
        
        data5 = new Object[3];

        for(int i = 0; i < mudur.kurListesi().size(); i++) {
        	data5[0] = mudur.kurListesi().get(i).getParaBirimi1();
        	data5[1] = mudur.kurListesi().get(i).getParaBirimi2();
        	data5[2] = mudur.kurListesi().get(i).getKurfarki();
        	tableModel_kur.addRow(data5); 
        }
 
        tableModel_islemler = new DefaultTableModel();
        Object[] colName2 = new Object[8];
        colName2[0] = "Ýslem_No";
        colName2[1] = "Kaynak_ID";
        colName2[2] = "Hede_ID";
        colName2[3] = "Ýslem";
        colName2[4] = "Tutar";
        colName2[5] = "Kaynak_Bakiye";
        colName2[6] = "Hedef_Bakiye";
        colName2[7] = "Tarih";
        tableModel_islemler.setColumnIdentifiers(colName2);
		
		JLabel lblHosgeldinizSayinKobank = new JLabel("Hoþgeldiniz, Sayýn ko\u00FCbank123");
		lblHosgeldinizSayinKobank.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("Cikis Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaPanel panel = null;
				try {
					panel = new AnaPanel();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				panel.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel label_tarih = new JLabel(mudur.degerListesi().get(0).getTarih().toString());
		label_tarih.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblHosgeldinizSayinKobank, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 562, Short.MAX_VALUE)
							.addComponent(label_tarih, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHosgeldinizSayinKobank, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_tarih, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Genel Durum", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		JLabel lblNewLabel_2 = new JLabel("Para_Birimi:");
		
		field_para = new JTextField();
		field_para.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Para_Birimi2:");
		
		field_para2 = new JTextField();
		field_para2.setColumns(10);
		
		JLabel lblNewLabel_2_2 = new JLabel("Kur_De\u011Feri:");
		
		field_kur = new JTextField();
		field_kur.setColumns(10);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_para.getText().length() == 0 || field_para2.getText().length() == 0 || field_kur.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else{
					try {
						float kur = Float.parseFloat(field_kur.getText());
						boolean kontrol = mudur.kurEkle(field_para.getText(), field_para2.getText(), kur);
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
							field_para.setText(null);
							field_para2.setText(null);
							field_kur.setText(null);
							updateTableModel_kur(mudur);
						}
					} catch (SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
					.addGap(15)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addComponent(field_para, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addComponent(field_para2, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addComponent(field_kur, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEkle, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addGap(68))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(105)
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(74)
							.addComponent(lblNewLabel_2)
							.addGap(1)
							.addComponent(field_para, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_1)
							.addGap(1)
							.addComponent(field_para2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2)
							.addGap(1)
							.addComponent(field_kur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEkle)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table_kur = new JTable(tableModel_kur);
		scrollPane_4.setViewportView(table_kur);
		
		table_kur.getModel().addTableModelListener((TableModelListener) new TableModelListener() {
			 public void tableChanged(TableModelEvent e) {
				 if(e.getType() == TableModelEvent.UPDATE) {
					 String parabirimi = table_kur.getValueAt(table_kur.getSelectedRow(), 0).toString();
					 String parabirimi2 = table_kur.getValueAt(table_kur.getSelectedRow(), 1).toString();
					 Float kur = Float.parseFloat(table_kur.getValueAt(table_kur.getSelectedRow(), 2).toString());			 
					 
					 try {
						boolean kontrol = mudur.kurGuncelle(parabirimi, parabirimi2, kur);
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 }
			 }
		 });
		
		table_degerler = new JTable(tableModel_degerler);
		scrollPane_1.setViewportView(table_degerler);
		
		table_degerler.getModel().addTableModelListener((TableModelListener) new TableModelListener() {
			 public void tableChanged(TableModelEvent e) {
				 if(e.getType() == TableModelEvent.UPDATE) {
					 Double maas = Double.parseDouble(table_degerler.getValueAt(table_degerler.getSelectedRow(), 0).toString());
					 Float faizOraný = Float.parseFloat(table_degerler.getValueAt(table_degerler.getSelectedRow(), 1).toString());
					 Float gecikmeOraný = Float.parseFloat(table_degerler.getValueAt(table_degerler.getSelectedRow(), 2).toString());			 
					 
					 try {
						boolean kontrol = mudur.degerGuncelle(maas, faizOraný, gecikmeOraný,mudur.degerListesi().get(0).getTarih());
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
						}
					} catch (SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				 }
			 }
		 });
		
		table_banka = new JTable(tableModel_banka);
		scrollPane.setViewportView(table_banka);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("M\u00FC\u015Fteriler", null, panel_1, null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Ad_Soyad");
		
		field_ad = new JTextField();
		field_ad.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Telefon");
		
		field_telefon = new JTextField();
		field_telefon.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("TC_No");
		
		field_tc = new JTextField();
		field_tc.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Adres");
		
		field_adres = new JTextField();
		field_adres.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Eposta");
		
		field_eposta = new JTextField();
		field_eposta.setColumns(10);
		
		JButton button_ekle = new JButton("Ekle");
		button_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_ad.getText().length() == 0 || field_telefon.getText().length() == 0 || field_tc.getText().length() == 0 || field_adres.getText().length() == 0 || field_eposta.getText().length() == 0 )
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else{
					try {
						boolean kontrol = mudur.musteriEkle(field_ad.getText(), field_telefon.getText(), field_tc.getText(), field_adres.getText(), field_eposta.getText());
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
							field_ad.setText(null);
							field_telefon.setText(null);
							field_tc.setText(null);
							field_adres.setText(null);
							field_eposta.setText(null);
							updateTableModel_musteriler(mudur);
						}
					} catch (SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_adres, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_tc, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_ad, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_telefon, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(button_ekle)
							.addComponent(field_eposta, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(2)
							.addComponent(field_ad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblNewLabel_1_1)
							.addGap(2)
							.addComponent(field_telefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblNewLabel_1_2)
							.addGap(2)
							.addComponent(field_tc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblNewLabel_1_3)
							.addGap(2)
							.addComponent(field_adres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblNewLabel_1_4)
							.addGap(2)
							.addComponent(field_eposta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(button_ekle)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		table_musteriler = new JTable(tableModel_musteriler);
		scrollPane_3.setViewportView(table_musteriler);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u0130\u015Flemler", null, panel_2, null);
		
		field_xislem = new JTextField();
		field_xislem.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Son X i\u015Flemi g\u00F6r\u00FCnt\u00FCle X:");
		
		JButton btnGrntle = new JButton("G\u00F6r\u00FCnt\u00FCle");
		btnGrntle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_xislem.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir müþteri seçiniz..!");
				else {
					DefaultTableModel clearModel = (DefaultTableModel) table_islemler.getModel();
					clearModel.setRowCount(0);
					int top = Integer.parseInt(field_xislem.getText());
					
					data2 = new Object[8];
					
					try {
						int deadlock = 0;
						for(int i = 0; i < mudur.islemListesi(top).size(); i++){
							data2[0] = mudur.islemListesi(top).get(i).getId();
							data2[1] = mudur.islemListesi(top).get(i).getKaynak_ID();
							data2[2] = mudur.islemListesi(top).get(i).getHedef_ID();
							data2[3] = mudur.islemListesi(top).get(i).getIslem();
							data2[4] = mudur.islemListesi(top).get(i).getTutar();
							data2[5] = mudur.islemListesi(top).get(i).getKaynak_Bakite();
							data2[6] = mudur.islemListesi(top).get(i).getHedef_Bakiye();
							data2[7] = mudur.islemListesi(top).get(i).getTarih();
							tableModel_islemler.addRow(data2);						
							
							if(mudur.islemListesi(top).get(i).getIslem().equals("Para Transferi")) {
								int kaynak = mudur.islemListesi(top).get(i).getKaynak_ID();
								int hedef = mudur.islemListesi(top).get(i).getHedef_ID();
								int id = mudur.islemListesi(top).get(i).getId();
								String tarih = mudur.islemListesi(top).get(i).getTarih(); 
								
								for(int j = 0; j < mudur.islemListesi(top).size(); j++) {
									if((hedef == mudur.islemListesi(top).get(j).getHedef_ID() || hedef == mudur.islemListesi(top).get(j).getKaynak_ID()) && mudur.islemListesi(top).get(j).getTarih().equals(tarih) && id != mudur.islemListesi(top).get(j).getId()) {
										String tarih2 = mudur.islemListesi(top).get(j).getTarih();
										Thread t1 = new Thread() {
											public void run() {
										        synchronized(tarih) {
										        	
										        	System.out.println("Locked "+hedef);
										          	try {
										          		Thread.sleep(100);
										          	}
										          	catch(Exception e) {}
										          	System.out.println("Waiting for lock "+kaynak);
										          
										          	synchronized(tarih2) {
										          		System.out.println("Locked "+kaynak+" & "+hedef);
										          	}
										        }
											}
										};
										t1.start();
				
										deadlock++;
										System.out.println("("+kaynak+"-"+hedef+") ");
										System.out.println("-------------------------");
										break;
									}		
								}
							}
						}
						 Thread.sleep(100);
						System.out.println("Deadlock Sayýsý: "+deadlock);
					} catch (ClassNotFoundException | SQLException | InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JButton button_ileriata = new JButton("Sistemi Bir Ay \u0130leri Ata");
		button_ileriata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, "Sistem simüle ediliyor. Biraz uzun sürebilir. Anlayýþýnýz için teþekkür ederiz.");
					Date tarih = mudur.degerListesi().get(0).getTarih();
					String query = "UPDATE Hesaplar SET Gelir = '0', Gider = '0'";
					Connection con;			
					
					con = mudur.conn.conDB();
					st = con.createStatement();
					ps = con.prepareStatement(query);
					ps.execute();
						
					con.close(); st.close(); ps.close();
			
					for(int k = 0; k < mudur.borcListesi().size(); k++) {
						Long fark = (mudur.borcListesi().get(k).getSon_Ödeme_Tarihi().getTime()-musteri.degerListesi().get(0).getTarih().getTime())/(1000*60*60*24);
						if(fark < 0) {
							musteri.islemEkle(mudur.borcListesi().get(k).getHid(), 2, mudur.borcListesi().get(k).getAylýk_Borç(), 2);
							musteri.islemEkle(mudur.borcListesi().get(k).getHid(), 1, (mudur.borcListesi().get(k).getAylýk_Borç()-mudur.borcListesi().get(k).getKredi_Borcu()/mudur.borcListesi().get(k).getVade()), 7);
							musteri.hesapGuncelle(mudur.borcListesi().get(k).getHid(), mudur.borcListesi().get(k).getAylýk_Borç(), 3);
							if(mudur.borcListesi().get(k).getVade() == 0) {
								musteri.borcSil(mudur.borcListesi().get(k).getHid());
							}
						}	
					}
					
					for(int j = 1; j < 31; j++) {
						if(tarih.getDate() == 1) {
							query = "UPDATE Hesaplar SET Bakiye -= '"+(mudur.temsilciListesi().size()*mudur.degerListesi().get(0).getMaas())+"', Gider += '"+(mudur.temsilciListesi().size()*mudur.degerListesi().get(0).getMaas())+"' WHERE hid = '0'";			
							
							con = mudur.conn.conDB();
							st = con.createStatement();
							ps = con.prepareStatement(query);
							ps.execute();
								
							con.close(); st.close(); ps.close();
							
							musteri.islemEkle(0,0,(mudur.temsilciListesi().size()*mudur.degerListesi().get(0).getMaas()),6);	
						}			
						
						for(int i = 1; i < mudur.borcListesi().size(); i++) {
							if(mudur.borcListesi().get(i).getSon_Ödeme_Tarihi().getDate() == tarih.getDate() && mudur.borcListesi().get(i).getSon_Ödeme_Tarihi().getMonth() == tarih.getMonth()) {
								musteri.islemEkle(mudur.borcListesi().get(i).getHid(), 1, mudur.borcListesi().get(i).getAylýk_Borç(), 2);
								musteri.islemEkle(mudur.borcListesi().get(i).getHid(), 1, (mudur.borcListesi().get(i).getAylýk_Borç()-mudur.borcListesi().get(i).getKredi_Borcu()/mudur.borcListesi().get(i).getVade()), 7);
								musteri.hesapGuncelle(mudur.borcListesi().get(i).getHid(), mudur.borcListesi().get(i).getAylýk_Borç(), 2);
								
								if(mudur.borcListesi().get(i).getVade() == 0) {
									musteri.borcSil(mudur.borcListesi().get(i).getHid());
								}
							}	
						}

						tarih.setDate(mudur.degerListesi().get(0).getTarih().getDate()+1);
						mudur.degerGuncelle(mudur.degerListesi().get(0).getMaas(), mudur.degerListesi().get(0).getFaizOraný(), mudur.degerListesi().get(0).getGecikmeFaizOraný(), tarih);						
					}

					label_tarih.setText(tarih.toString());
					JOptionPane.showMessageDialog(null, "Sistem bir ay ileri atandý");
					updateTableModel_banka(mudur);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 1039, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(field_xislem, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnGrntle, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
							.addComponent(button_ileriata, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 548, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(5)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(field_xislem, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGrntle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_ileriata, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		table_islemler = new JTable(tableModel_islemler);
		scrollPane_2.setViewportView(table_islemler);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void updateTableModel_musteriler(Mudur mudur) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_musteriler.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[7];
		
		for(int i = 0; i < mudur.musteriListesi().size(); i++){			
            data[0] = mudur.musteriListesi().get(i).getId();
            data[1] = mudur.musteriListesi().get(i).getAd_Soyad();
            data[2] = mudur.musteriListesi().get(i).getTelefon();
            data[3] = mudur.musteriListesi().get(i).getTC_No();
            data[4] = mudur.musteriListesi().get(i).getAdres();
            data[5] = mudur.musteriListesi().get(i).getEposta();
            data[6] = mudur.musteriListesi().get(i).getCid();          
            tableModel_musteriler.addRow(data); 
        }
	}
	
	public void updateTableModel_kur(Mudur mudur) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_kur.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[3];
		
		for(int i = 0; i < mudur.kurListesi().size(); i++){			
            data[0] = mudur.kurListesi().get(i).getParaBirimi1();
            data[1] = mudur.kurListesi().get(i).getParaBirimi2();
            data[2] = mudur.kurListesi().get(i).getKurfarki();
         
            tableModel_kur.addRow(data); 
        }
	}
	
	public void updateTableModel_banka(Mudur mudur) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_banka.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[4];		
            
        data[0] = mudur.hesapBul(0).getGelir();
        data[1] = mudur.hesapBul(0).getGider();
        data[2] = mudur.hesapBul(0).getGelir()-mudur.hesapBul(0).getGider();
        data[3] = mudur.hesapBul(0).getBakiye();
        tableModel_banka.addRow(data);       
	}
}
