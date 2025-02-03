# War_Vehicles_Card_Game

**Hazırlayanlar:** Fatma Nur Kurt
**Platform:** Masaüstü (Java Swing)

---

## Proje Hakkında

Savaş Araçları Kart Savaş Oyunu, nesneye yönelik programlama (NYP) prensiplerini derinlemesine kavramak ve uygulamak amacıyla geliştirilmiş, stratejik düşünmeyi teşvik eden dinamik bir kart savaş oyunudur. Bu proje, oyuncuların bilgisayara karşı (veya diğer oyunculara karşı) zekice hamleler yaparak kartlar üzerinden mücadele ettiği, esnek ve genişletilebilir bir yapı sunar.

---

## Neden Savaş Araçları Kart Savaş Oyunu?

- **Gerçek Zamanlı Strateji:**  
  Oyuncular, her hamlede elindeki kartlardan üç tanesini seçerek saldırı ve savunma stratejilerini belirler. Bilgisayar ise rastgele seçim yaparak rekabeti canlı tutar.

- **Kapsamlı OOP Uygulaması:**  
  Proje, encapsulation, inheritance, polymorphism ve abstraction gibi temel yazılım tasarım prensiplerinin uygulanmasıyla, geliştiricilere sağlam bir nesneye yönelik mimari örneği sunar.

- **Dinamik Oyun Mekanikleri:**  
  - Her oyuncu başlangıçta 6 karttan oluşan bir deste ile başlar.
  - Kartlar; hava, kara ve deniz kategorilerine ayrılmış olup, her kategoriye özgü avantajlar içerir (örneğin; kara kartları denize karşı, hava kartları kara kartlarına karşı avantajlıdır).
  - Her hamlede kartlar karşılaştırılarak saldırı değerleri, dayanıklılık ve seviye puanları hesaplanır.
  - Oyun, belirlenen maksimum hamle sayısına ulaşılması veya bir tarafın kartlarının tükenmesi durumunda sona erer.

- **Grafiksel ve Etkileşimli Arayüz:**  
  Java Swing kullanılarak oluşturulan kullanıcı arayüzü, kartların görsel olarak temsilini ve oyuncu ile bilgisayar arasındaki etkileşimi kolaylaştırır. Fare olaylarıyla kart seçimi, dinamik panel güncellemeleri ve anlık skor takibi, oyuna gerçekçi bir deneyim kazandırır.

- **Loglama ve İzlenebilirlik:**  
  Oyun boyunca gerçekleşen tüm hamleler, saldırı hesaplamaları ve sonuçlar detaylı bir log dosyasına kaydedilir. Bu özellik, oyunun şeffaflığını artırır ve geliştiricilerin performans analizi yapmasına olanak tanır.

---

## Projenin Çalışma Şekli

1. **Başlangıç Ayarları:**
   - Oyuncu ve bilgisayar, oyun başında her biri 6 karttan oluşan bir deste ile başlar.
   - Kartlar, hava, kara ve deniz kategorilerine ayrılır; her kategori kendi özel avantaj kurallarını içerir.
   
2. **Hamle Yönetimi:**
   - Her turda, oyuncu ve bilgisayar kartlarından üç tanesini seçer.
   - Seçilen kartlar, saldırı ve savunma değerlerine göre karşılaştırılır.
   - Avantaj durumları (örneğin, hava kartının kara kartına karşı avantajı) hesaplamalara dahil edilir.
   - Kartların dayanıklılığı güncellenir; dayanıklılığı sıfıra düşen kartlar elenir ve yerine yeni kartlar eklenir.
   
3. **Oyun Sonu ve Puanlama:**
   - Oyun, maksimum hamle sayısına ulaşıldığında veya oyunculardan birinin kartları tükendiğinde sona erer.
   - Sonuç ekranında, her oyuncunun skorları, hamle detayları ve genel performansı gösterilir.
   - Stratejik hamleler, doğru tahminler ve kalan süreye göre ek puanlar hesaplanarak galip belirlenir.

4. **Etkileşim ve Geri Bildirim:**
   - Kullanıcı arayüzü, oyuncunun hamlelerini görsel olarak destekleyen renk kodlamaları (örneğin, yeşil ve sarı renklerle) ile zenginleştirilmiştir.
   - Oyun sırasında, oyuncu rakibin durumunu görmek için “Rakibi Gör” seçeneğiyle güncel bilgileri görüntüleyebilir.

---

## Geliştirme Ortamı ve Başlatma

1. **IDE ve Araçlar:**
   - Proje, Java kullanılarak geliştirilmiş olup, Android Studio, IntelliJ IDEA veya Eclipse gibi popüler Java IDE'lerinden biri tercih edilmiştir.
   
2. **Projeyi Açma:**
   - IDE'nizde "File > Open" veya "File > Open Folder..." seçeneğini kullanarak proje klasörünü açın.
   
3. **Bağımlılıklar:**
   - Proje, Java Swing ve ilgili standart kütüphaneler ile geliştirilmiştir. Maven veya Gradle yapılandırma dosyalarındaki bağımlılıkların eksiksiz yüklendiğinden emin olun.

4. **Projeyi Çalıştırma:**
   - IDE üzerinden "Run" veya "Debug" seçeneklerini kullanarak uygulamayı başlatın.
   - Uygulama, varsayılan olarak belirlenen port veya çalışma ayarlarıyla (örneğin, masaüstü uygulaması olarak) çalışacaktır.
   - Oyun ana ekranı açıldığında, kullanıcı giriş işlemi ve oyun odasına katılım seçenekleri sunulacaktır.

---

## Kullanım

- **Oyun Başlatma:**
  - Kullanıcı, ana ekranda kendisine sunulan oyun modlarından birini seçer (örneğin, 4, 5, 6 veya 7 harfli kelime seçenekleri).
  - Seçilen mod doğrultusunda oyun odasına yönlendirilir ve karşılıklı olarak oyuncu ile bilgisayar arasında oyun başlatılır.

- **Oyun Süreci:**
  - Her turda, oyuncu ve bilgisayar kart seçimi yapar, seçilen kartlar karşılaştırılır ve hesaplamalar yapılır.
  - Skorlar ve güncel kart durumları anlık olarak arayüzde gösterilir.
  - Tüm hamleler ve sonuçlar, sistem log dosyasına kaydedilir.

- **Oyun Sonu:**
  - Oyun, maksimum hamle sayısına veya oyunculardan birinin kartlarının tükenmesine bağlı olarak sona erer.
  - Sonuç ekranında, her iki oyuncunun performansı, skorları ve kazanma nedenleri detaylı olarak sunulur.
  - Kullanıcı, oyun sonrasında yeni bir maç başlatma veya ana menüye dönme seçeneğine sahiptir.


