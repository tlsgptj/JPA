package hyeseo.boot.service;

import jakarta.transaction.Transactional;
import hyeseo.boot.domain.Address;
import hyeseo.boot.repository.JTAddressRepository;

import java.util.List;

//@Service
@Transactional //추가( JPA는 트랜젝션안에서 사용해야 함 )
public class JpaMariaJTAddressService implements JTAddressService {
    private final JTAddressRepository jtAddressRepository;
    //@Autowired
    public JpaMariaJTAddressService(JTAddressRepository jtAddressRepository) {
        this.jtAddressRepository = jtAddressRepository;
    }

    @Override
    public List<Address> listS() {
        pln("#JpaMariaJTAddressService list() 수행");
        return jtAddressRepository.list();
    }
    @Override
    public boolean insertS(Address address) {
        return jtAddressRepository.insert(address);
    }
    @Override
    public boolean deleteS(long seq) {
        return jtAddressRepository.delete(seq);
    }

    void pln(String str){
        System.out.println(str);
    }
}
