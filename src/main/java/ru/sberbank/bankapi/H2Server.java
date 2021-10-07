package ru.sberbank.bankapi;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Server {
    private static Server server;

    public static void run() throws Exception {
        try {
            server = Server.createTcpServer("-tcpAllowOthers").start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initDb();
    }

    public static void stop() throws Exception {
        server.stop();
    }

    private static void initDb() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/BankAPISpringBoot/src/main/resources/database/bank", "root","password");
        RunScript.execute(connection, new FileReader(
                H2Server.class.getClassLoader().getResource("database/dropSchema.sql").getFile()));
        RunScript.execute(connection, new FileReader(
                H2Server.class.getClassLoader().getResource("database/init.sql").getFile()));
        RunScript.execute(connection, new FileReader(
                H2Server.class.getClassLoader().getResource("database/data.sql").getFile()));
    }
}
