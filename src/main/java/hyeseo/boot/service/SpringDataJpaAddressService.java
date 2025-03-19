package hyeseo.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hyeseo.boot.domain.Address;
import hyeseo.boot.repository.SpringDataJpaMariaAddressRepository;
import java.util.List;

@Service
public class SpringDataJpaAddressService implements AddressService {
    private final SpringDataJpaMariaAddressRepository repository;
    @Autowired
    public SpringDataJpaAddressService(SpringDataJpaMariaAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> listS() {
        pln("#SpringDataJpaAddressService listS() 수행");
        //List<Address> list = repository.findAll();
        //List<Address> list = repository.findByName("이길동");
        //List<Address> list = repository.findByNameAndAddr("홍길동", "제주시");
        List<Address> list = repository.findByNameContaining("길동");
        return list;
    }
    @Override
    public Address insertS(Address address) {
        address = repository.save(address);
        pln("#SpringDataJpaAddressService insertS() address: " + address);
        return address;
    }
    @Override
    public boolean deleteS(long seq) {
        repository.deleteById(seq);
        pln("#SpringDataJpaAddressService deleteS()");
        return true;
    }
    public List<Address> findByNameS(String name){
        List<Address> list = repository.findByName(name);
        pln("#SpringDataJpaAddressService findByNameS() list: " + list);
        return list;
    }
    public List<Address> findByNameAndAddrS(String name, String addr){
        List<Address> list = repository.findByNameAndAddr(name, addr);
        pln("#SpringDataJpaAddressService findByNameAndAddrS() list: " + list);
        return list;
    }
    public List<Address> findByNameContainingS(String name){
        List<Address> list = repository.findByNameContaining(name);
        pln("#SpringDataJpaAddressService findByNameContainingS() list: " + list);
        return list;
    }

    void pln(String str){
        System.out.println(str);
    }
}
