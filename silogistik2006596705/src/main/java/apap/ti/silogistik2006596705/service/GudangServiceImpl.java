package apap.ti.silogistik2006596705.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2006596705.model.Gudang;
import apap.ti.silogistik2006596705.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService {
    // jpa repo
    @Autowired
    GudangDb gudangDb;

    // save gudang via jpa
    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }

}
