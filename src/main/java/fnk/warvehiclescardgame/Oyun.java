package fnk.warvehiclescardgame;

import fnk.warvehiclescardgame.model.*;
import fnk.warvehiclescardgame.player.Oyuncu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Random;

public class Oyun {

    private static PrintWriter writer;
    private static final int MAX_SEVIYE_PUANI = 20;
    private final int MAX_HAMLE;
    private final int seviyePuani;
    private final Oyuncu oyuncu;
    private final Oyuncu bilgisayar;
    private int hamle = 1; // Hamle sayısını global olarak tanımla
    private boolean sonHamleMi = false;

    JFrame mainFrame = new JFrame("Savaş Araçları Kart Oyunu");
    JPanel mainPanel = new JPanel();
    JPanel textPanel = new JPanel(new GridLayout(1, 3));
    public static JPanel masaVeSonucPanel = new JPanel(new BorderLayout());
    public static JPanel masaPanel = new JPanel();
    public static JPanel bilgisayarPanel = new JPanel();
    public static JPanel oyuncuPanel = new JPanel();
    public static JPanel oyuncuKartlariPanel = new JPanel();
    public static JPanel bilgisayarKartlariPanel = new JPanel();
    public static JPanel oynananKartlarPanel = new JPanel();
    public static JButton savasButton;
    JButton oyunaBaslaButton;


    public Oyun() {
        this.oyuncu = new Oyuncu(0, "Fatma", 0);
        this.bilgisayar = new Oyuncu(1, "Bilgisayar", 0);

        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        seviyePuani = kullaniciGirdisiAl("Lütfen seviye puanı bilgisi giriniz (varsayılan: 0):", 0);
        MAX_HAMLE = kullaniciGirdisiAl("Lütfen MAX hamle sayısını giriniz (varsayılan: 5):", 5);

        kartlariDagit();
        girisSayfasiniGoster();

        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true)); // Dosyayı ekleme modunda aç
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(Oyun::new);
    }
    public static void logger(String mesaj) {
        if (writer != null) {
            writer.println(mesaj);
            writer.flush(); // Hemen yazmayı gerçekleştir
        } else {
            System.out.println("Dosya yazıcısı başlatılamadı.");
        }
    }
    private int kullaniciGirdisiAl(String mesaj, int varsayilan) {
        String input = JOptionPane.showInputDialog(null, mesaj, "Giriş", JOptionPane.QUESTION_MESSAGE);
        try {
            if (input == null || input.trim().isEmpty()) {
                return varsayilan;
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Geçersiz bir giriş yaptınız. Varsayılan değer (0) atanıyor.");
            return varsayilan;
        }
    }
    private void kartlariDagit() {
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            oyuncu.kartEkle(rastgeleKartOlustur(rand, false));
            bilgisayar.kartEkle(rastgeleKartOlustur(rand, false));
        }
    }
    private void girisSayfasiniGoster() {

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/arkaplan1.png")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(Box.createVerticalGlue());

        JLabel baslik = new JLabel("SAVAŞ ARAÇLARI KART OYUNU", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 24));
        baslik.setAlignmentX(Component.CENTER_ALIGNMENT); // Ortala
        mainPanel.add(baslik);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Başlık ile buton arasında boşluk

        oyunaBaslaButton = new JButton("Başla");
        oyunaBaslaButton.setFont(new Font("Arial", Font.PLAIN, 18));
        oyunaBaslaButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Ortala
        mainPanel.add(oyunaBaslaButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Butonlar arası boşluk
        mainPanel.add(Box.createVerticalGlue());

        oyunaBaslaButton.addActionListener(e -> {
            mainFrame.dispose();
            baslat();
        });

        mainFrame.add(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
    public void baslat() {

        mainPanel.removeAll();
        mainFrame = new JFrame("Savaş Araçları Kart Oyunu");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainPanel.setLayout(new BorderLayout());

        masaVeSonucPanel = new JPanel(new BorderLayout());

        masaPanel = new JPanel(new GridLayout(2, 1));
        masaPanel.setPreferredSize(new Dimension(800, 200));
        masaPanel.setBorder(BorderFactory.createTitledBorder("Masa"));

        if (oynananKartlarPanel != null) {
            oynananKartlarPanel.removeAll();
        }
        if (bilgisayarKartlariPanel != null) {
            bilgisayarKartlariPanel.removeAll();
        }
        if (textPanel != null) {
            textPanel.removeAll();
        }

        bilgisayarPanel.removeAll();
        bilgisayarPanel = new JPanel(new BorderLayout());
        bilgisayarPanel.setPreferredSize(new Dimension(800, 200));
        bilgisayarPanel.setBorder(BorderFactory.createTitledBorder("Bilgisayar Ekranı"));

        bilgisayarKartlariPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        oyuncuPanel.removeAll();
        oyuncuPanel = new JPanel(new BorderLayout());
        oyuncuPanel.setPreferredSize(new Dimension(800, 200));
        oyuncuPanel.setBorder(BorderFactory.createTitledBorder("Oyuncu Ekranı"));

        oyuncuKartlariPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        oynananKartlarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        oyuncu.kartlariGoster();

        savasButton = new JButton("Savas");
        savasButton.setFont(new Font("Arial", Font.PLAIN, 18));
        savasButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Ortala
        savasButton.setVisible(false); // Başlangıçta görünmesin
        oyuncuPanel.add(savasButton, BorderLayout.EAST); // Butonu oyuncu paneline ekle

        savasButton.addActionListener(event -> {
            savasButton.setVisible(false);
            if (hamle <= MAX_HAMLE) {
                hamleYap(); // Hamle fonksiyonunu çağır
            } else {
                JOptionPane.showMessageDialog(null, "Maksimum hamleye ulaşıldı. Oyun sona eriyor.", "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        oyuncuKartlariPanel.removeAll();
        oyuncuKartlariPanel.setLayout(new FlowLayout());
        oynananKartlarPanel.removeAll();
        oynananKartlarPanel.setLayout(new FlowLayout());
        bilgisayarKartlariPanel.removeAll();
        bilgisayarKartlariPanel.setLayout(new FlowLayout());

        for (JLabel kartLabel : oyuncu.getKartListesiLabel()) {
            oyuncuKartlariPanel.add(kartLabel);
        }

        oyuncuKartlariPanel.revalidate();
        oyuncuKartlariPanel.repaint();

        masaPanel.add(bilgisayarKartlariPanel);
        masaPanel.add(oynananKartlarPanel);

        masaVeSonucPanel.add(masaPanel, BorderLayout.CENTER);
        oyuncuPanel.add(oyuncuKartlariPanel);

        mainPanel.add(bilgisayarPanel, BorderLayout.NORTH);
        mainPanel.add(masaVeSonucPanel, BorderLayout.CENTER);
        mainPanel.add(oyuncuPanel, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    private void hamleYap() {

        logger("\n==== Hamle " + hamle + " ====");

        if (sonHamleMi) {
            oynananKartlarPanel.removeAll();
            bilgisayarKartlariPanel.removeAll();
            oyuncuKartlariPanel.removeAll();
            textPanel.removeAll();
            oyuncu.getOynananKartlar().clear();
            bilgisayar.getOynananKartlar().clear();
            oyuncu.getOynananKartlarLabel().clear();
            bilgisayar.getOynananKartlarLabel().clear();

            for (int i = 0; i < 3 && i < oyuncu.getKartListesi().size(); i++) {
                SavasAraclari kart = oyuncu.getKartListesi().get(i);

                JLabel kartLabel = new JLabel(kart.getClass().getSimpleName());
                kartLabel.setHorizontalAlignment(SwingConstants.CENTER);
                kartLabel.setVerticalAlignment(SwingConstants.CENTER);
                kartLabel.setOpaque(true);
                kartLabel.setBackground(Color.black);
                kartLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                kartLabel.setPreferredSize(new Dimension(100, 150));
                // Arka plan resmi belirle
                String arkaPlanResmi = arkaPlanResmiBelirle(kart.getClass().getSimpleName());
                if (arkaPlanResmi != null) {
                    kartLabel.setIcon(resizeIcon(arkaPlanResmi, 100, 150));
                } else {
                    // Eğer arka plan resmi bulunamazsa varsayılan renk
                    kartLabel.setBackground(Color.black);
                }
                oyuncu.getOynananKartlar().add(kart);
                oyuncu.getOynananKartlarLabel().add(kartLabel);
            }

            for (int i = 0; i < 3 && i < bilgisayar.getKartListesi().size(); i++) {
                SavasAraclari kart = bilgisayar.getKartListesi().get(i);

                JLabel kartLabel = new JLabel(kart.getClass().getSimpleName());
                kartLabel.setHorizontalAlignment(SwingConstants.CENTER);
                kartLabel.setVerticalAlignment(SwingConstants.CENTER);
                kartLabel.setOpaque(true);
                kartLabel.setBackground(Color.black);
                kartLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                kartLabel.setPreferredSize(new Dimension(100, 150));
                // Arka plan resmi belirle
                String arkaPlanResmi = arkaPlanResmiBelirle(kart.getClass().getSimpleName());
                if (arkaPlanResmi != null) {
                    kartLabel.setIcon(resizeIcon(arkaPlanResmi, 100, 150));
                } else {
                    // Eğer arka plan resmi bulunamazsa varsayılan renk
                    kartLabel.setBackground(Color.black);
                }
                bilgisayar.getOynananKartlar().add(kart);
                bilgisayar.getOynananKartlarLabel().add(kartLabel);
            }

            oyunaBaslaButton.setVisible(false);
            logger("\nSon hamle için otomatik kartlar seçildi.");
        } else {
            // Normal hamlede bilgisayar kartlarını seçer
            bilgisayar.kartlariGoster();
            savasButton.setVisible(false);
        }

        for (int i = 0; i < 3; i++) {
            JTextArea sonucTextArea = new JTextArea();
            sonucTextArea.setEditable(false); // Kullanıcı metni düzenleyemesin
            sonucTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Yazı tipi ve boyut
            sonucTextArea.setBorder(BorderFactory.createTitledBorder("KARŞILAŞMA " + (i + 1)));

            logger("\nKARŞILAŞMA " + (i + 1));
            SavasAraclari oyuncuKart = oyuncu.getOynananKartlar().get(i);
            SavasAraclari bilgisayarKart = bilgisayar.getOynananKartlar().get(i);

            JLabel oyuncuKartLabel = oyuncu.getOynananKartlarLabel().get(i);
            JLabel bilgisayarKartLabel = bilgisayar.getOynananKartlarLabel().get(i);

            oyuncuKartLabel.setBackground(Color.GREEN);
            bilgisayarKartLabel.setBackground(Color.GREEN);

            System.out.println("Oyuncu Kartı:** " + oyuncuKartLabel.getText());
            System.out.println("Bilgisayar Kartı:** " + bilgisayarKartLabel.getText());
            logger("Oyuncu Kartı: " + oyuncuKart.getClass().getSimpleName());
            logger("Bilgisayar Kartı: " + bilgisayarKart.getClass().getSimpleName());
            sonucTextArea.append("\nOyuncu Kartı: " + oyuncuKart.getClass().getSimpleName() + "\n");
            sonucTextArea.append("Bilgisayar Kartı: " + bilgisayarKart.getClass().getSimpleName() + "\n");

            savasSonuc(oyuncuKart, bilgisayarKart, oyuncuKartLabel, bilgisayarKartLabel, sonucTextArea);
        }

        if ((oyuncu.getKartListesi().size() <= 1 || bilgisayar.getKartListesi().size() <= 1) && !sonHamleMi) {
            logger("\nBir tarafın kart sayısı 1'e düştü. Kartlar tamamlanıyor ve son hamle oynanıyor...");
            JOptionPane.showMessageDialog(null, "Bir tarafın kart sayısı 1'e düştü. Kartlar tamamlanıyor ve son hamle oynanıyor...", "Uyarı", JOptionPane.WARNING_MESSAGE);
            Random rand = new Random();
            sonHamleMi = true;
            // Kart sayısı 1'e düştüğünde fazladan kart ver ve oyunu bitir
            while (oyuncu.getKartListesi().size() < 3) {
                oyuncu.kartEkle(rastgeleKartOlustur(rand, oyuncu.getSkor() >= 20));
            }
            while (bilgisayar.getKartListesi().size() < 3) {
                bilgisayar.kartEkle(rastgeleKartOlustur(rand, bilgisayar.getSkor() >= MAX_SEVIYE_PUANI));
            }
            hamleYap();
            return;
        }
        if (!sonHamleMi) {
            Random rand = new Random();
            oyuncu.kartEkle(rastgeleKartOlustur(rand, oyuncu.getSkor() >= 20));
            bilgisayar.kartEkle(rastgeleKartOlustur(rand, bilgisayar.getSkor() >= 20));
            logger("\nOyuncuya ve bilgisayara rastgele birer kart verildi.");
        }
        oyuncu.skorGoster();
        bilgisayar.skorGoster();

        oyuncuKartlariPanel.removeAll();
        oynananKartlarPanel.removeAll();
        bilgisayarKartlariPanel.removeAll();

        if (!sonHamleMi) {
            for (JLabel kartLabel : oyuncu.getKartListesiLabel()) {
                oyuncuKartlariPanel.add(kartLabel);
            }
        }
        for (JLabel kartLabel : oyuncu.getOynananKartlarLabel()) {
            oynananKartlarPanel.add(kartLabel);
        }
        for (JLabel kartLabel : bilgisayar.getOynananKartlarLabel()) {
            bilgisayarKartlariPanel.add(kartLabel);
        }

        oyuncuKartlariPanel.revalidate();
        oyuncuKartlariPanel.repaint();
        oynananKartlarPanel.revalidate();
        oynananKartlarPanel.repaint();
        bilgisayarKartlariPanel.revalidate();
        bilgisayarKartlariPanel.repaint();

        oyuncuKartlariPanel.setLayout(new FlowLayout()); // Kartları yatayda sıralamak için FlowLayout kullan
        oynananKartlarPanel.setLayout(new FlowLayout());
        bilgisayarKartlariPanel.setLayout(new FlowLayout());

        oyunaBaslaButton.setText("Sonraki Hamle");
        oyuncuPanel.add(oyunaBaslaButton, BorderLayout.EAST); // Butonu oyuncu paneline ekle

        masaVeSonucPanel.revalidate();
        masaVeSonucPanel.repaint();

        if (hamle == MAX_HAMLE) {
            JOptionPane.showMessageDialog(null, "Maksimum hamle sayısına ulaşıldı. Oyun sona eriyor.", "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
            oyunuSonlandir();
            return;
        } else if (sonHamleMi) {
            JOptionPane.showMessageDialog(null, "Son hamle oynandı. Oyun sona eriyor.", "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
            oyunuSonlandir();
            return;
        } else if (oyuncu.getKartListesi().size() == 0 || bilgisayar.getKartListesi().size() == 0) {
            JOptionPane.showMessageDialog(null, "Bir tarafın kartları tamamen tükendi. Oyun sona eriyor.", "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
            oyunuSonlandir();
            return;
        }
        hamle++;
    }

    private SavasAraclari rastgeleKartOlustur(Random rand, boolean ekAraclarDahil) {
        // Ek araçlar dahil ise seçim havuzunu genişlet
        int maxKartTuru = ekAraclarDahil ? 6 : 3; // 3 temel araç, 6 toplam (temel + ek)
        int kartTuru = rand.nextInt(maxKartTuru); // 0 - (maxKartTuru - 1) arasında değer üretir

        return switch (kartTuru) {
            case 0 -> new Ucak(seviyePuani, 20, 10, "Hava", "Ucak", 10);
            case 1 -> new Obus(seviyePuani, 20, 10, "Kara", "Obus", 5);
            case 2 -> new Firkateyn(seviyePuani, 25, 10, "Deniz", "Firkateyn", 5);
            case 3 -> new Siha(seviyePuani, 15, 10, "Hava", "Siha", 10, 10); // Ek araç
            case 4 -> new Sida(seviyePuani, 15, 10, "Deniz", "Sida", 10, 10); // Ek araç
            case 5 -> new KFS(seviyePuani, 10, 10, "Kara", "KFS", 10, 20); // Ek araç
            default -> throw new IllegalStateException("Geçersiz kart türü: " + kartTuru);
        };
    }

    public static String arkaPlanResmiBelirle(String kartAdi) {
        switch (kartAdi) {
            case "Obus":
                return "/images/obus.png";
            case "Ucak":
                return "/images/ucak.png";
            case "Firkateyn":
                return "/images/firkateyn.png";
            case "Siha":
                return "/images/siha.png";
            case "Sida":
                return "/images/sida.png";
            case "KFS":
                return "/images/kfs.png";
            default:
                return null; // Resim yoksa null döner
        }
    }

    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Resmi yeniden boyutlandır
        return new ImageIcon(resizedImage);
    }


    private int saldiriHesapla(SavasAraclari saldiranKart, SavasAraclari savunanKart, JTextArea textArea) {
        int hasar = saldiranKart.getVurus();

        // Kara araçları için avantaj kontrolü
        switch (saldiranKart.getSinif()) {
            case "Kara" -> {
                if (savunanKart.getSinif().equals("Deniz")) {
                    hasar += ((KaraAraclari) saldiranKart).getDenizVurusAvantaji();
                    textArea.append(((KaraAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((KaraAraclari) saldiranKart).getDenizVurusAvantaji() +"\n");
                    logger(((KaraAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((KaraAraclari) saldiranKart).getDenizVurusAvantaji());
                } else if (savunanKart.getSinif().equals("Hava") && ((KaraAraclari) saldiranKart).getAltSinif().equals("KFS")) {
                    hasar += ((KFS) saldiranKart).getHavaVurusAvantaji();
                    textArea.append("KFS avantajlı! Ekstra hasar: " + ((KFS) saldiranKart).getHavaVurusAvantaji()+"\n");
                    logger("KFS avantajlı! Ekstra hasar: " + ((KFS) saldiranKart).getHavaVurusAvantaji());
                } else {
                    //System.out.println("Kara aracı avantaj yok.");
                }
            }
            // Hava araçları için avantaj kontrolü
            case "Hava" -> {
                if (savunanKart.getSinif().equals("Kara")) {
                    hasar += ((HavaAraclari) saldiranKart).getKaraVurusAvantaji();
                    textArea.append(((HavaAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((HavaAraclari) saldiranKart).getKaraVurusAvantaji()+"\n");
                    logger(((HavaAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((HavaAraclari) saldiranKart).getKaraVurusAvantaji());
                } else if (savunanKart.getSinif().equals("Deniz") && ((HavaAraclari) saldiranKart).getAltSinif().equals("Siha")) {
                    hasar += ((Siha) saldiranKart).getDenizVurusAvantaji();
                    textArea.append("Siha avantajlı! Ekstra hasar: " + ((Siha) saldiranKart).getDenizVurusAvantaji()+"\n");
                    logger("Siha avantajlı! Ekstra hasar: " + ((Siha) saldiranKart).getDenizVurusAvantaji());
                } else {
                    //System.out.println("Hava aracı avantaj yok.");
                }
            }
            // Deniz araçları için avantaj kontrolü
            case "Deniz" -> {
                if (savunanKart.getSinif().equals("Hava")) {
                    hasar += ((DenizAraclari) saldiranKart).getHavaVurusAvantaji();
                    textArea.append(((DenizAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((DenizAraclari) saldiranKart).getHavaVurusAvantaji()+"\n");
                    logger(((DenizAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((DenizAraclari) saldiranKart).getHavaVurusAvantaji());
                } else if (savunanKart.getSinif().equals("Kara") && ((DenizAraclari) saldiranKart).getAltSinif().equals("Sida")) {
                    hasar += ((Sida) saldiranKart).getKaraVurusAvantaji();
                    textArea.append("Sida avantajlı! Ekstra hasar: " + ((Sida) saldiranKart).getKaraVurusAvantaji()+"\n");
                    logger("Sida avantajlı! Ekstra hasar: " + ((Sida) saldiranKart).getKaraVurusAvantaji());
                } else {
                    //System.out.println("Deniz aracı avantaj yok.");
                }
            }
        }

        return hasar;
    }

    public void savasSonuc(SavasAraclari oyuncuKart, SavasAraclari bilgisayarKart, JLabel oyuncuKartLabel, JLabel bilgisayarKartLabel, JTextArea textArea) {

        int oyuncuVurus = saldiriHesapla(oyuncuKart, bilgisayarKart, textArea);
        int bilgisayarVurus = saldiriHesapla(bilgisayarKart, oyuncuKart, textArea);

       logger("oyuncuVurus =" + oyuncuVurus);
        logger("bilgisayarVurus =" + bilgisayarVurus);

        textArea.append("\nOyuncu Vuruş: " + oyuncuVurus + "\n" + "Bilgisayar Vuruş: " + bilgisayarVurus + "\n");
        JScrollPane scrollPane = new JScrollPane(textArea);

        oyuncuKart.durumGuncelle(bilgisayarVurus, 0);
        bilgisayarKart.durumGuncelle(oyuncuVurus, 0);

        // Oyuncu kartı dayanıklılık kontrolü
        if (oyuncuKart.getDayaniklilik() <= 0) {
            logger("Oyuncunun \"" + oyuncuKart.getClass().getSimpleName() + "\" kartı elendi!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            textArea.append("\nOyuncunun \"" + oyuncuKart.getClass().getSimpleName() + "\" kartı elendi!\n");
            int puanArtisi = Math.max(10, oyuncuKart.getSeviyePuani());
            bilgisayarKart.durumGuncelle(0, puanArtisi);
            bilgisayar.setSkor(bilgisayar.getSkor() + puanArtisi);
            oyuncu.getKartListesi().remove(oyuncuKart); // Oyuncunun elenen kartını listeden kaldır
            oyuncu.getSecilenKartlar().remove(oyuncuKart); // Oyuncunun elenen kartını listeden kaldır
            oyuncuKartLabel.setBackground(Color.red);
            oyuncu.getKartListesiLabel().remove(oyuncuKartLabel); // Oyuncunun elenen kartını listeden kaldır
            oyuncu.getSecilenKartlarLabel().remove(oyuncuKartLabel); // Oyuncunun elenen kartını listeden kaldır
            logger("Bilgisayarın skoru " + puanArtisi + " puan arttı!\n");
            textArea.append("\nBilgisayarın skoru " + puanArtisi + " puan arttı!\n");

        } else {
            logger("Oyuncu-------------------------");
            textArea.append("\nOyuncu Kartı\n");
            oyuncuKart.kartPuaniGoster(textArea);
        }

        // Bilgisayar kartı dayanıklılık kontrolü
        if (bilgisayarKart.getDayaniklilik() <= 0) {
            logger("Bilgisayarın \"" + bilgisayarKart.getClass().getSimpleName() + "\" kartı elendi!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            textArea.append("\nBilgisayarın \"" + bilgisayarKart.getClass().getSimpleName() + "\" kartı elendi!\n");
            int puanArtisi = Math.max(10, bilgisayarKart.getSeviyePuani());
            oyuncuKart.durumGuncelle(0, puanArtisi);
            oyuncu.setSkor(oyuncu.getSkor() + puanArtisi);
            bilgisayar.getKartListesi().remove(bilgisayarKart); // Bilgisayarın elenen kartını listeden kaldır
            bilgisayar.getSecilenKartlar().remove(bilgisayarKart); // Oyuncunun elenen kartını listeden kaldır
            bilgisayarKartLabel.setBackground(Color.red);
            bilgisayar.getKartListesiLabel().remove(bilgisayarKartLabel); // Bilgisayarın elenen kartını listeden kaldır
            bilgisayar.getSecilenKartlarLabel().remove(bilgisayarKartLabel); // Oyuncunun elenen kartını listeden kaldır
            logger("Oyuncunun skoru " + puanArtisi + " puan arttı!");
            textArea.append("\nOyuncunun skoru " + puanArtisi + " puan arttı!\n");
        } else {
            logger("Bilgisayar----------------------");
            textArea.append("\nBilgisayar Kartı\n");
            bilgisayarKart.kartPuaniGoster(textArea);
        }

        textPanel.add(scrollPane);
        masaVeSonucPanel.add(textPanel, BorderLayout.EAST);
        masaVeSonucPanel.revalidate();
        masaVeSonucPanel.repaint();
    }

    private void oyunuSonlandir() {
        logger("Oyun sona erdi.");
       logger("Son skorlar:");
        oyuncu.skorGoster();
        bilgisayar.skorGoster();
        oyunaBaslaButton.setVisible(false);
        //mainPanel.setEnabled(false); // Paneldeki tüm bileşenler de devre dışı kalır.

        JFrame sonucFrame = new JFrame("Oyun Sonucu");
        sonucFrame.setSize(400, 300);
        sonucFrame.setLocationRelativeTo(null); // Ortada açılır
        sonucFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel sonucPanel = new JPanel();
        sonucPanel.setLayout(new BoxLayout(sonucPanel, BoxLayout.Y_AXIS));
        sonucPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel baslikLabel = new JLabel("Oyun Sonuçları");
        baslikLabel.setFont(new Font("Arial", Font.BOLD, 20));
        baslikLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sonucPanel.add(baslikLabel);

        sonucPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel oyuncuSkorLabel = new JLabel("Oyuncu Skoru: " + oyuncu.getSkor());
        oyuncuSkorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        oyuncuSkorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sonucPanel.add(oyuncuSkorLabel);

        JLabel bilgisayarSkorLabel = new JLabel("Bilgisayar Skoru: " + bilgisayar.getSkor());
        bilgisayarSkorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bilgisayarSkorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sonucPanel.add(bilgisayarSkorLabel);

        sonucPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        String kazananMesaj;
        if (oyuncu.getKartListesi().isEmpty() && !bilgisayar.getKartListesi().isEmpty()) {
            kazananMesaj = " Oyuncunun kartları tükendi.\nBilgisayar kazandı!";
        } else if (bilgisayar.getKartListesi().isEmpty() && !oyuncu.getKartListesi().isEmpty()) {
            kazananMesaj = "Bilgisayarın kartları tükendi.\nOyuncu kazandı! ";
        } else if (oyuncu.getSkor() > bilgisayar.getSkor()) {
            kazananMesaj = "Oyuncu kazandı!";
        } else if (bilgisayar.getSkor() > oyuncu.getSkor()) {
            kazananMesaj = "Bilgisayar kazandı!";
        } else {
            int oyuncuToplamDayaniklilik = oyuncu.getKartListesi().stream()
                    .mapToInt(SavasAraclari::getDayaniklilik)
                    .sum();
            int bilgisayarToplamDayaniklilik = bilgisayar.getKartListesi().stream()
                    .mapToInt(SavasAraclari::getDayaniklilik)
                    .sum();

            kazananMesaj = "Berabere!";

            if (oyuncuToplamDayaniklilik > bilgisayarToplamDayaniklilik) {
                int fark = oyuncuToplamDayaniklilik - bilgisayarToplamDayaniklilik;
                oyuncu.setSkor(oyuncu.getSkor() + fark);
                kazananMesaj = "Dayanıklılık farkı " + fark + " puan skora eklendi.\nOyuncu kazandı!";
                oyuncuSkorLabel.setText("Oyuncu Skoru: " + oyuncu.getSkor());
            } else if (bilgisayarToplamDayaniklilik > oyuncuToplamDayaniklilik) {
                int fark = bilgisayarToplamDayaniklilik - oyuncuToplamDayaniklilik;
                bilgisayar.setSkor(bilgisayar.getSkor() + fark);
                kazananMesaj = "Dayanıklılık farkı " + fark + " puan skora eklendi.\nBilgisayar kazandı!";
                bilgisayarSkorLabel.setText("Bilgisayar Skoru: " + bilgisayar.getSkor());
            }
        }

        JLabel kazananLabel = new JLabel(kazananMesaj);
        kazananLabel.setFont(new Font("Arial", Font.BOLD, 16));
        kazananLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sonucPanel.add(kazananLabel);

        sonucPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton kapatButton = new JButton("Kapat");
        kapatButton.setFont(new Font("Arial", Font.PLAIN, 14));
        kapatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        kapatButton.addActionListener(e -> sonucFrame.dispose());
        sonucPanel.add(kapatButton);

        sonucFrame.add(sonucPanel);
        sonucFrame.setVisible(true);

        writer.close();
    }
}
