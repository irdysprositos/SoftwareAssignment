import java.sql.*;
//import org.junit.Test;

public class ContactServer {
    private Connection con;

    public ContactServer() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            String url = "jdbc:mysql://localhost:3306/zselectcourse";
            String userName = "root";
            String password = "123456";
            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finalize() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW
    public ResultSet select_name(String name) throws SQLException {
        String sql = "select * from addressbook where name = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    // ADD
    public Boolean insert(String name, String location, String phone) {
        String sql = "insert into addressbook values (null, ?,?,?)";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setString(3, phone);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE
    public void update_name(int id, String name, String location, String phone) {
        String sql = "update addressbook set name = ?, set location = ?, phone = ? where id= ? ";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setString(3, phone);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // DELETE
    public Boolean delete(int id) {
        String sql = "delete from addressbook where uid=?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

//    @Test
//    public void test1() throws Exception { // 下面方法有不同的异常，我直接抛出一个大的异常
//        JDBCDemo j = new JDBCDemo();
//
//        j.delete(3);
//
//    }
}