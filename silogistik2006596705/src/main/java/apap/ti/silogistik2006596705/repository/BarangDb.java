package apap.ti.silogistik2006596705.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2006596705.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    @Query(value = "SELECT SUM(stok) FROM gudang_barang WHERE sku_barang=?1", nativeQuery = true)
    int sumStokBySku(String sku);

    // find all barang ordered by merk
    List<Barang> findAllByOrderByMerkAsc();
}
