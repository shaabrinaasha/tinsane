package apap.ti.silogistik2006596705.service;

import java.util.List;

import apap.ti.silogistik2006596705.model.Karyawan;

public interface KaryawanService {
    // to save Karyawan in db via jpa
    void saveKaryawan(Karyawan karyawan);

    // get all karyawan
    List<Karyawan> getAllKaryawan();

    // count karyawan
    Long countKaryawan();
}
