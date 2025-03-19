package hyeseo.boot.service;

import hyeseo.boot.domain.Address;
import hyeseo.boot.repository.JTAddressRepository;

import java.util.List;

//@Service
public class jdbcTemplateAddressService implements JTAddressService {
    //private final AddressRepository addressRepository;
    private final JTAddressRepository addressRepository;
    //@Autowired
    public jdbcTemplateAddressService(JTAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> listS() {
        return addressRepository.list();
    }
    @Override
    public boolean insertS(Address address) {
        return addressRepository.insert(address);
    }
    @Override
    public boolean deleteS(long seq) {
        return addressRepository.delete(seq);
    }
}