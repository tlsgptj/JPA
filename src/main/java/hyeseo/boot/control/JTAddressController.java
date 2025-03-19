package hyeseo.boot.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import hyeseo.boot.domain.Address;
import hyeseo.boot.service.JTAddressService;

import java.util.List;

@RequestMapping("address_jt")
@Controller
public class JTAddressController {
    @Autowired
    private JTAddressService jtAddressService;

    @GetMapping("list.do")
    public String list(Model model) {
        System.out.println("@addressController list() 수행");
        List<Address> list = jtAddressService.listS();
        model.addAttribute("list", list);
        return "address_jt/list";
    }
    @GetMapping("write.do")
    public String write() {
        return "address_jt/write";
    }
    @PostMapping("write.do")
    public String write(Address address) {
        boolean flag = jtAddressService.insertS(address);
        return "redirect:list.do";
    }
    @GetMapping("del.do")
    public String del(@RequestParam("seq") long seq) {
        boolean flag = jtAddressService.deleteS(seq);
        return "redirect:list.do";
    }
}