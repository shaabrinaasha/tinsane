package apap.ti.silogistik2006596705.service;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    // generate nomor pengiriman
    String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);

    // save permintaan pengiriman
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
}
