package apap.ti.silogistik2006596705.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2006596705.model.Karyawan;
import apap.ti.silogistik2006596705.repository.KaryawanDb;

@Service
public class KaryawanServiceImpl implements KaryawanService {
    // jpa repo
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void saveKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }

}
