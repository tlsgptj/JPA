package hyeseo.boot.repository;

import hyeseo.boot.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import hyeseo.boot.domain.Address;

import java.util.List;


@SpringBootTest
class jdbcAddressServiceTest {
    @Autowired
    AddressService addressService;

    @Test
    void listS() {
        List<Address> list = addressService.listS();
        pln("@list: " + list);
    }
    @Test
    void insertS() {
        Address address = new Address(-1L, "태정",  "충주", null);
        Address addressDb = addressService.insertS(address);
        pln("@ddressDb: " + addressDb);
    }
    @Test
    void deleteS() {
        long seq = 6;
        boolean flag = addressService.deleteS(seq);
        pln("@flag: " + flag);
    }

    void pln(String str){
        System.out.println(str);
    }
}