package apap.ti.silogistik2006596705.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2006596705.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {

}
