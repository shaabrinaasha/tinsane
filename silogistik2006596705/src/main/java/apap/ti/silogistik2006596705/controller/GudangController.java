package apap.ti.silogistik2006596705.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import apap.ti.silogistik2006596705.service.BarangService;
import apap.ti.silogistik2006596705.service.GudangService;
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
        model.addAttribute("gudangCount", gudangCount);
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
        model.addAttribute("gudang", gudang);
        model.addAttribute("gudangDTO", gudangDTO);
        // Send list of existing barangs as option
        List<Barang> listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "restock-gudang";
    }

    // TODO addRow: tambahBarangRow
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
}
