package apap.ti.silogistik2006596705.service;

import apap.ti.silogistik2006596705.model.Gudang;

import java.util.List;

public interface GudangService {
    // to save Gudang in db via jpa
    void saveGudang(Gudang gudang);

    // count rows of gudang
    Long countGudang();

    // get list of gudangs from db via jpa
    List<Gudang> getAllGudang();
}
