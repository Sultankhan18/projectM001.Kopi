package no.kristiania.projectManagement;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDaoTest {

    private JdbcDataSource dataSource = TestDataBase.testDataSource();

    @Test
    void shouldFindSavedMembers() throws SQLException {
       /* JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:myTestDatabase;DB_CLOSE_DELAY=-1");

        */


       // Flyway.configure().dataSource(dataSource).load().migrate();
        Member member = new Member();

        member.setName("Test");
        MemberDao dao = new MemberDao(dataSource);

        dao.insert(member);
        assertThat(dao.listAll()).contains(member);


    }

}
