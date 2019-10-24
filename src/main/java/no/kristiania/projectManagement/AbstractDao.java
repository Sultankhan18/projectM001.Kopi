package no.kristiania.projectManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<A> {
    protected DataSource dataSource;

    public AbstractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insert(A task, String sql1) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = sql1;
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertTask(task, statement);
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        }
    }

    protected abstract void insertTask(A task, PreparedStatement statement) throws SQLException;

    public List<A> listAll(String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<A> task = new ArrayList<>();

                    while (rs.next()) {
                        task.add(readTask(rs));
                    }

                    return task;
                }

            }
        }

    }

    protected abstract A readTask(ResultSet rs) throws SQLException;


}
