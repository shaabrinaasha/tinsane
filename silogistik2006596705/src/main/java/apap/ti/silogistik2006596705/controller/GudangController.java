package apap.ti.silogistik2006596705.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import apap.ti.silogistik2006596705.service.BarangService;
import apap.ti.silogistik2006596705.service.GudangService;
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

    // home page
    @GetMapping("/")
    public String home(Model model) {
        // count gudang with service
        var gudangCount = gudangService.countGudang();
        // TODO count barang
        var barangCount = barangService.countBarang();
        // TODO count permintaan pengiriman
        // TODO count karyawan
        model.addAttribute("gudangCount", gudangCount);
        model.addAttribute("barangCount", barangCount);
        return "home";
    }

    // viewall gudang
    @GetMapping("/gudang")
    public String viewAllGudang(Model model) {
        // TODO gudangService send list of gudangs from jpa
        List<Gudang> gudangs = gudangService.getAllGudang();
        model.addAttribute("gudangs", gudangs);
        return "viewall-gudang";
    }

    // restock gudang
    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockGudang(@PathVariable(value = "idGudang") Long idGudang, Model model) {
        // TODO Get Gudang by id buat diambil datanya to present at form
        var gudang = gudangService.getGudangById(idGudang);

        // Set data in DTO (updateGudangRequestDTO)
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

        // Add vars to be rendered at thyme
        model.addAttribute("gudangDTO", gudangDTO);
        // Send list of existing barangs as option
        List<Barang> listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);
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

    // TODO submit restock gudang
    @PostMapping("/gudang/{idGudang}/restock-barang")
    public String restockGudang(
            @PathVariable(value = "idGudang") Long idGudang,
            @Valid @ModelAttribute UpdateGudangRequestDTO updateGudangRequestDTO,
            BindingResult validationResult,
            Model model) {
        // DTO Validation
        System.out.println(updateGudangRequestDTO.getListGudangBarang());
        for (GudangBarang gudangBarang : updateGudangRequestDTO.getListGudangBarang()) {
            System.out.println(gudangBarang.getGudangId());
            System.out.println(gudangBarang.getBarangSku());
        }

        // Map from DTO to object
        var gudang = gudangMapper.updateGudangRequestDTOToGudang(updateGudangRequestDTO);
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
        return "home";
    }

    // TODO Detail gudang
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
}
