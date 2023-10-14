package apap.ti.silogistik2006596705.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2006596705.model.Gudang;
import apap.ti.silogistik2006596705.repository.GudangBarangDb;

@Service
public class GudangBarangImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void deleteByGudangId(Gudang gudangId) {
        // TODO Auto-generated method stub
        gudangBarangDb.deleteByGudangId(gudangId);
    }

}
