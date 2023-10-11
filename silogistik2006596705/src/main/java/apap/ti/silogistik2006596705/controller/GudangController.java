package apap.ti.silogistik2006596705.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import apap.ti.silogistik2006596705.service.GudangService;
import apap.ti.silogistik2006596705.model.Gudang;

import java.util.List;

@Controller
public class GudangController {

    @Autowired
    private GudangService gudangService;

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
}
