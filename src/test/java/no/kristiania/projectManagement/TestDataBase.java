package no.kristiania.projectManagement;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

public class TestDataBase {
    public static JdbcDataSource testDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase;DB_CLOSE_DELAY=-1");

        Flyway.configure().dataSource(dataSource).load().migrate();
        return dataSource;
    }
}
