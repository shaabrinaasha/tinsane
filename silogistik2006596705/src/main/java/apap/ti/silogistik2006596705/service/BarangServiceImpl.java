package apap.ti.silogistik2006596705.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2006596705.model.Barang;
import apap.ti.silogistik2006596705.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public String createSKU(int tipeBarang) {
        String temp;
        String generatedSKU;

        if (tipeBarang == 1) {
            generatedSKU = "ELEC";
        } else if (tipeBarang == 2) {
            generatedSKU = "CLOT";
        } else if (tipeBarang == 3) {
            generatedSKU = "FOOD";
        } else if (tipeBarang == 4) {
            generatedSKU = "COSM";
        } else {
            generatedSKU = "TOOL";
        }

        // Check the total number of current data
        var totalBarang = countBarang() + 1;
        if (countBarang() > 1000) {
            temp = Long.toString(totalBarang);
            // https://stackoverflow.com/questions/15253406/get-the-last-three-chars-from-any-string-java
            temp = temp.substring(temp.length() - 3);
        } else {
            temp = String.format("%03d", totalBarang);
        }

        // concat string
        generatedSKU += temp;
        return generatedSKU;
    }

    @Override
    public Long countBarang() {
        return barangDb.count();
    }

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public Barang getBarangBySku(String sku) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSku().equals(sku)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public Barang updateBarang(Barang barangFromDTO) {
        Barang barang = getBarangBySku(barangFromDTO.getSku());

        if (barang != null) {
            barang.setMerk(barangFromDTO.getMerk());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang);
        }

        return barang;
    }

    @Override
    public String convertTipeBarangToString(int tipeBarang) {
        String tipeBarangString;

        if (tipeBarang == 1) {
            tipeBarangString = "Elektronik";
        } else if (tipeBarang == 2) {
            tipeBarangString = "Pakaian & Aksesoris";
        } else if (tipeBarang == 3) {
            tipeBarangString = "Makanan & Minuman";
        } else if (tipeBarang == 4) {
            tipeBarangString = "Kosmetik";
        } else {
            tipeBarangString = "Perlengkapan Rumah";
        }

        return tipeBarangString;
    }

    @Override
    public int sumStokBySku(String sku) {
        return barangDb.sumStokBySku(sku);
    }
}
