package hyeseo.boot.control;

import hyeseo.boot.domain.Address;
import hyeseo.boot.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("address")
@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    public String list(Model model) {
        List<Address> list = addressService.listS();
        model.addAttribute("list", list);
        return "address/list";
    }

    @RequestMapping("write.do")
    public String write() {
        return "address/write";
    }

    @PostMapping("write.do")
    public String write(Address address) {
        Address addressDb = addressService.insertS(address);
        System.out.println("@AddressController write() 수행 addressDb: " + addressDb);
        return "redirect:list.do";
    }

    @GetMapping("del.do")
    public String del(@RequestParam("seq") long seq) {
        boolean flag = addressService.deleteS(seq);
        return "redirect:list.do";
    }

}
