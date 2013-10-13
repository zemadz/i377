package hsql;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

public class ServerMode {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting Database");
        HsqlProperties p = new HsqlProperties();
        p.setProperty("server.database.0", "${user.home}/hsql/jeeProject/db;shutdown=true");
        p.setProperty("server.dbname.0", "mydb");
        p.setProperty("server.port", "9001");
        Server server = new Server();
        server.setProperties(p);
        server.start();

        while (true) {
            Thread.sleep(1);
        }


    }

}
