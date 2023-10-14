package apap.ti.silogistik2006596705.service;

import apap.ti.silogistik2006596705.model.Gudang;
import apap.ti.silogistik2006596705.model.GudangBarang;
import apap.ti.silogistik2006596705.model.Barang;

import java.util.List;

public interface GudangBarangService {
    // delete gudang_barang by gudang id
    void deleteByGudangId(Gudang gudangId);

    // find all gudang_barang by barang sku
    List<GudangBarang> findGudangBarangByBarangSku(Barang barangSku);
}
