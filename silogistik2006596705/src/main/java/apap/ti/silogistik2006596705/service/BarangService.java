package apap.ti.silogistik2006596705.service;

import apap.ti.silogistik2006596705.model.Barang;

import java.util.List;

public interface BarangService {
    // save Barang in db via jpa
    void saveBarang(Barang barang);

    // create sku
    String createSKU(int tipeBarang);

    // count rows of Barang via jpa
    Long countBarang();

    // get list of barangs via jpa
    List<Barang> getAllBarang();
}
