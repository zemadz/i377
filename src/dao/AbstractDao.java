package dao;

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public abstract class AbstractDao {

	public static final String DB_URL =
			"jdbc:hsqldb:file:${user.home}/i377/zemadz/db;shutdown=true;";

	private Connection connection;
	protected PreparedStatement pst;
	protected Statement st;
	protected ResultSet rs;

	static {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Connection getConnection() {
		try {
			connection = DriverManager.getConnection(DB_URL, "sa", "");
			
	    	try {
				DatabaseMetaData meta = connection.getMetaData();
				ResultSet testRs = meta.getTables(null, null, null, new String[] {"TABLE"});
				if(testRs.next()) {
					testRs.close();
				} else {
					SetupDao dao = new SetupDao();
					dao.createSchema();
					dao.insertTestData();
					testRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}
	    	connection = DriverManager.getConnection(DB_URL, "sa", "");
	    	
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected void closeResources() {
		DbUtils.closeQuietly(rs);
		DbUtils.closeQuietly(pst);
		DbUtils.closeQuietly(st);
		DbUtils.closeQuietly(connection);
	}

	protected void executeQuery(String queryString) {
		try {
			st = getConnection().createStatement();
			st.executeUpdate(queryString);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeResources();
		}
	}

}
