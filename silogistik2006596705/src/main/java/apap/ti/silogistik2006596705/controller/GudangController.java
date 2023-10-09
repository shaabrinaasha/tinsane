package apap.ti.silogistik2006596705.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GudangController {
    // home page
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
