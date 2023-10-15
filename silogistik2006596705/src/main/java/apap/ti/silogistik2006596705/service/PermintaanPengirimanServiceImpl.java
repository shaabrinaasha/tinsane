package apap.ti.silogistik2006596705.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;
import apap.ti.silogistik2006596705.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2006596705.repository.PermintaanPengirimanDb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Override
    public String generateNomorPengiriman(PermintaanPengiriman permintaanFromDTO) {
        // TODO Auto-generated method stub
        String nomorPengiriman = "REQ";

        // Check jumlah barang yang ingin dipesan
        int sumKuantitas = 0;
        List<PermintaanPengirimanBarang> listPpb = permintaanFromDTO.getListPermintaanPengirimanBarang();
        for (PermintaanPengirimanBarang ppb : listPpb) {
            sumKuantitas += ppb.getKuantitasPesanan();
        }
        System.out.println("Sum Kuantitas: " + sumKuantitas);

        String temp;
        if (sumKuantitas > 99) {
            temp = Integer.toString(sumKuantitas);
            // https://stackoverflow.com/questions/15253406/get-the-last-three-chars-from-any-string-java
            temp = temp.substring(temp.length() - 2);
        } else {
            temp = Integer.toString(sumKuantitas);
        }

        // Check jenis layanan
        // 1 = SAM, 2 = KIL, 3 = REG, 4 = HEM
        String layanan;
        var jenisLayanan = permintaanFromDTO.getJenisLayanan();
        if (jenisLayanan == 1) {
            layanan = "SAM";
        } else if (jenisLayanan == 2) {
            layanan = "KIL";
        } else if (jenisLayanan == 3) {
            layanan = "REG";
        } else {
            layanan = "HEM";
        }

        // 8 Karakter Waktu Pembuatan Permintaan
        LocalDateTime waktuNow = LocalDateTime.now();
        // Sekalian set to permintaan
        permintaanFromDTO.setWaktuPermintaan(waktuNow);
        DateTimeFormatter waktuFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String waktuPermintaanString = waktuNow.format(waktuFormatter);

        // Join all components
        nomorPengiriman = nomorPengiriman + temp + layanan + waktuPermintaanString;

        return nomorPengiriman;
    }

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        // Set isCancelled to false first
        permintaanPengiriman.setIsCancelled(false);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public List<PermintaanPengiriman> getAllNotCancelledPermintaan() {
        return permintaanPengirimanDb.findByIsCancelledFalse();
    }

}
