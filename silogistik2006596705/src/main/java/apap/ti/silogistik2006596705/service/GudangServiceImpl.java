package apap.ti.silogistik2006596705.service;

import java.util.List;

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

    // count rows of gudang via jpa
    @Override
    public Long countGudang() {
        return gudangDb.count();
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(Long id) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getId() == id) {
                return gudang;
            }
        }
        return null;
    }

}
