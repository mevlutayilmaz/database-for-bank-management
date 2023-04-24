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

import kullanicilar.Temsilci;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TemsilciPaneli extends JFrame {

	static Temsilci temsilci = new Temsilci();
	private JPanel contentPane;
	private JTextField field_musad;
	private JTextField field_mustel;
	private JTextField field_mustc;
	private JTextField field_musadres;
	private JTextField field_musposta;
	private JTextField field_musid;
	private JTable table;
	private DefaultTableModel tableModel = null;
	private DefaultTableModel tableModel_islemler = null;
	private DefaultTableModel tableModel_talepler = null;
	private DefaultTableModel tableModel_hesaplar = null;
	private DefaultTableModel tableModel_borclar = null;
	private Object[] data = null;
	private Object[] data2 = null;
	private Object[] data3 = null;
	private Object[] data4 = null;
	private Object[] data5 = null;
	
	private JTable table_islemler;
	private JTable table_talepler;
	private JTextField field_talepid;
	private JTable table_hesaplar;
	private JTable table_borclar;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemsilciPaneli frame = new TemsilciPaneli(temsilci);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TemsilciPaneli(Temsilci temsilci) throws SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tableModel = new DefaultTableModel();
        Object[] colName = new Object[7];
        colName[0] = "mid";
        colName[1] = "Ad_Soyad";
        colName[2] = "Telefon";
        colName[3] = "TC_No";
        colName[4] = "Adres";
        colName[5] = "Eposta";
        colName[6] = "cid";
        tableModel.setColumnIdentifiers(colName);
        
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
        
        data = new Object[7];
        
        for(int i = 0; i < temsilci.temsilListesi().size(); i++){
            data[0] = temsilci.temsilListesi().get(i).getId();
            data[1] = temsilci.temsilListesi().get(i).getAd_Soyad();
            data[2] = temsilci.temsilListesi().get(i).getTelefon();
            data[3] = temsilci.temsilListesi().get(i).getTC_No();
            data[4] = temsilci.temsilListesi().get(i).getAdres();
            data[5] = temsilci.temsilListesi().get(i).getEposta();
            data[6] = temsilci.temsilListesi().get(i).getCid();
            tableModel.addRow(data);         
        }
        
        data2 = new Object[8];
        
        for(int i = 0; i < temsilci.temsilci_islemler().size(); i++){
            data2[0] = temsilci.temsilci_islemler().get(i).getId();
            data2[1] = temsilci.temsilci_islemler().get(i).getKaynak_ID();
            data2[2] = temsilci.temsilci_islemler().get(i).getHedef_ID();
            data2[3] = temsilci.temsilci_islemler().get(i).getIslem();
            data2[4] = temsilci.temsilci_islemler().get(i).getTutar();
            data2[5] = temsilci.temsilci_islemler().get(i).getKaynak_Bakite();
            data2[6] = temsilci.temsilci_islemler().get(i).getHedef_Bakiye();
            data2[7] = temsilci.temsilci_islemler().get(i).getTarih();
            tableModel_islemler.addRow(data2);         
        }
        
        tableModel_talepler = new DefaultTableModel();
        Object[] colName3 = new Object[7];
        colName3[0] = "tid";
        colName3[1] = "mid";
        colName3[2] = "Talep";
        colName3[3] = "hid";
        colName3[4] = "Miktar";
        colName3[5] = "Vade";
        colName3[6] = "Para_Birimi";

        tableModel_talepler.setColumnIdentifiers(colName3);
        
        data3 = new Object[7];
        
        for(int i = 0; i < temsilci.talepListesi().size(); i++){
            data3[0] = temsilci.talepListesi().get(i).getTid();
            data3[1] = temsilci.talepListesi().get(i).getMid();
            data3[2] = temsilci.talepListesi().get(i).getTalep();
            data3[3] = temsilci.talepListesi().get(i).getHid();
            data3[4] = temsilci.talepListesi().get(i).getMiktar();
            data3[5] = temsilci.talepListesi().get(i).getVade();
            data3[6] = temsilci.talepListesi().get(i).getPara_Birimi();
            tableModel_talepler.addRow(data3);         
        }
        
        tableModel_hesaplar = new DefaultTableModel();
        Object[] colName4 = new Object[6];
        colName4[0] = "hid";
        colName4[1] = "mid";
        colName4[2] = "Para_Birimi";
        colName4[3] = "Bakiye";
        colName4[4] = "Gelir";
        colName4[5] = "Gider";
        tableModel_hesaplar.setColumnIdentifiers(colName4);
		
        data4 = new Object[6];
        
        for(int i = 0; i < temsilci.hesapListesi().size(); i++){
            data4[0] = temsilci.hesapListesi().get(i).getHid();
            data4[1] = temsilci.hesapListesi().get(i).getMid();
            data4[2] = temsilci.hesapListesi().get(i).getPara_Birimi();
            data4[3] = temsilci.hesapListesi().get(i).getBakiye();
            data4[4] = temsilci.hesapListesi().get(i).getGelir();
            data4[5] = temsilci.hesapListesi().get(i).getGider();
            tableModel_hesaplar.addRow(data4);         
        }
        
        tableModel_borclar = new DefaultTableModel();
        Object[] colName5 = new Object[6];
        colName5[0] = "bid";
        colName5[1] = "hid";
        colName5[2] = "Kredi_Borcu";
        colName5[3] = "Vade";
        colName5[4] = "Son_Ödeme_Tarihi";
        colName5[5] = "Aylýk_Borç";
        tableModel_borclar.setColumnIdentifiers(colName5);
		
        data5 = new Object[6];
        
        for(int i = 0; i < temsilci.borcListesi().size(); i++){
        	data5[0] = temsilci.borcListesi().get(i).getBid();
        	data5[1] = temsilci.borcListesi().get(i).getHid();
        	data5[2] = temsilci.borcListesi().get(i).getKredi_Borcu();
        	data5[3] = temsilci.borcListesi().get(i).getVade();   
        	data5[4] = temsilci.borcListesi().get(i).getSon_Ödeme_Tarihi();
        	data5[5] = temsilci.borcListesi().get(i).getAylýk_Borç();
            tableModel_borclar.addRow(data5);         
        }

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + temsilci.getAd_Soyad());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
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
		
		JLabel lblNewLabel_2_1 = new JLabel(temsilci.degerListesi().get(0).getTarih().toString());
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 578, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1058, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel panel_bilgi = new JPanel();
		tabbedPane.addTab("Musteri Bilgileri", null, panel_bilgi, null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad_Soyad:");
		
		field_musad = new JTextField();
		field_musad.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Telefon:");
		
		field_mustel = new JTextField();
		field_mustel.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("TC_No:");
		
		field_mustc = new JTextField();
		field_mustc.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Adres:");
		
		field_musadres = new JTextField();
		field_musadres.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Eposta:");
		
		field_musposta = new JTextField();
		field_musposta.setColumns(10);
		
		JButton button_ekle = new JButton("Ekle");
		button_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_musad.getText().length() == 0 || field_mustel.getText().length() == 0 || field_mustc.getText().length() == 0 || field_musadres.getText().length() == 0 || field_musposta.getText().length() == 0 )
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanlarý doldurunuz..!");
				else{
					try {
						boolean kontrol = temsilci.musteriEkle(field_musad.getText(), field_mustel.getText(), field_mustc.getText(), field_musadres.getText(), field_musposta.getText());
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
							field_musad.setText(null);
							field_mustel.setText(null);
							field_mustc.setText(null);
							field_musadres.setText(null);
							field_musposta.setText(null);
							updateTableModel(temsilci);
						}
					} catch (SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblNewLabel_1_5 = new JLabel("Musteri ID:");
		
		field_musid = new JTextField();
		field_musid.setEditable(false);
		field_musid.setColumns(10);
		
		JButton button_sil = new JButton("Sil");
		button_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_musid.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir müþteri seçiniz..!");
				else {
					int res = JOptionPane.showConfirmDialog(null, "Bu iþlemi gerçekleþtirmek istiyormusunuz?","Dikkat..!",JOptionPane.YES_NO_OPTION);
					if(res == 0) {
						int selectID = Integer.parseInt(field_musid.getText());
						try {
							boolean kontrol = temsilci.musteriSil(selectID);
							if(kontrol) {
								JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
								field_musid.setText(null);
								updateTableModel(temsilci);
							}
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
						}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_bilgi = new GroupLayout(panel_bilgi);
		gl_panel_bilgi.setHorizontalGroup(
			gl_panel_bilgi.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_bilgi.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 671, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addGroup(gl_panel_bilgi.createParallelGroup(Alignment.LEADING)
						.addComponent(button_sil, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_5, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_musid, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_musad, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_musadres, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_mustc, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(field_mustel, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_bilgi.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(button_ekle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(field_musposta, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
					.addGap(51))
		);
		gl_panel_bilgi.setVerticalGroup(
			gl_panel_bilgi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_bilgi.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_bilgi.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_bilgi.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(field_musad, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblNewLabel_1_1)
							.addGap(6)
							.addComponent(field_mustel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_2)
							.addGap(6)
							.addComponent(field_mustc, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1_3)
							.addGap(6)
							.addComponent(field_musadres, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1_4)
							.addGap(6)
							.addComponent(field_musposta, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_ekle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1_5)
							.addGap(6)
							.addComponent(field_musid, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_sil, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {		
			public void valueChanged(ListSelectionEvent e) {
				try {
					field_musid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				}catch(Exception ex) {
					
				}
			}
		});	
		
		 table.getModel().addTableModelListener((TableModelListener) new TableModelListener() {
			 public void tableChanged(TableModelEvent e) {
				 if(e.getType() == TableModelEvent.UPDATE) {
					 int selectID = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					 String selectAd = table.getValueAt(table.getSelectedRow(), 1).toString();
					 String selectTel = table.getValueAt(table.getSelectedRow(), 2).toString();
					 String selectTc = table.getValueAt(table.getSelectedRow(), 3).toString();
					 String selectAdres = table.getValueAt(table.getSelectedRow(), 4).toString();
					 String selectEposta = table.getValueAt(table.getSelectedRow(), 5).toString();				 
					 
					 try {
						boolean kontrol = temsilci.musteriGuncelle(selectID,selectAd,selectTel,selectTc,selectAdres,selectEposta);
						if(kontrol) {
							JOptionPane.showMessageDialog(null, "Ýþlem Baþarýlý");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 }
			 }
		 });
			
		panel_bilgi.setLayout(gl_panel_bilgi);
		
		JPanel panel_durum = new JPanel();
		tabbedPane.addTab("Genel Durum", null, panel_durum, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel_durum = new GroupLayout(panel_durum);
		gl_panel_durum.setHorizontalGroup(
			gl_panel_durum.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_durum.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_durum.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
						.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_durum.setVerticalGroup(
			gl_panel_durum.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_durum.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table_borclar = new JTable(tableModel_borclar);
		scrollPane_3.setViewportView(table_borclar);
		
		table_hesaplar = new JTable(tableModel_hesaplar);
		scrollPane_2.setViewportView(table_hesaplar);
		panel_durum.setLayout(gl_panel_durum);
		
		JPanel panel_talep = new JPanel();
		tabbedPane.addTab("Talepler", null, panel_talep, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		field_talepid = new JTextField();
		field_talepid.setEditable(false);
		field_talepid.setColumns(10);
		
		JButton btnOnayla = new JButton("Onayla");
		btnOnayla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_talepid.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir talep seçiniz..!");
				else{
					try {
						int id = Integer.parseInt(field_talepid.getText());
						for(int i = 0; i < temsilci.talepListesi().size(); i++) {
							if(temsilci.talepListesi().get(i).getTid() == id) {
								if(temsilci.talepListesi().get(i).getTalep().equals("Hesap Silme")) {
									temsilci.islemSil(temsilci.talepListesi().get(i).getHid());
									if(temsilci.hesapSil(temsilci.talepListesi().get(i).getHid())) {
										JOptionPane.showMessageDialog(null, "Talep onaylandý.");
										field_talepid.setText(null);
										try {
											updateTableModel_talepler(temsilci);
										}catch(Exception a){
											
										}
									}
								}
								else if(temsilci.talepListesi().get(i).getTalep().equals("Yeni Hesap Oluþturma")) {
									if(temsilci.hesapEkle(temsilci.talepListesi().get(i).getMid(), temsilci.talepListesi().get(i).getPara_Birimi())) {
										if(temsilci.talepSil(temsilci.talepListesi().get(i).getTid())) {
											JOptionPane.showMessageDialog(null, "Talep onaylandý.");
											field_talepid.setText(null);
											try {
												updateTableModel_talepler(temsilci);
											}catch(Exception a){
												
											}
										}
									}
								}
								else if(temsilci.talepListesi().get(i).getTalep().equals("Kredi Talebi")) {
									if(temsilci.krediOlustur(temsilci.talepListesi().get(i).getHid(), temsilci.talepListesi().get(i).getMiktar(), temsilci.talepListesi().get(i).getVade())) {
										if(temsilci.talepSil(temsilci.talepListesi().get(i).getTid())) {
											JOptionPane.showMessageDialog(null, "Talep onaylandý.");
											field_talepid.setText(null);
											try {
												updateTableModel_talepler(temsilci);
											}catch(Exception a){
												
											}											
										}
									}
								}
							}
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Talep ID:");
		
		JButton button_reddet = new JButton("Reddet");
		button_reddet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_talepid.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Lütfen bir talep seçiniz..!");
				else {
					int id = Integer.parseInt(field_talepid.getText());
					try {
						for(int i = 0; i < temsilci.talepListesi().size(); i++) {
							if(temsilci.talepListesi().get(i).getTid() == id) {
								if(temsilci.talepSil(temsilci.talepListesi().get(i).getTid())) {
									JOptionPane.showMessageDialog(null, "Talep reddedildi.");
									field_talepid.setText(null);
									try {
										updateTableModel_talepler(temsilci);
									}catch(Exception a){
										
									}											
								}
							}
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		GroupLayout gl_panel_talep = new GroupLayout(panel_talep);
		gl_panel_talep.setHorizontalGroup(
			gl_panel_talep.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_talep.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_talep.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_talep.createSequentialGroup()
							.addGap(125)
							.addComponent(button_reddet, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnOnayla, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(37, Short.MAX_VALUE))
						.addGroup(gl_panel_talep.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_talep.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(field_talepid, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
							.addGap(29))))
		);
		gl_panel_talep.setVerticalGroup(
			gl_panel_talep.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_talep.createSequentialGroup()
					.addGroup(gl_panel_talep.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_talep.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_talep.createSequentialGroup()
							.addGap(42)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(field_talepid, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_talep.createParallelGroup(Alignment.LEADING)
								.addComponent(button_reddet, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOnayla, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		
		table_talepler = new JTable(tableModel_talepler);
		scrollPane_1.setViewportView(table_talepler);
		
		table_talepler.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					field_talepid.setText(table_talepler.getValueAt(table_talepler.getSelectedRow(), 0).toString());
				}catch(Exception a){
					
				}
			}	
		});

		panel_talep.setLayout(gl_panel_talep);
		
		JPanel panel_islem = new JPanel();
		tabbedPane.addTab("\u0130slemler", null, panel_islem, null);
		
		JScrollPane scrollPane_islemler = new JScrollPane();
		GroupLayout gl_panel_islem = new GroupLayout(panel_islem);
		gl_panel_islem.setHorizontalGroup(
			gl_panel_islem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_islem.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_islemler, GroupLayout.PREFERRED_SIZE, 1029, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_islem.setVerticalGroup(
			gl_panel_islem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_islem.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_islemler, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table_islemler = new JTable(tableModel_islemler);
		scrollPane_islemler.setViewportView(table_islemler);
		panel_islem.setLayout(gl_panel_islem);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void updateTableModel(Temsilci tem) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
		clearModel.setRowCount(0);
		
		data = new Object[7];
		
		for(int i = 0; i < tem.temsilListesi().size(); i++){			
            data[0] = tem.temsilListesi().get(i).getId();
            data[1] = tem.temsilListesi().get(i).getAd_Soyad();
            data[2] = tem.temsilListesi().get(i).getTelefon();
            data[3] = tem.temsilListesi().get(i).getTC_No();
            data[4] = tem.temsilListesi().get(i).getAdres();
            data[5] = tem.temsilListesi().get(i).getEposta();
            data[6] = tem.temsilListesi().get(i).getCid();          
            tableModel.addRow(data);
        }
	}
	
	public void updateTableModel_talepler(Temsilci tem) throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table_talepler.getModel();
		clearModel.setRowCount(0);
		
		data2 = new Object[7];
		
		for(int i = 0; i < tem.temsilListesi().size(); i++){			
            data2[0] = tem.talepListesi().get(i).getTid();
            data2[1] = tem.talepListesi().get(i).getMid();
            data2[2] = tem.talepListesi().get(i).getTalep();
            data2[3] = tem.talepListesi().get(i).getHid();
            data2[4] = tem.talepListesi().get(i).getMiktar();
            data2[5] = tem.talepListesi().get(i).getVade();
            data2[6] = tem.talepListesi().get(i).getPara_Birimi();
            tableModel_talepler.addRow(data2);
        }
	}
}
