package hyeseo.boot.control;

import hyeseo.boot.domain.Address;
import hyeseo.boot.service.AddressAjaxService;
import hyeseo.boot.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest.addr")
@CrossOrigin(origins = "*", maxAge = 3600) //React서버 허용
public class AddressRestController {
    private final AddressAjaxService addressService;
    private final AddressAjaxService addressAjaxService;

    //(1) Create (insert)
    @PostMapping("create1")
    public void create1(Address address) {
        pln("@AddressRestController create1() address" + address);
        addressService.insertS(address);
    }

    void pln(String msg) {
        System.out.println(msg);
    }

    @PostMapping("create2")
    public void create2(@RequestBody Address address) {
        pln("@AddressRestController create2() address" + address);
        addressAjaxService.insertS(address);
    }

    //(2) read (select)
    @GetMapping("read")
    public List<Address> read() {
        List<Address> list = addressAjaxService.listS();
        return list;
    }

    @GetMapping("read/{seq}")
    public Address read(@RequestParam("seq") long seq) {
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }
    //@GetMapping("read/{seq}")와 공존할 수 없음
    /*@GetMapping("read/{na}")
    public List<Address> read(@PathVariable("na") String na) {
        List<Address> list = addressAjaxService.getListByNames(na);
        return list;
    }*/

    @GetMapping(value = "read", params = {"na"})
    public List<Address> read(@RequestParam("na") String na) {
        List<Address> list = addressAjaxService.getListByNames(na);
        return list;
    }

    //(3) Delete (delete)
    @DeleteMapping("delete/{seq}")
    public void delete(@PathVariable("seq") long seq) {
        addressAjaxService.deleteS(seq);
    }
}
