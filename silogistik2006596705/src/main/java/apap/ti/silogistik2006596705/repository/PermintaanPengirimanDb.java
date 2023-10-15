package apap.ti.silogistik2006596705.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {
    @Query(value = "SELECT * FROM permintaan_pengiriman WHERE is_cancelled=FALSE", nativeQuery = true)
    List<PermintaanPengiriman> findByIsCancelledFalse();
}
