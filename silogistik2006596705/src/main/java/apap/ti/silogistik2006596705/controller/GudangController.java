package apap.ti.silogistik2006596705.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import apap.ti.silogistik2006596705.service.BarangService;
import apap.ti.silogistik2006596705.service.GudangBarangService;
import apap.ti.silogistik2006596705.service.GudangService;
import apap.ti.silogistik2006596705.service.KaryawanService;
import apap.ti.silogistik2006596705.service.PermintaanPengirimanService;
import jakarta.validation.Valid;
import apap.ti.silogistik2006596705.DTO.GudangMapper;
import apap.ti.silogistik2006596705.DTO.UpdateGudangRequestDTO;
import apap.ti.silogistik2006596705.model.Barang;
import apap.ti.silogistik2006596705.model.Gudang;
import apap.ti.silogistik2006596705.model.GudangBarang;

import java.util.List;
import java.util.ArrayList;

@Controller
public class GudangController {

    @Autowired
    private GudangService gudangService;

    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    // home page
    @GetMapping("/")
    public String home(Model model) {
        // count gudang with service
        var gudangCount = gudangService.countGudang();
        // count barang
        var barangCount = barangService.countBarang();
        // count permintaan pengiriman
        var permintaanCount = permintaanPengirimanService.countPermintaanPengiriman();
        // count karyawan
        var karyawanCount = karyawanService.countKaryawan();

        model.addAttribute("gudangCount", gudangCount);
        model.addAttribute("barangCount", barangCount);
        model.addAttribute("permintaanCount", permintaanCount);
        model.addAttribute("karyawanCount", karyawanCount);

        return "home";
    }

    // viewall gudang
    @GetMapping("/gudang")
    public String viewAllGudang(Model model) {
        // gudangService send list of gudangs from jpa
        List<Gudang> gudangs = gudangService.getAllGudang();
        model.addAttribute("gudangs", gudangs);
        return "viewall-gudang";
    }

    // restock gudang
    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockGudang(@PathVariable(value = "idGudang") Long idGudang, Model model) {
        // Get Gudang by id buat diambil datanya to present at form
        var gudang = gudangService.getGudangById(idGudang);

        // Set data in DTO (updateGudangRequestDTO)
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

        // Add vars to be rendered at thyme
        model.addAttribute("gudangDTO", gudangDTO);
        System.out.println(gudangDTO.getId());
        // Send list of existing barangs as option
        List<Barang> listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);
        model.addAttribute("gudang", gudangService.getGudangById(idGudang));

        return "restock-gudang";
    }

    // addRow: tambahBarangRow
    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = { "tambahBarangRow" })
    public String addRowTambahBarang(
            @PathVariable(value = "idGudang") Long idGudang,
            @ModelAttribute UpdateGudangRequestDTO updateGudangRequestDTO,
            Model model) {
        // Just in case kosong
        if (updateGudangRequestDTO.getListGudangBarang() == null
                || updateGudangRequestDTO.getListGudangBarang().size() == 0) {
            // Set new listGudangBarang at gudangDTO
            updateGudangRequestDTO.setListGudangBarang(new ArrayList<>());
        }

        // Insert GudangBarang kosong
        updateGudangRequestDTO.getListGudangBarang().add(new GudangBarang());

        // Kirim variables to be rendered at thyme
        model.addAttribute("gudang", gudangService.getGudangById(idGudang));
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("gudangDTO", updateGudangRequestDTO);

        return "restock-gudang";
    }

    // submit restock gudang
    @PostMapping("/gudang/{idGudang}/restock-barang")
    public String restockGudang(
            @PathVariable(value = "idGudang") Long idGudang,
            @Valid @ModelAttribute UpdateGudangRequestDTO updateGudangRequestDTO,
            BindingResult validationResult,
            Model model) {
        // DTO Validation
        if (validationResult.hasErrors()) {
            model.addAttribute("errorMessage", validationResult.getAllErrors());
            return "error-validation";
        }
        System.out.println(updateGudangRequestDTO.getListGudangBarang());
        for (GudangBarang gudangBarang : updateGudangRequestDTO.getListGudangBarang()) {
            System.out.println(gudangBarang.getGudangId());
            System.out.println(gudangBarang.getBarangSku());
        }

        // Map from DTO to object
        var gudang = gudangMapper.updateGudangRequestDTOToGudang(updateGudangRequestDTO);

        // update gudang list

        List<GudangBarang> listFromObject = gudang.getListGudangBarang();
        System.out.println(listFromObject);
        for (GudangBarang gd : listFromObject) {
            System.out.println(gd.getBarangSku().getSku());
            System.out.println(gd.getGudangId().getId());
        }

        // Call service to update object via jpa
        // gudangService.saveGudang(gudang);
        gudangService.updateGudangList(gudang);
        // Send variables to be rendered at thyme
        model.addAttribute("idGudang", gudang.getId());
        return "success-restock-gudang";
    }

    // Detail gudang
    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(
            @PathVariable(value = "idGudang") Long idGudang,
            Model model) {

        // Get gudang by id
        var gudang = gudangService.getGudangById(idGudang);

        // Kirim ke thyme
        model.addAttribute("gudang", gudang);
        return "detail-gudang";
    }

    // TODO Tampilin gudang yang memuat barang tertentu (cari barang)
    @GetMapping("/gudang/cari-barang")
    public String formCariBarang(
            @RequestParam(name = "sku", required = false) String sku,
            Model model) {

        List<GudangBarang> gudangBarangs = new ArrayList<>();
        // Get list of GudangBarang from barang with specified sku
        if (sku != null) {
            var barang = barangService.getBarangBySku(sku);
            // This will list all the gudang
            gudangBarangs = gudangBarangService.findGudangBarangByBarangSku(barang);
        }

        // Get list of barang yang bisa jadi pilihan di dropdown
        List<Barang> barangsAsc = barangService.getAllBarangAsc();
        model.addAttribute("barangs", barangsAsc);
        model.addAttribute("gudangBarangs", gudangBarangs);

        return "form-cari-barang";
    }
}
