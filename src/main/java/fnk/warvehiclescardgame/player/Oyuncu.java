package fnk.warvehiclescardgame.player;

import fnk.warvehiclescardgame.model.SavasAraclari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static fnk.warvehiclescardgame.Oyun.*;

public class Oyuncu {
    private int oyuncuID;
    private String oyuncuAdi;
    private int skor;
    private List<SavasAraclari> kartListesi = new ArrayList<>();// 6 adet (elimizdeki kartlar)
    private List<SavasAraclari> secilenKartlar = new ArrayList<>();//önceden seçilenler
    private List<SavasAraclari> oynananKartlar = new ArrayList<>(); // 3 adet

    private List<JLabel> kartListesiLabel = new ArrayList<>();// 6 adet (elimizdeki kartlar)
    private List<JLabel> secilenKartlarLabel = new ArrayList<>();//önceden seçilenler
    private List<JLabel> oynananKartlarLabel = new ArrayList<>(); // 3 adet


    public Oyuncu() {
    }

    public Oyuncu(int oyuncuID, String oyuncuAdi, int skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = skor;
    }

    public boolean kartSec(int kartNo) {

        if (this.oyuncuID == 0) {//insan
            // Tüm kartlar seçildiyse listeyi sıfırla
            resetKartListesi();

            int secim = alKullaniciSecimi(kartNo);
            if (secim >= 0) {
                secilenKartlar.add(kartListesi.get(secim));
                oynananKartlar.add(kartListesi.get(secim));
                secilenKartlarLabel.add(kartListesiLabel.get(secim));
                //oynananKartlarLabel.add(kartListesiLabel.get(secim));
                return true;
            }
            return false;


        } else {//bilgisayar
            Random rand = new Random();
            while (oynananKartlar.size() < 3) {

                resetKartListesi();
                SavasAraclari kart;
                JLabel kartLabel;
                do {
                    int kartNumarasi = rand.nextInt(kartListesi.size());
                    kart = kartListesi.get(kartNumarasi);
                    kartLabel = kartListesiLabel.get(kartNumarasi);
                } while (secilenKartlar.contains(kart) || oynananKartlar.contains(kart));

                if (!secilenKartlar.contains(kart) && !oynananKartlar.contains(kart)) {
                    secilenKartlar.add(kart);
                    oynananKartlar.add(kart);
                    secilenKartlarLabel.add(kartLabel);
                    oynananKartlarLabel.add(kartLabel);
                }
            }
            System.out.println("\nBilgisayarın Seçtiği Kartlar:");
            for (SavasAraclari kart : oynananKartlar) {
                System.out.println("- " + kart.getClass().getSimpleName());
            }
            for (JLabel kart : oynananKartlarLabel) {
                System.out.println("-*** " + kart.getText());
            }

            logger("\nBilgisayar kartlarını rastgele seçti.");
            return true;
        }
    }

    public void kartlariGoster() {

        System.out.println("\n==== Oyuncu Kart Seçimi ====");
        getOynananKartlar().clear();
        getOynananKartlarLabel().clear();

        kartListesiLabel.clear();
        int index = 0; // Kart sıralaması 1'den başlıyor

        for (SavasAraclari kart : kartListesi) {
            JLabel kartLabel = new JLabel(kart.getClass().getSimpleName());
            kartLabel.setHorizontalAlignment(SwingConstants.CENTER);
            kartLabel.setVerticalAlignment(SwingConstants.CENTER);

            // Görsellik ekle
            kartLabel.setOpaque(true);
            kartLabel.setBackground(Color.black); // Kartın arka plan rengi
            kartLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Kenarlık
            kartLabel.setPreferredSize(new Dimension(100, 150)); // Boyutlar
            kartLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
// Arka plan resmi belirle
            String arkaPlanResmi = arkaPlanResmiBelirle(kart.getClass().getSimpleName());
            if (arkaPlanResmi != null) {
                kartLabel.setIcon(resizeIcon(arkaPlanResmi, 100, 150));
            } else {
                // Eğer arka plan resmi bulunamazsa varsayılan renk
                kartLabel.setBackground(Color.black);
            }
            int currentIndex = index; // Sıra numarasını sabitle
            if (oyuncuID == 0) {
                kartLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        kartLabel.setBackground(Color.YELLOW); // Kart üzerine gelince rengi değiştir
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        kartLabel.setBackground(Color.black); // Karttan çıkınca rengi geri değiştir
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (kartSec(currentIndex)) {
                            // Kart seçildiğinde işlem yap
                            JLabel secilenKartLabel = new JLabel(kart.getClass().getSimpleName());
                            secilenKartLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            secilenKartLabel.setVerticalAlignment(SwingConstants.CENTER);
                            secilenKartLabel.setOpaque(true);
                            secilenKartLabel.setBackground(Color.black);
                            secilenKartLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            secilenKartLabel.setPreferredSize(new Dimension(100, 150));
                            // Arka plan resmi belirle
                            String arkaPlanResmi = arkaPlanResmiBelirle(kart.getClass().getSimpleName());
                            if (arkaPlanResmi != null) {
                                secilenKartLabel.setIcon(resizeIcon(arkaPlanResmi, 100, 150));
                            } else {
                                // Eğer arka plan resmi bulunamazsa varsayılan renk
                                secilenKartLabel.setBackground(Color.black);
                            }
                            oynananKartlarLabel.add(secilenKartLabel);
                            oynananKartlarPanel.add(secilenKartLabel);
                            oynananKartlarPanel.revalidate();
                            oynananKartlarPanel.repaint();
                        }
                        if (oynananKartlarLabel.size() >= 3) {

                            savasButton.setVisible(true);

                        }

                    }
                });
            }
            index++;
            kartListesiLabel.add(kartLabel);
        }

        if (oyuncuID == 1) {
            kartSec(-1);
            for (int i = 0; i < oynananKartlar.size(); i++) {
                oynananKartlarLabel.get(i).setCursor(null);
                bilgisayarKartlariPanel.add(oynananKartlarLabel.get(i));
            }
        }

        bilgisayarKartlariPanel.revalidate();
        bilgisayarKartlariPanel.repaint();
        System.out.println(oyuncuAdi + " Kartları:");
        for (SavasAraclari kart : kartListesi) {
            System.out.println("- " + kart.getClass().getSimpleName());
        }
    }
    public void skorGoster() {
        logger(oyuncuAdi + " Skor: " + skor);
        JLabel label = new JLabel(" Skor: " + skor + " Puan");
        JPanel targetPanel = (oyuncuID == 0) ? oyuncuPanel : bilgisayarPanel;
        targetPanel.add(label, BorderLayout.WEST);
    }

    //eklenen metodlar
    private int alKullaniciSecimi(int kartNo) {
        int secim;

        //System.out.print("Bir kart numarası seçin (1-" + kartListesi.size() + "): ");
        secim = kartNo;
        if (oynananKartlar.size() >= 3) {
            JOptionPane.showMessageDialog(null, "En fazla 3 kart seçilebilir!", "Uyarı", JOptionPane.WARNING_MESSAGE);
            savasButton.setVisible(false);
        } else if (secilenKartlar.contains(kartListesi.get(secim))) {
            JOptionPane.showMessageDialog(null, "Bu kart daha önce seçildi! Lütfen başka bir kart seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            savasButton.setVisible(false);
        } else if (oynananKartlar.contains(kartListesi.get(secim))) {
            JOptionPane.showMessageDialog(null, "Bu kart zaten seçildi! Lütfen başka bir kart seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            savasButton.setVisible(false);
        } else {
            return secim;
        }
        return -1;
    }

    private void resetKartListesi() {
        if (secilenKartlar.size() == kartListesi.size()) {
            logger("Tüm kartlar seçildi, liste sıfırlanıyor.");
            secilenKartlar.clear();
            secilenKartlarLabel.clear();
        }
    }

    public void kartEkle(SavasAraclari kart) {
        kartListesi.add(kart);
    }

    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Resmi yeniden boyutlandır
        return new ImageIcon(resizedImage);
    }


    public int getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }


    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public List<SavasAraclari> getKartListesi() {
        return kartListesi;
    }

    public List<SavasAraclari> getSecilenKartlar() {
        return secilenKartlar;
    }

    public List<SavasAraclari> getOynananKartlar() {
        return oynananKartlar;
    }


    public List<JLabel> getKartListesiLabel() {
        return kartListesiLabel;
    }

    public List<JLabel> getSecilenKartlarLabel() {
        return secilenKartlarLabel;
    }

    public List<JLabel> getOynananKartlarLabel() {
        return oynananKartlarLabel;
    }
}
