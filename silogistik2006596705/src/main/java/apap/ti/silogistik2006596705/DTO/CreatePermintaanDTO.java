package apap.ti.silogistik2006596705.DTO;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2006596705.model.Karyawan;
import apap.ti.silogistik2006596705.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanDTO {
    String namaPenerima;

    String alamatPenerima;

    Date tanggalPengiriman;

    int jenisLayanan;

    int biayaPengiriman;

    Karyawan karyawanId;

    List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

    // Masukkin date dari form ke string dulu
    String tanggalPengirimanString;

    // toString method buat check isi
    @Override
    public String toString() {
        return "CreatePermintaanDTO [namaPenerima=" + namaPenerima + ", alamatPenerima=" + alamatPenerima
                + ", jenisLayanan=" + jenisLayanan + ", biayaPengiriman=" + biayaPengiriman + ", karyawan="
                + karyawanId.getIdKaryawan() + ", listPermintaanPengirimanBarang=" + listPermintaanPengirimanBarang
                + ", tanggalPengirimanString=" + tanggalPengirimanString + "]";
    }
}
