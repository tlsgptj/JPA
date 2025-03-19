package hyeseo.boot.mapper;

import hyeseo.boot.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressMapper {
    List<Address> list();
    boolean insert(Address address);
    boolean delete(long seq);
}
