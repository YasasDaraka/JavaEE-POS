package lk.ijse.pos.dao.custom.impl.util;

import lk.ijse.pos.listener.ContextListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(String sql, ResultSetHandler<T> handler, Object... args) throws SQLException, ClassNotFoundException {
        try (Connection connection = ContextListener.pool.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                pstm.setObject((i + 1), args[i]);
            }
            if (sql.startsWith("SELECT") || sql.startsWith("select")) {
                try (ResultSet rs = pstm.executeQuery()) {
                    return handler.handle(rs);
                }
            } else {
                int rowsAffected = pstm.executeUpdate();
                return (T) Boolean.valueOf(rowsAffected > 0);
            }
        }
    }
    public interface ResultSetHandler<T> {
        T handle(ResultSet rs) throws SQLException;
    }
}
