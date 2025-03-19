package hyeseo.boot.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThController {
    @GetMapping("hi")
    public String hi(Model model) {
        model.addAttribute("data", "안녕 Thymleaf");
        return "index";
    }
}
