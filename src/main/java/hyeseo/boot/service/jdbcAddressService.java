package hyeseo.boot.service;

import hyeseo.boot.repository.AddressRepository;
import hyeseo.boot.domain.Address;
import java.util.List;

//@Service
public class jdbcAddressService implements AddressService {
    private final AddressRepository addressRepository;
    //@Autowired
    public jdbcAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> listS() {
        return addressRepository.list();
    }
    @Override
    public Address insertS(Address address) {
        return addressRepository.insert(address);
    }
    @Override
    public boolean deleteS(long seq) {
        return addressRepository.delete(seq);
    }
}