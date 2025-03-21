package hyeseo.boot.service;

import hyeseo.boot.domain.Address;
import hyeseo.boot.repository.SpringDataJpaMariaAddressRepository;
import hyeseo.boot.repository.SpringDataJpaMariaAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressAjaxServiceImpl implements AddressAjaxService {
    private final SpringDataJpaMariaAddressRepository authorRepository;
    @Override
    public List<Address> listS() {
        return authorRepository.findAll(Sort.by(Sort.Direction.DESC,"seq"));
    }

    @Override
    public Address insertS(Address address) {
        address = authorRepository.save(address);
        return address;
    }

    @Override
    public void deleteS(long seq) {
        authorRepository.deleteById(seq);
    }

    @Override
    public Address getBySeqS(long seq) {
        Address address = authorRepository.findById(seq).orElse( null);
        return address;
    }

    @Override
    public List<Address> getListByNames(String name) {
        List<Address> list = authorRepository.findByNameContaining(name);
        return list;
    }

}
