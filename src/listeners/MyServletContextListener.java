package listeners;

import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;

import dao.SetupDao;

public class MyServletContextListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

//		SetupDao setup = new SetupDao();
//		setup.createSchema();
//		setup.insertTestData();
		// File file = new File(System.getProperty("user.home"),
		// "/i377/zemadz/db/db.script");
		// if(!file.getAbsoluteFile().exists()) {
		// SetupDao setup = new SetupDao();
		// setup.createSchema();
		// setup.insertTestData();
		// }
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Enumeration<Driver> drivers = DriverManager.getDrivers();
		// while (drivers.hasMoreElements()) {
		// try {
		// DriverManager.deregisterDriver(drivers.nextElement());
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
	}

}
