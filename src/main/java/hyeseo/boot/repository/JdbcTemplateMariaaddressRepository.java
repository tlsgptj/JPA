package hyeseo.boot.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import hyeseo.boot.domain.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class JdbcTemplateMariaaddressRepository implements JTAddressRepository {
    private final JdbcTemplate jdbcTemplate;
    //@Autowired
    public JdbcTemplateMariaaddressRepository(JTAddressRepository jdbcTemplate) {
        this.jdbcTemplate = (JdbcTemplate) jdbcTemplate;
    }

    @Override
    public List<Address> list() {
        String sql = "select * from address order by SEQ desc";
        List<Address> list = jdbcTemplate.query(sql,  addressRowMapper());
        return list;
    }
    private RowMapper<Address> addressRowMapper() {
        return new RowMapper<Address>() {
            @Override
            public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
                Address address = new Address();
                address.setSeq(rs.getInt("SEQ"));
                address.setName(rs.getString("NAME"));
                address.setAddr(rs.getString("ADDR"));
                address.setRdate(rs.getTimestamp("RDATE")); //hh:mm:ss를 담을 수 있음
                return address;
            }
        };
    }
    @Override
    public boolean insert(Address address) {
        String sql = "insert into address(NAME, ADDR, RDATE) values(?,?,now())";
        int count = jdbcTemplate.update(sql, address.getName(), address.getAddr());
        if(count > 0) return true;
        else return false;
    }
    @Override
    public boolean delete(long seq) {
        String sql = "delete from address where SEQ=?";
        int count = jdbcTemplate.update(sql, seq);
        if(count > 0) return true;
        else return false;
    }
}