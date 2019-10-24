package no.kristiania.projectManagement;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMemberDaoTest {

    private JdbcDataSource dataSource = TestDataBase.testDataSource();

    @Test
    void shouldRetriveStoredProjectMembers() throws SQLException {

        ProjectMemberDao dao = new ProjectMemberDao(dataSource);
        //String task = sampleTask();
        ProjectMember projectMember = sampleTask();
        dao.insert(projectMember);
        assertThat(dao.listAll()).contains(projectMember);

    }

    @Test
    public void shouldSaveAllTaskFields() throws SQLException {
        ProjectMemberDao dao = new ProjectMemberDao(dataSource);
        ProjectMember projectMember = sampleTask();
        long id = dao.insert(projectMember);
        assertThat(dao.retrieve(id)).isEqualToComparingFieldByField(projectMember);
    }

    private ProjectMember sampleTask(){
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(sampleTaskName());

        return projectMember;
    }

    private String sampleTaskName() {
        String[] alternatives = {
                "Get files", "Deadline til 8pm", "meeting"
        };

        return alternatives[new Random().nextInt(alternatives.length)];
    }


}
