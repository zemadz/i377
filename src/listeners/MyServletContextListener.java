package listeners;

import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;

import dao.SetupDao;

public class MyServletContextListener implements javax.servlet.ServletContextListener {

	public MyServletContextListener() {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		String dbPath = System.getProperty("user.home") + "/i377/db/db.script";
		if(!new File(dbPath).exists()) {
			SetupDao dao = new SetupDao();
			dao.createSchema();
			dao.insertTestData();
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
