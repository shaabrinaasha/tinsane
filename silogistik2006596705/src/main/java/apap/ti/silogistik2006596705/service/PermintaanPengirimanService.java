package apap.ti.silogistik2006596705.service;

import java.util.List;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    // generate nomor pengiriman
    String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);

    // save permintaan pengiriman
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    // get all permintaan via jpa (yang ga kena soft delete)
    List<PermintaanPengiriman> getAllNotCancelledPermintaan();

    // get permintaan by id
    PermintaanPengiriman getPengirimanById(Long idPengiriman);

    // get all permintaan
    List<PermintaanPengiriman> getAllPermintaan();

    // cancel if its < 24 hours from now
    void cancelByid(Long idPengiriman) throws Exception;
}
