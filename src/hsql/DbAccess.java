package hsql;

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public class DbAccess {

    private static final String DB_URL = "jdbc:hsqldb:file:${user.home}/i377/zemadz/db;shutdown=true;hsqldb.lock_file=false";

    static {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    };

    public static void main(String[] args) throws Exception {
        DbAccess d = new DbAccess();
        d.createTable();
        d.insertData();
        d.printPersons();
        d.printCertainPerson();
        d.updatePerson();
    }

    private void updatePerson() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);

        PreparedStatement ps = null;
        Statement stmt = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE person SET name = ? WHERE id = ?");
            ps.setString(1, "Mary");
            ps.setLong(2, 3L);

            int rowCount = ps.executeUpdate();
            System.out.println(rowCount + " rows updated!");

        } finally {
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
    }

    private void printCertainPerson() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            ps = conn.prepareStatement("SELECT id, name FROM person "
                    + "WHERE id = ?");
            ps.setLong(1, 3L);
            rset = ps.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getInt(1) + ", " + rset.getString(2));
            }
        } finally {
            DbUtils.closeQuietly(rset);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
    }

    private void printPersons() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);

        Statement stmt = null;
        ResultSet rset = null;
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT id, name FROM person");
            while (rset.next()) {
                System.out.println(rset.getLong(1) + ", " + rset.getString(2));
            }
        } finally {
            DbUtils.closeQuietly(rset);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
    }

    private void createTable() throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE person (id INT, name VARCHAR(100))");
        } finally {
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
    }

    private void insertData() {
        executeQuery("INSERT INTO person VALUES (1, 'John')");
        executeQuery("INSERT INTO person VALUES (2, 'Jack')");
        executeQuery("INSERT INTO person VALUES (3, 'Jill')");
    }

    private void executeQuery(String queryString) {
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            stmt.executeUpdate(queryString);
         } catch (Exception e) {
             throw new RuntimeException(e);
         } finally {
             DbUtils.closeQuietly(stmt);
             DbUtils.closeQuietly(conn);
         }
    }
}

