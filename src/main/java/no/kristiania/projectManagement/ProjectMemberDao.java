package no.kristiania.projectManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectMemberDao extends AbstractDao<ProjectMember> {

    public ProjectMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertTask(ProjectMember projectMember, PreparedStatement statement) throws SQLException {
        statement.setString(1, projectMember.getName());
    }

    @Override
    protected ProjectMember readTask(ResultSet rs) throws SQLException {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(rs.getString("name"));
        return projectMember;
    }

    public long insert(ProjectMember projectMember) throws SQLException {
        return insert(projectMember, "insert into tasks (name) values(?)");
    }

    public List<ProjectMember> listAll() throws SQLException {
        return listAll("select * from tasks");
    }


    public ProjectMember retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("select * from tasks where id =?")) {
                statement.setLong(1, id);
                try(ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return readTask(rs);
                    } else {
                        return null;
                    }

                }
            }
        }

    }
}

