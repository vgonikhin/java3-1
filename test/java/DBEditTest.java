import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class DBEditTest {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psinit;
    private static PreparedStatement pss;
    private static PreparedStatement psi;
    private static PreparedStatement psu;
    private static PreparedStatement psd;
    private static ResultSet res;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDbNew.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initializeTable(int tableSize) throws Exception{
        stmt.execute("CREATE TABLE IF NOT EXISTS Products (\n" +
                "    ID    INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    PRODID INTEGER, \n" +
                "    TITLE TEXT,\n" +
                "    COST INTEGER);");

        stmt.execute("DELETE FROM Products");
        stmt.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='Products';");

        connection.setAutoCommit(false);

        psinit = connection.prepareStatement("INSERT INTO Products (prodid, title, cost) VALUES (?, ?, ?)");
        for (int i = 1; i <= tableSize; i++) {
            psinit.setInt(1, i);
            psinit.setString(2,"product" + i);
            psinit.setInt(3, i * 10);
            psinit.addBatch();
        }

        psinit.executeBatch();
        connection.setAutoCommit(true);
    }

    public static String selectRow(int id) {
            try {
                pss.setInt(1, id);
                res = pss.executeQuery();
                if (!res.next()) throw new RuntimeException();
                return res.getInt("PRODID") + " " + res.getString("TITLE") + " " + res.getInt("COST");
            }catch (SQLException e){
                e.printStackTrace();
            }
            return "ResultSet empty";
    }

    public static boolean insertRow(int id){
        try {
            psi.setInt(1, id);
            psi.setString(2,"product" + id);
            psi.setInt(3, id * 10);
            return (psi.executeUpdate()==1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateRow(int id, int newValue){
        try {
            psu.setInt(1, newValue);
            psu.setInt(2, id);
            return (psu.executeUpdate()==1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteRow(int id){
        try {
            psd.setInt(1, id);
            psd.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void init() throws Exception {
        connect();
        stmt = connection.createStatement();
        initializeTable(10000);
        connection.setAutoCommit(false);

        pss = connection.prepareStatement("SELECT * FROM Products WHERE prodid = ?");
        psi = connection.prepareStatement("INSERT INTO Products (prodid, title, cost) VALUES (?, ?, ?)");
        psu = connection.prepareStatement("UPDATE Products SET cost = ? WHERE prodid = ?");
        psd = connection.prepareStatement("DELETE FROM Products WHERE prodid = ?");
    }

    @Test
    public void test1() {
        insertRow(123456);
        Assert.assertTrue(selectRow(123456).equals("123456 product123456 1234560"));
    }

    @Test
    public void test2() {
        updateRow(123456,101010);
        Assert.assertTrue(selectRow(123456).equals("123456 product123456 101010"));
    }

    @Test(expected = RuntimeException.class)
    public void test3() {
        deleteRow(123456);
        selectRow(123456);
    }

    @AfterClass
    public static void end() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

}
