package apap.ti.silogistik2006596705.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ti.silogistik2006596705.model.Karyawan;
import apap.ti.silogistik2006596705.service.KaryawanService;

@Controller
public class PermintaanController {
    @Autowired
    KaryawanService karyawanService;

    // TODO Tampilin form tambah permintaan pengiriman
    @GetMapping("/permintaan-pengiriman/tambah")
    public String formTambahPermintaan(Model model) {
        // TODO Get list of karyawan sebagai option selection
        List<Karyawan> karyawans = karyawanService.getAllKaryawan();
        model.addAttribute("karyawans", karyawans);
        return "form-create-permintaan";
    }
}
