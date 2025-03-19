package hyeseo.boot.repository;

import hyeseo.boot.domain.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class jdbcAddressRepositoryTest {
    @Autowired
    private jdbcOracleAddressRepository jdbcOracleAddressRepository;

    @Test
    void getConnection() {
        pln("#repository: " + jdbcOracleAddressRepository);
        pln("#con: " + jdbcOracleAddressRepository.getConnection());
    }

    @Test
    void list() {
        List<Address> list = jdbcOracleAddressRepository.list();
        pln("#list: " + list);
    }

    @Test
    void insert() {
        Address address = new Address(-1L, "sdfsdf", "서울", null);
        Address adressDb = jdbcOracleAddressRepository.insert(address);
        pln("insert: " + adressDb);
    }

    @Test
    void delete() {
        boolean flag = jdbcOracleAddressRepository.delete(-5L);
        pln("#flag: " + flag);
    }

    void pln(String str) {
        System.out.println(str);
    }
}