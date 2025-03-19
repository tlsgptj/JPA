package hyeseo.boot.repository;

import org.springframework.jdbc.datasource.DataSourceUtils;
import hyeseo.boot.domain.Address;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class jdbcMariaaddressRepository implements AddressRepository {
    //@Autowired
    private final DataSource dataSource;
    //@Autowired
    public jdbcMariaaddressRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection con) throws SQLException {
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    @Override
    public List<Address> list() {
        List<Address> list = new ArrayList<Address>();
        String sql = "select * from address order by SEQ desc";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                Date date = rs.getDate(4);
                list.add(new Address(seq, name, addr, date));
            }
            return list;
        }catch(SQLException se){
            return null;
        }finally{
            close(con, stmt, rs);
        }
    }
    private void close(Connection con, Statement stmt, ResultSet rs) {
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }catch(SQLException se){}
    }

    @Override
    public Address insert(Address address) {
        String sql = "insert into address(NAME, ADDR, RDATE) values(?,?,now())";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, address.getName());
            pstmt.setString(2, address.getAddr());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                String seqStr = rs.getString(1);
                Address addressDb = getaddress(seqStr);
                return addressDb;
            }else{
                return null;
            }
        }catch(SQLException se){
            return null;
        }finally {
            close(con, pstmt, rs);
        }
    }
    private Address getaddress(String seqStr) {
        String sql = "select * from address where SEQ = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, seqStr);
            rs = pstmt.executeQuery();
            if(rs.next()){
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                Date date = rs.getDate(4);
                return new Address(seq, name, addr, date);
            }else{
                return null;
            }
        }catch (SQLException se){
            return null;
        }finally{
            close(con, pstmt, rs);
        }
    }
    @Override
    public boolean delete(long seq) {
        String sql = "delete from address where SEQ = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, seq);
            int i = pstmt.executeUpdate();
            if(i > 0) return true;
            else return false;
        }catch(SQLException se){
            return false;
        }finally{
            close(con, pstmt, rs);
        }
    }
}