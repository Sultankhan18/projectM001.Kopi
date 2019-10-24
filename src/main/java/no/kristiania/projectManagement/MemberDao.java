package no.kristiania.projectManagement;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao extends AbstractDao<Member> {


    public MemberDao(DataSource dataSource) {
        super(dataSource);

    }

    @Override
    protected void insertTask(Member member, PreparedStatement statement) throws SQLException {
        statement.setString(1, member.getName());
    }

    @Override
    protected Member readTask(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setName(rs.getString("name"));
        return member;
    }


    public void insert(Member task) throws SQLException {
        insert(task, "insert into members (name) values(?)");
    }

    public List<Member> listAll() throws SQLException {
        return listAll("select * from MEMBERS ");
    }
}
