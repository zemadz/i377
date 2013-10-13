package hsql;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;

public class Pooling {

    public static void main(String[] args) {
        // First we set up the BasicDataSource.
        // Normally this would be handled automatically by
        // an external configuration, but in this example we'll
        // do it manually.
        DataSource dataSource = setupDataSource("jdbc:hsqldb:mem:orders");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT count(*) FROM INFORMATION_SCHEMA.SYSTEM_TABLES");
            rset.next();
            System.out.println("count is " + rset.getInt(1));

            // current connection
            System.out.println(conn);

            Connection newConnection;
            for (int i = 0; i < 3; i++) {
                newConnection = dataSource.getConnection();
                // ask for new connection
                System.out.println(newConnection);
                // and close it
                if (newConnection != null) newConnection.close();
            }

            printDataSourceStats(dataSource);
            shutdownDataSource(dataSource);

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(rset);
            DbUtils.closeQuietly(stmt);
        }
    }

    public static DataSource setupDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUsername("SA");
        ds.setPassword("");
        ds.setUrl(connectURI);
        return ds;
    }

    public static void printDataSourceStats(DataSource ds) {
        BasicDataSource bds = (BasicDataSource) ds;
        System.out.println("NumActive: " + bds.getNumActive());
        System.out.println("NumIdle: " + bds.getNumIdle());
    }

    public static void shutdownDataSource(DataSource ds) throws SQLException {
        BasicDataSource bds = (BasicDataSource) ds;
        bds.close();
    }
}