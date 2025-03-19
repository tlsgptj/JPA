package hyeseo.boot.service;

import hyeseo.boot.domain.Address;
import hyeseo.boot.mapper.AddressMapper;

import java.util.List;

//@Service
public class MybatisMariaAddressService implements JTAddressService{
    private final AddressMapper addressMapper;

    //@Autowired
    public MybatisMariaAddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }
    @Override
    public List<Address> listS() {
        return addressMapper.list();
    }
    public boolean insertS(Address address) {
        return addressMapper.insert(address);
    }
    public boolean deleteS(long seq) {
        return addressMapper.delete(seq);
    }

}
