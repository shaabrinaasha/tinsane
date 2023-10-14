package apap.ti.silogistik2006596705.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2006596705.model.GudangBarang;
import jakarta.transaction.Transactional;
import apap.ti.silogistik2006596705.model.Gudang;

@Transactional
@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    // delete gudang_barang yang punya id barang
    void deleteByGudangId(Gudang gudangId);
}
