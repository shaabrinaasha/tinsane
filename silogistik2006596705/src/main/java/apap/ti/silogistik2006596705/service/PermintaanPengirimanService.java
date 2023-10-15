package apap.ti.silogistik2006596705.service;

import java.util.List;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    // generate nomor pengiriman
    String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);

    // save permintaan pengiriman
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    // get all pengiriman via jpa (yang ga kena soft delete)
    List<PermintaanPengiriman> getAllNotCancelledPermintaan();
}
