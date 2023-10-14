package apap.ti.silogistik2006596705.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2006596705.model.Barang;
import apap.ti.silogistik2006596705.model.Gudang;
import apap.ti.silogistik2006596705.model.GudangBarang;
import apap.ti.silogistik2006596705.repository.GudangBarangDb;

@Service
public class GudangBarangImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void deleteByGudangId(Gudang gudangId) {
        gudangBarangDb.deleteByGudangId(gudangId);
    }

    @Override
    public List<GudangBarang> findGudangBarangByBarangSku(Barang barangSku) {
        return gudangBarangDb.findByBarangSku(barangSku);
    }

}
