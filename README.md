# Database For Bank Management

## Proje Özeti

Bu projede bir bankanın yönetim sistemi için bir veritabanı tasarımı yapılmıştır ve bu veritabanı üzerinde gerekli işlemleri gerçekleştiren bir uygulama geliştirilmiştir. Tasarlanan veritabanı, bankanın müşteri, çalışan, hesap ve işlemlerle ilgili bilgileri organize bir şekilde işlemesine yardımcı olacaktır. Böylece bankanın ve müşterilerin ihtiyaç duyduğu bilgilere daha kolay ulaşılması sağlanmıştır. Ayrıca, veritabanı kullanılarak müşteri istekleri ve bankanın ihtiyaçları doğrultusunda raporlar hazırlanabilecektir.

Veritabanı tasarımının ilk aşamasında sistemin ihtiyaçlarının belirlenmesi ve depolanacak bilgi türlerinin tanımlanması için Varlık-İlişki (ER) diyagramı oluşturulmuştur. ER diyagramı, sistem içindeki varlıkların ve aralarındaki ilişkilerin görsel olarak ifade edilmesi için kullanılmıştır. Geliştirme sırasında, ER diyagramı gereksinimlerin daha açık ve özlü bir şekilde haritalanmasına yardımcı olmuştur.

**Bankadaki Roller:**
- Müşteriler
- Temsilciler
- Banka Müdürü

**Veritabanında Saklanacak Bilgiler:**
- Müşteriler ve çalışanlar için tanımlayıcı bilgiler (Ad Soyad, Telefon, TC No, Adres, E-posta)
- Hesap bilgileri (Her müşteri birden fazla hesaba sahip olabilir ve hesaplar farklı para birimlerinde açılabilir)
- İşlem bilgileri

**Müşterilerin Yapabileceği İşlemler:**
- Hesaplardan para çekme ve yatırma
- Yeni hesap açma ve mevcut hesabı silme (Bakiyesi "0" olmayan hesaplar silinemez)
- Hesaplar arası para transferi (Para birimleri otomatik dönüştürülür)
- Bilgi güncellemeleri (Adres, Telefon vb.)
- Bankaya para transferi (Kredi borcu ödeme)
- Kredi talebi (Sadece TL cinsinden kredi talep edilebilir, faiz ve anapara ayrı ayrı görüntülenir)
- Aylık özet görüntüleme

**Banka Müdürünün Yapabileceği İşlemler:**
- Bankanın genel durumunu görüntüleme (Gelir, gider, kar, toplam bakiye)
- Yeni para birimleri ekleme ve kur değerlerini güncelleme
- Çalışan maaşlarını belirleme
- Kredi ve gecikme faiz oranlarını belirleme
- Yeni müşteri ekleme ve temsilci atama
- Sistemi bir ay ileriye taşıma (Maaş ödemeleri, gelir-gider güncellemeleri, borçların yansıması)

**Müşteri Temsilcisinin Yapabileceği İşlemler:**
- Müşteri ekleme, silme ve güncelleme (Sadece kendi müşterileri için)
- Müşterilerin hesap ve borç bilgilerini görüntüleme
- Müşteri taleplerini onaylama ve işlemleri yönetme

## Temel Bilgiler ve Yöntemler

### Yardımcı Sınıflar
- **DBbaglanti.java:** Veritabanı bağlantısını sağlayan sınıf. Birçok yerde hızlı erişim için oluşturulmuştur.

### Kullanıcı Sınıfları
- **Kullanici.java:** Müşteri, müşteri temsilcisi ve banka müdürü için oluşturulmuş sınıftır. Veritabanından gerekli bilgileri çeker ve günceller.
- **Musteri.java:** Müşteri işlemleri ve güncellemeleri için fonksiyonlar içerir.
- **Temsilci.java:** Temsilci işlemleri ve müşteri yönetimi için fonksiyonlar içerir.
- **Mudur.java:** Banka müdürü işlemleri ve genel yönetim için fonksiyonlar içerir.
- **Hesap.java:** Banka hesaplarının değerlerini tutar.
- **Talep.java:** Müşterilerin taleplerinin değerlerini tutar.
- **Islem.java:** Müşterilerin gerçekleştirdiği işlemlerin değerlerini tutar.
- **Borclar.java:** Müşterilerin kredi borçlarının değerlerini tutar.
- **Degerler.java:** Bankanın faiz oranları ve çalışan maaşlarını tutar.
- **Kur.java:** Para birimlerinin kur değerlerini tutar.

### Panel Sınıfları
- **AnaPanel.java:** Programın başlatıldığı ilk paneldir. Kullanıcıların giriş yapmak istediği paneli seçmelerine olanak tanır.
- **MusteriPaneli.java:** Müşterilerin işlemlerini gerçekleştirebildiği paneldir.
- **TemsilciPaneli.java:** Müşteri temsilcilerinin işlemlerini gerçekleştirebildiği paneldir.
- **MudurPaneli.java:** Banka müdürünün işlemlerini gerçekleştirebilidiği paneldir.

## Kullanılan Teknolojiler

- **Java:** Geliştirme dili
- **Java Swing:** GUI oluşturma
- **Microsoft SQL:** İlişkisel veritabanı

## Ekran Görüntüleri

<table style="border-spacing: 0; border-collapse: collapse; width: 100%;">
  <tr>
    <td style="padding: 0; vertical-align: middle; text-align: center;">
      <img src="https://github.com/user-attachments/assets/f63b95fa-6490-4cb2-b99c-e35841011ddc" width="400" />
      <p style="text-align: center;">Ana Panel</p>
    </td>
    <td style="padding: 0; vertical-align: middle; text-align: center;">
      <img src="https://github.com/user-attachments/assets/dd8ead92-c6cd-4eff-9a72-3714933ccf9c" width="400" />
      <p style="text-align: center;">Müşteri Paneli</p>
    </td>
  </tr>
  <tr>
    <td style="padding: 0; vertical-align: middle; text-align: center;">
      <img src="https://github.com/user-attachments/assets/ed7f47a4-0408-424e-968e-bd4f6fe9c636" width="400" />
      <p style="text-align: center;">Temsilci Paneli</p>
    </td>
    <td style="padding: 0; vertical-align: middle; text-align: center;">
      <img src="https://github.com/user-attachments/assets/c6429a19-3c2e-4b06-a85c-35604499aa67" width="400" />
      <p style="text-align: center;">Müdür Paneli</p>
    </td>
  </tr>
  <tr>
    <td colspan="2" style="padding: 0; vertical-align: middle; text-align: center;">
      <img src="https://github.com/user-attachments/assets/d90cbe68-4088-4c37-b406-4e73eec4729f" width="400" />
      <p style="text-align: center;">Varlık-İlişki (ER) Diyagramı</p>
    </td>
  </tr>
</table>





