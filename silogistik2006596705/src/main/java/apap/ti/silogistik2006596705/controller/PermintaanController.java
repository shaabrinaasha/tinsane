package apap.ti.silogistik2006596705.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2006596705.DTO.CreatePermintaanDTO;
import apap.ti.silogistik2006596705.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2006596705.model.Barang;
import apap.ti.silogistik2006596705.model.Karyawan;
import apap.ti.silogistik2006596705.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2006596705.service.BarangService;
import apap.ti.silogistik2006596705.service.KaryawanService;
import apap.ti.silogistik2006596705.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class PermintaanController {
    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    // TODO Tampilin form tambah permintaan pengiriman
    @GetMapping("/permintaan-pengiriman/tambah")
    public String formTambahPermintaan(Model model) {
        // TODO Kirim permintaanDTO untuk diisi at form
        var permintaanDTO = new CreatePermintaanDTO();

        // Get list of karyawan sebagai option selection
        List<Karyawan> karyawans = karyawanService.getAllKaryawan();

        model.addAttribute("permintaanDTO", permintaanDTO);
        model.addAttribute("karyawans", karyawans);
        return "form-create-permintaan";
    }

    // TODO addRow Barang at tambah permintaan
    @PostMapping(value = "/permintaan-pengiriman/tambah", params = { "tambahBarangRow" })
    public String addRowTambahPermintaan(
            @ModelAttribute CreatePermintaanDTO permintaanDTO,
            Model model) {
        // If listPPB kosong at permintaanDTO
        if (permintaanDTO.getListPermintaanPengirimanBarang() == null
                || permintaanDTO.getListPermintaanPengirimanBarang().size() == 0) {
            // Set new listPPB at permintaanDTO
            permintaanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        // Insert PermintaanPengirimanBarang kosong
        permintaanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        // Get list of existing karyawan sebagai option selection
        List<Karyawan> karyawans = karyawanService.getAllKaryawan();

        // Get list of existing barang sebagai option selection tiap tambah barang
        List<Barang> barangs = barangService.getAllBarang();

        // Kirim variables to be rendered at thyme
        model.addAttribute("permintaanDTO", permintaanDTO);
        model.addAttribute("karyawans", karyawans);
        model.addAttribute("barangs", barangs);

        return "form-create-permintaan";
    }

    // TODO Process tambah permintaan
    @PostMapping("/permintaan-pengiriman/tambah")
    public String tambahPermintaan(
            @ModelAttribute CreatePermintaanDTO permintaanDTO,
            BindingResult validationResult,
            Model model) {
        // Check isi DTO
        System.out.println(permintaanDTO.toString());
        // Check isi list PPB
        for (PermintaanPengirimanBarang ppb : permintaanDTO.getListPermintaanPengirimanBarang()) {
            System.out.println(ppb.getBarangSkuRef().getSku());
            System.out.println(ppb.getKuantitasPesanan());
        }

        // Parse tanggal string ke Date
        // https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat tanggalFormatter = new SimpleDateFormat(pattern);

        try {
            Date tanggalDate = tanggalFormatter.parse(permintaanDTO.getTanggalPengirimanString());
            permintaanDTO.setTanggalPengiriman(tanggalDate);
            System.out.print(permintaanDTO.getTanggalPengiriman());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Map permintaanDTO to Permintaan
        var permintaan = permintaanPengirimanMapper.createPermintaanDTOToPermintaan(permintaanDTO);
        // Generate permintaan id via service
        var generatedNomorPermintaan = permintaanPengirimanService.generateNomorPengiriman(permintaan);
        System.out.println(generatedNomorPermintaan);
        // Set Permintaan id
        // Save Permintaan via jpa

        return "success-create-permintaan";
    }
}
