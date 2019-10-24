package no.kristiania.projectManagement;


import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

public class ProjectManager {

    private MemberDao memberDao;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private PGSimpleDataSource dataSource;
    private ProjectMemberDao projectMemberDao;

    public ProjectManager() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("projectmanager.properties"));

        dataSource = new PGSimpleDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/projectmanager");
        dataSource.setUser("projectmanager");
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();

        projectMemberDao = new ProjectMemberDao(dataSource);
        memberDao = new MemberDao(dataSource);

    }

    public static void main(String[] args) throws IOException, SQLException {
        new ProjectManager().run();
    }

    private void run() throws IOException, SQLException {
        System.out.println("Choose action: create [task] | create [member]");
        String action = input.readLine();

        switch (action.toLowerCase()) {
            case "task":
                insertTask();
                break;
            case "member":
                insertMember();
                break;
        }

        System.out.println("Current tasks " + projectMemberDao.listAll());

    }

    private void insertMember() throws IOException, SQLException {
        System.out.println("Please type the name of new task");
        Member member = new Member();
        member.setName(input.readLine());
        memberDao.insert(member);
    }

    private void insertTask() throws IOException, SQLException {
        System.out.println("Please type the name of new task");
        String taskName = input.readLine();
        ProjectMember projectMember = new ProjectMember();
        projectMember.setName(taskName);
        projectMemberDao.insert(projectMember);
    }

}
