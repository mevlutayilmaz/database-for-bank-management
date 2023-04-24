package panaller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import kullanicilar.Musteri;
import kullanicilar.Temsilci;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MusteriPaneli extends JFrame {

	private JPanel contentPane;
	static Musteri musteri = new Musteri();
	private JTable table_hesaplar;
	private DefaultTableModel tableModel_hesaplar = null;
	private Object[] data = null;
	private DefaultTableModel tableModel_aylikozet = null;
	private Object[] data2 = null;
	private DefaultTableModel tableModel_bilgiguncelle = null;
	private Object[] data3 = null;
	private DefaultTableModel tableModel_borclar = null;
	private Object[] data4 = null;
	private JTextField field_miktarcek;
	private JTextField field_miktaryatir;
	private JTextField field_kaynakid;
	private JTextField field_hedefid;
	private JTextField field_transfermiktar;
	private JTextField field_hesapid;
	private JTextField field_parabirimi;
	private JTextField field_silhesapid;
	private JTextField field_krediode;
	private JTextField field_kreditalep;
	private JTable table_bilgiguncelle;
	private JTable table_aylikozet;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table_borclar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriPaneli frame = new MusteriPaneli(musteri);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MusteriPaneli(Musteri musteri) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1098, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tableModel_hesaplar = new DefaultTableModel();
        Object[] colName = new Object[6];
        colName[0] = "hid";
        colName[1] = "mid";
        colName[2] = "Para_Birimi";
        colName[3] = "Bakiye";
        colName[4] = "Gelir";
        colName[5] = "Gider";
        tableModel_hesaplar.setColumnIdentifiers(colName);
		
        data = new Object[6];
        
        for(int i = 0; i < musteri.hesapListesi().size(); i++){
            data[0] = musteri.hesapListesi().get(i).getHid();
            data[1] = musteri.hesapListesi().get(i).getMid();
            data[2] = musteri.hesapListesi().get(i).getPara_Birimi();
            data[3] = musteri.hesapListesi().get(i).getBakiye();   
            data[4] = musteri.hesapListesi().get(i).getGelir();
            data[5] = musteri.hesapListesi().get(i).getGider();
            tableModel_hesaplar.addRow(data);         
        }
        
		tableModel_bilgiguncelle = new DefaultTableModel();
        Object[] colName2 = new Object[7];
        colName2[0] = "mid";
        colName2[1] = "Ad_Soyad";
        colName2[2] = "Telefon";
        colName2[3] = "TC_No";
        colName2[4] = "Adres";
        colName2[5] = "Eposta";
        colName2[6] = "cid";
        
        tableModel_bilgiguncelle.setColumnIdentifiers(colName2);
		
        data2 = new Object[7];
        
        data2[0] = musteri.getId();
        data2[1] = musteri.getAd_Soyad();
        data2[2] = musteri.getTelefon();
        data2[3] = musteri.getTC_No();
        data2[4] = musteri.getAdres();
        data2[5] = musteri.getEposta();
        data2[6] = musteri.getCid();
        tableModel_bilgiguncelle.addRow(data2);         
        
		tableModel_aylikozet = new DefaultTableModel();
        Object[] colName3 = new Object[8];
        colName3[0] = "Ýslem_No";
        colName3[1] = "Kaynak_ID";
        colName3[2] = "Hedef_ID";
        colName3[3] = "Ýslem";
        colName3[4] = "Tutar";
        colName3[5] = "Kaynak_Bakiye";
        colName3[6] = "Hedef_Bakiye";
        colName3[7] = "Tarih";
        
        tableModel_aylikozet.setColumnIdentifiers(colName3);
		
        data3 = new Object[8];
        
        for(int i = 0; i < musteri.islemListesi().size(); i++){
            data3[0] = musteri.islemListesi().get(i).getId();
            data3[1] = musteri.islemListesi().get(i).getKaynak_ID();
            data3[2] = musteri.islemListesi().get(i).getHedef_ID();
            data3[3] = musteri.islemListesi().get(i).getIslem();
            data3[4] = musteri.islemListesi().get(i).getTutar();
            data3[5] = musteri.islemListesi().get(i).getKaynak_Bakite();
            data3[6] = musteri.islemListesi().get(i).getHedef_Bakiye();
            data3[7] = musteri.islemListesi().get(i).getTarih();
            tableModel_aylikozet.addRow(data3);         
        }
        
        tableModel_borclar = new DefaultTableModel();
        Object[] colName4 = new Object[6];
        colName4[0] = "bid";
        colName4[1] = "hid";
        colName4[2] = "Kredi_Borcu";
        colName4[3] = "Vade";
        colName4[4] = "Son_Ödeme_Tarihi";
        colName4[5] = "Aylýk_Borç";
        tableModel_borclar.setColumnIdentifiers(colName4);
		
        data4 = new Object[6];
        
        for(int i = 0; i < musteri.borcListesi().size(); i++){
            data4[0] = musteri.borcListesi().get(i).getBid();
            data4[1] = musteri.borcListesi().get(i).getHid();
            data4[2] = musteri.borcListesi().get(i).getKredi_Borcu();
            data4[3] = musteri.borcListesi().get(i).getVade();   
            data4[4] = musteri.borcListesi().get(i).getSon_Ödeme_Tarihi();
            data4[5] = musteri.borcListesi().get(i).getAylýk_Borç();
            tableModel_borclar.addRow(data4);         
        }

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + musteri.getAd_Soyad());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton button_cikis = new JButton("Cikis Yap");
		button_cikis.addActionListener(new ActionListener() {
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
		button_cikis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblNewLabel_2_1 = new JLabel(musteri.degerListesi().get(0).getTarih().toString());
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_cikis)
							.addGap(16))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1055, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_cikis)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panel_hesapislem = new JPanel();
		tabbedPane.addTab("Hesap \u0130\u015Flemleri", null, panel_hesapislem, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("\u00C7ekmek istedi\u011Finiz miktar:");
		
		field_miktarcek = new JTextField();
		field_miktarcek.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Yat\u0131rmak istedi\u011Finiz miktar:");
		
		field_miktaryatir = new JTextField();
		field_miktaryatir.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Kaynak Hesap ID:");
		
		field_kaynakid = new JTextField();
		field_kaynakid.setEditable(false);
		field_kaynakid.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Hedef Hesap ID:");
		
		field_hedefid = new JTextField();
		field_hedefid.setColumns(10);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Para Transferi Miktar\u0131:");
		
		field_transfermiktar = new JTextField();
		field_transfermiktar.setColumns(10);
		
		JButton button_transfer = new JButton("Para Transferi Yap");
		button_transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_kaynakid.getText().length() == 0 || field_hedefid.getText().length() == 0 || field_transfermiktar.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else {
					int kaynakID = Integer.parseInt(field_kaynakid.getText());
					int hedefID = Integer.parseInt(field_hedefid.getText());
					double miktar = Integer.parseInt(field_transfermiktar.getText());
					
					try {
						if(musteri.hesapBul(kaynakID).getBakiye() >= miktar) {
							boolean kontrol = musteri.paraTransferi(kaynakID,hedefID,miktar);
							if(kontrol) {
								if(musteri.islemEkle(kaynakID, hedefID, miktar, 3)) {
									JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
									field_kaynakid.setText(null);
									field_hedefid.setText(null);
									field_transfermiktar.setText(null);
									updateTableModel(musteri);
								}
							}			
						}
						else
							JOptionPane.showMessageDialog(null, "Hesapta yeterli bakiye bulunmamaktadýr..!");
					} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}		
				}		
			}
		});
		
		JButton button_cek = new JButton("\u00C7ek");
		button_cek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_hesapid.getText().length() == 0 || field_miktarcek.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else {
					int selectID = Integer.parseInt(field_hesapid.getText());
					double miktar = Double.parseDouble(field_miktarcek.getText());
					try {
						if(miktar <= musteri.hesapBul(selectID).getBakiye()) {
							try {
							boolean kontrol = musteri.hesapGuncelle(selectID,miktar,0);
							if(kontrol) {
								if(musteri.islemEkle(selectID, 0, miktar, 0)) {
									JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
									field_hesapid.setText(null);
									field_miktarcek.setText(null);
									updateTableModel(musteri);
								}
							}
							} catch (SQLException | ClassNotFoundException e1) {
								e1.printStackTrace();
							}	
						}
						else {
							JOptionPane.showMessageDialog(null, "Hesapta yeterli bakiye bulunmamaktadýr!");
							field_hesapid.setText(null);
							field_miktarcek.setText(null);
						}				
					} catch (HeadlessException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}		
			}
		});
		
		JButton button_yatir = new JButton("Yat\u0131r");
		button_yatir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_hesapid.getText().length() == 0 || field_miktaryatir.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else {
					int selectID = Integer.parseInt(field_hesapid.getText());
					double miktar = Double.parseDouble(field_miktaryatir.getText());
					try {
						boolean kontrol = musteri.hesapGuncelle(selectID,miktar,1);
						if(kontrol) {
							if(musteri.islemEkle(selectID, 0, miktar, 1)) {
								JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
								field_hesapid.setText(null);
								field_miktaryatir.setText(null);
								updateTableModel(musteri);
							}

						}
					} catch (SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}			
				}		
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Hesap ID:");
		
		field_hesapid = new JTextField();
		field_hesapid.setEditable(false);
		field_hesapid.setColumns(10);
		
		JButton btnYeniHesapTalebi = new JButton("Yeni Hesap Talebi");
		btnYeniHesapTalebi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_parabirimi.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else{
					try {					
						boolean kontrol = musteri.talepEkle(musteri.getId(), 0, 0, 0, field_parabirimi.getText(), 1);

						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Talebiniz Oluþturulmuþtur.");
							field_parabirimi.setText(null);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		field_parabirimi = new JTextField();
		field_parabirimi.setColumns(10);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Para Birimi:");
		
		JButton btnHesapSilmeTalebi = new JButton("Silme Talebi");
		btnHesapSilmeTalebi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_silhesapid.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir müþteri seçiniz..!");
				else {
					int res = JOptionPane.showConfirmDialog(null, "Bu iþlemi gerçekleþtirmek istiyormusunuz?","Dikkat..!",JOptionPane.YES_NO_OPTION);
					if(res == 0) {
						int hesapid = Integer.parseInt(field_silhesapid.getText());
						try {
							if(musteri.hesapBul(hesapid).getBakiye() != 0) {
								JOptionPane.showMessageDialog(null, "Bakiyesi “0” olmayan bir hesap silinemez.");
								field_silhesapid.setText(null);
							}
							else {
								boolean kontrol = musteri.talepEkle(musteri.getId(),hesapid,0,0,"",0);
								if(kontrol) {
									JOptionPane.showMessageDialog(null, "Talebiniz Oluþturulmuþtur.");
									field_silhesapid.setText(null);
								}
							}	
						} catch (SQLException | ClassNotFoundException e1) {
							e1.printStackTrace();
						}		
					}
				}
			}
		});
		
		field_silhesapid = new JTextField();
		field_silhesapid.setEditable(false);
		field_silhesapid.setColumns(10);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Hesap ID:");
		
		field_krediode = new JTextField();
		field_krediode.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Kredi Talep Et:");
		
		field_kreditalep = new JTextField();
		field_kreditalep.setColumns(10);
		
		JComboBox selected_vade = new JComboBox();
		selected_vade.setModel(new DefaultComboBoxModel(new String[] {"3", "6", "9", "12", "18", "24", "30", "36"}));
		
		JButton button_kreditalep = new JButton("Talep");
		button_kreditalep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_kreditalep.getText().length() == 0 || field_hesapid.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else {
					int hesapid = Integer.parseInt(field_hesapid.getText());
					int miktar = Integer.parseInt(field_kreditalep.getText());
					int vade = Integer.parseInt(selected_vade.getSelectedItem().toString());
					
					try {					
						if(musteri.hesapBul(hesapid).getPara_Birimi().equals("TL")) {
							boolean kontrol = musteri.talepEkle(musteri.getId(),hesapid,miktar,vade,"",2);
							if(kontrol) {
								JOptionPane.showMessageDialog(null, "Talebiniz Oluþturulmuþtur.");
								field_hesapid.setText(null);
								field_kreditalep.setText(null);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, " Kredi sadece TL cinsinden talep edilebilmektedir.");
						}
							
					} catch (SQLException | HeadlessException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
				}	
			}
		});
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Kredi Borcu \u00D6deme:");
		
		JButton button_krediode = new JButton("\u00D6de");
		button_krediode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_hesapid.getText().length() == 0 && field_krediode.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir hesap seçiniz..!");
				else {
					int selectID = Integer.parseInt(field_hesapid.getText());
					double miktar = Double.parseDouble(field_krediode.getText());
					
					try {
						boolean kontrol = false;
						Long fark = (musteri.borcBul(selectID).getSon_Ödeme_Tarihi().getTime()-musteri.degerListesi().get(0).getTarih().getTime())/(1000*60*60*24);
						int durum = 0;
						
						if(fark > 30) {
							kontrol = musteri.hesapGuncelle(selectID,miktar,4);
							durum = 3;
						}
						else if(fark < 0) {
							kontrol = musteri.hesapGuncelle(selectID,miktar,3);
							durum = 2;
						}
						else {
							kontrol = musteri.hesapGuncelle(selectID,miktar,2);
							durum = 1;
						}
						if(kontrol) {
							if(musteri.islemEkle(selectID, durum, miktar, 2)) {
								if(musteri.borcBul(selectID).getVade() != 0) {
									if(musteri.borcBul(selectID).getKredi_Borcu()/musteri.borcBul(selectID).getVade() == miktar) 
										musteri.islemEkle(selectID, 0, 0, 7);
									if(musteri.borcBul(selectID).getKredi_Borcu()/musteri.borcBul(selectID).getVade() < miktar) 
										musteri.islemEkle(selectID, 0, (miktar-musteri.borcBul(selectID).getKredi_Borcu()/musteri.borcBul(selectID).getVade()), 7);
								}
								else {
									musteri.borcSil(selectID);
								}
								
								JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
								field_hesapid.setText(null);
								field_krediode.setText(null);
								updateTableModel(musteri);
								updateTableModel_borclar(musteri);
							}
						}
						
					} catch (ClassNotFoundException | SQLException e2) {
						
						e2.printStackTrace();
					}
				}			
			}
		});
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Kredi Vadesi(Ay):");
		
		JButton button_tamaminiode = new JButton("T\u00FCm Borcu \u00D6de");
		button_tamaminiode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_hesapid.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir hesap seçiniz..!");
				else {
					int selectID = Integer.parseInt(field_hesapid.getText());
					
					try {
						double kredi = musteri.borcBul(selectID).getKredi_Borcu();
						boolean kontrol = musteri.hesapGuncelle(selectID,kredi,5);
						
						if(kontrol) {
							if(musteri.islemEkle(selectID, 0, kredi, 4)) {
								JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
								field_hesapid.setText(null);
								field_krediode.setText(null);
								updateTableModel(musteri);
								updateTableModel_borclar(musteri);
							}
						}	
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		GroupLayout gl_panel_hesapislem = new GroupLayout(panel_hesapislem);
		gl_panel_hesapislem.setHorizontalGroup(
			gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_hesapislem.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_hesapislem.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(gl_panel_hesapislem.createSequentialGroup()
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_2_1_1_2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING, false)
									.addComponent(field_kreditalep, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_hesapislem.createSequentialGroup()
										.addComponent(selected_vade, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
										.addGap(64)
										.addComponent(button_kreditalep, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_panel_hesapislem.createSequentialGroup()
									.addComponent(field_krediode, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_krediode, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_tamaminiode, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel_hesapislem.createSequentialGroup()
										.addComponent(field_miktaryatir, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addComponent(button_yatir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(gl_panel_hesapislem.createSequentialGroup()
										.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(field_hesapid, Alignment.LEADING)
											.addComponent(field_miktarcek, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
										.addGap(26)
										.addComponent(button_cek, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))))
							.addGap(167)))
					.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_kaynakid, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.TRAILING)
							.addComponent(button_transfer, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(field_transfermiktar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_2_1_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addComponent(field_parabirimi, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
										.addComponent(field_silhesapid, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_hesapislem.createSequentialGroup()
											.addGap(122)
											.addComponent(btnHesapSilmeTalebi, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
									.addComponent(btnYeniHesapTalebi, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))))
						.addComponent(field_hedefid, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		gl_panel_hesapislem.setVerticalGroup(
			gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_hesapislem.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_hesapislem.createSequentialGroup()
							.addComponent(lblNewLabel_1_2)
							.addGap(2)
							.addComponent(field_kaynakid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_3)
							.addGap(2)
							.addComponent(field_hedefid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_2_1)
							.addGap(2)
							.addComponent(field_transfermiktar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_transfer, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(lblNewLabel_1_2_1_1)
							.addGap(2)
							.addComponent(field_parabirimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnYeniHesapTalebi, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(lblNewLabel_1_2_1_1_1)
							.addGap(2)
							.addComponent(field_silhesapid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnHesapSilmeTalebi, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_hesapislem.createSequentialGroup()
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addGap(2)
							.addComponent(field_hesapid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(lblNewLabel_1)
							.addGap(2)
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.BASELINE)
								.addComponent(field_miktarcek, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_cek))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_1)
							.addGap(2)
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.BASELINE)
								.addComponent(field_miktaryatir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_yatir))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_1_1)
							.addGap(2)
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.BASELINE)
								.addComponent(field_krediode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_krediode)
								.addComponent(button_tamaminiode))
							.addGap(10)
							.addComponent(lblNewLabel_1_1_1)
							.addGap(3)
							.addComponent(field_kreditalep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_2_1_1_2)
							.addGap(2)
							.addGroup(gl_panel_hesapislem.createParallelGroup(Alignment.BASELINE)
								.addComponent(selected_vade, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_kreditalep, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(76))))
		);
		
		table_borclar = new JTable(tableModel_borclar);
		scrollPane_1.setViewportView(table_borclar);
		
		table_borclar.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {		
			public void valueChanged(ListSelectionEvent e) {
				try {
					field_krediode.setText(table_borclar.getValueAt(table_borclar.getSelectedRow(), 5).toString());
					field_hesapid.setText(table_borclar.getValueAt(table_borclar.getSelectedRow(), 1).toString());
					
				}catch(Exception ex) {
					
				}
			}
		});
		
		table_hesaplar = new JTable(tableModel_hesaplar);
		scrollPane.setViewportView(table_hesaplar);
		
        table_hesaplar.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {		
			public void valueChanged(ListSelectionEvent e) {
				try {
					field_silhesapid.setText(table_hesaplar.getValueAt(table_hesaplar.getSelectedRow(), 0).toString());
					field_hesapid.setText(table_hesaplar.getValueAt(table_hesaplar.getSelectedRow(), 0).toString());
					field_kaynakid.setText(table_hesaplar.getValueAt(table_hesaplar.getSelectedRow(), 0).toString());
				}catch(Exception ex) {
					
				}
			}
		});
        
		panel_hesapislem.setLayout(gl_panel_hesapislem);
		
		JPanel panel_musbilgi = new JPanel();
		tabbedPane.addTab("Musteri Bilgiler", null, panel_musbilgi, null);
		
		JScrollPane scrollPane_bilgiguncelle = new JScrollPane();
		
		JScrollPane scrollPane_aylikozet = new JScrollPane();
		
		JButton btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					updateTableModel_islemler(musteri);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGncelle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panel_musbilgi = new GroupLayout(panel_musbilgi);
		gl_panel_musbilgi.setHorizontalGroup(
			gl_panel_musbilgi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_musbilgi.createSequentialGroup()
					.addGroup(gl_panel_musbilgi.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_musbilgi.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_musbilgi.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane_aylikozet, Alignment.LEADING)
								.addComponent(scrollPane_bilgiguncelle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)))
						.addGroup(gl_panel_musbilgi.createSequentialGroup()
							.addGap(453)
							.addComponent(btnGncelle, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_panel_musbilgi.setVerticalGroup(
			gl_panel_musbilgi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_musbilgi.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_bilgiguncelle, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(scrollPane_aylikozet, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(btnGncelle, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(93, Short.MAX_VALUE))
		);
		
		table_aylikozet = new JTable(tableModel_aylikozet);
		scrollPane_aylikozet.setViewportView(table_aylikozet);
		
		table_bilgiguncelle = new JTable(tableModel_bilgiguncelle);
		scrollPane_bilgiguncelle.setViewportView(table_bilgiguncelle);
		
		table_bilgiguncelle.getModel().addTableModelListener((TableModelListener) new TableModelListener() {
			 public void tableChanged(TableModelEvent e) {
				 if(e.getType() == TableModelEvent.UPDATE) {
					 int selectID = Integer.parseInt(table_bilgiguncelle.getValueAt(table_bilgiguncelle.getSelectedRow(), 0).toString());
					 String selectAd = table_bilgiguncelle.getValueAt(table_bilgiguncelle.getSelectedRow(), 1).toString();
					 String selectTel = table_bilgiguncelle.getValueAt(table_bilgiguncelle.getSelectedRow(), 2).toString();
					 String selectTc = table_bilgiguncelle.getValueAt(table_bilgiguncelle.getSelectedRow(), 3).toString();
					 String selectAdres = table_bilgiguncelle.getValueAt(table_bilgiguncelle.getSelectedRow(), 4).toString();
					 String selectEposta = table_bilgiguncelle.getValueAt(table_bilgiguncelle.getSelectedRow(), 5).toString();				 
					 
					 try {
						boolean kontrol = musteri.musteriGuncelle(selectID,selectAd,selectTel,selectTc,selectAdres,selectEposta);
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 }
			 }
		 });

		panel_musbilgi.setLayout(gl_panel_musbilgi);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void updateTableModel(Musteri mus) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_hesaplar.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[6];
		
		for(int i = 0; i < mus.hesapListesi().size(); i++){			
            data[0] = mus.hesapListesi().get(i).getHid();
            data[1] = mus.hesapListesi().get(i).getMid();
            data[2] = mus.hesapListesi().get(i).getPara_Birimi();
            data[3] = mus.hesapListesi().get(i).getBakiye();
            data[4] = mus.hesapListesi().get(i).getGelir();
            data[5] = mus.hesapListesi().get(i).getGider();
            tableModel_hesaplar.addRow(data);         
        }
	}
	
	public void updateTableModel_borclar(Musteri mus) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_borclar.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[6];
		
		for(int i = 0; i < mus.borcListesi().size(); i++){			
            data[0] = mus.borcListesi().get(i).getBid();
            data[1] = mus.borcListesi().get(i).getHid();
            data[2] = mus.borcListesi().get(i).getKredi_Borcu();
            data[3] = mus.borcListesi().get(i).getVade();
            data[4] = mus.borcListesi().get(i).getSon_Ödeme_Tarihi();
            data[5] = mus.borcListesi().get(i).getAylýk_Borç();
            tableModel_borclar.addRow(data);         
        }
	}
	
	public void updateTableModel_islemler(Musteri mus) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_aylikozet.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[8];
		
		for(int i = 0; i < mus.islemListesi().size(); i++){			
            data[0] = mus.islemListesi().get(i).getId();
            data[1] = mus.islemListesi().get(i).getKaynak_ID();
            data[2] = mus.islemListesi().get(i).getHedef_ID();
            data[3] = mus.islemListesi().get(i).getIslem();
            data[4] = mus.islemListesi().get(i).getTutar();
            data[5] = mus.islemListesi().get(i).getKaynak_Bakite();
            data[6] = mus.islemListesi().get(i).getHedef_Bakiye();
            data[7] = mus.islemListesi().get(i).getTarih();
            tableModel_aylikozet.addRow(data);           
        }
	}
}
