package apap.ti.silogistik2006596705.controller;

import apap.ti.silogistik2006596705.DTO.BarangMapper;
import apap.ti.silogistik2006596705.DTO.CreateBarangRequestDTO;
import apap.ti.silogistik2006596705.DTO.UpdateBarangRequestDTO;
import apap.ti.silogistik2006596705.model.Barang;
import apap.ti.silogistik2006596705.service.BarangService;
import jakarta.validation.Valid;

import java.util.List;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BarangController {
    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private BarangService barangService;

    // Viewall Barang
    @GetMapping("/barang")
    public String viewAllBarang(Model model) {
        List<Barang> barangs = barangService.getAllBarang();
        model.addAttribute("barangs", barangs);
        return "viewall-barang";
    }

    // Tampilin form tambah barang
    @GetMapping("/barang/tambah")
    public String addBarangForm(Model model) {
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return "form-create-barang";
    }

    // Tambah barang from form
    @PostMapping("/barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO,
            BindingResult validationResult, Model model) {
        // Validation
        if (validationResult.hasErrors()) {
            model.addAttribute("errorMessage", validationResult.getAllErrors());
            return "error-validation";
        }

        // check barangDTO tipeBarang's number & create sku via service
        var generatedSKU = barangService.createSKU(barangDTO.getTipeBarang());
        // map barangDTO to barang
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        // set SKU
        barang.setSku(generatedSKU);
        // call service to save barang via jpa
        barangService.saveBarang(barang);
        model.addAttribute("sku", barang.getSku());
        return "success-create-barang";
    }

    // tampilin form update barang
    @GetMapping("/barang/{idBarang}/ubah")
    public String formUpdateBarang(
            @PathVariable(value = "idBarang") String sku,
            Model model) {
        // Get barang by id
        var barang = barangService.getBarangBySku(sku);

        // Map to update DTO
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        // Add variables to be rendered at thyme
        model.addAttribute("barangDTO", barangDTO);

        return "form-ubah-barang";
    }

    // TODO process update barang
    @PostMapping("/barang/{idBarang}/ubah")
    public String updateBarang(
            @PathVariable(value = "idBarang") String sku,
            @Valid @ModelAttribute UpdateBarangRequestDTO barangDTO,
            BindingResult validationResult,
            Model model) {
        // DTO Validation
        if (validationResult.hasErrors()) {
            model.addAttribute("errorMessage", validationResult.getAllErrors());
            return "error-validation-edit";
        }

        // Map DTO to object
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        // Call service to update barang via jpa
        var barang = barangService.updateBarang(barangFromDTO);
        // Add variables to be rendered at thyme
        model.addAttribute("sku", barang.getSku());
        return "success-ubah-barang";
    }
}
