package services;

import data.DataSourceFactory;
import models.Equipo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EquipoServicio {
    public boolean BookAllEquiposByCategoria(List<Long> ids) {
        String sql = "UPDATE ad.equipo SET disponible = false " +
                "WHERE id = ? AND disponible = true";

        DataSourceFactory dataSourceFactory=new DataSourceFactory();
        DataSource dataSource=dataSourceFactory.getDataSource();
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Long id : ids) {
                    System.out.println("Reservando equipo con id: " + id);
                    ps.setLong(1, id);

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                                "No se pudo reservar el equipo con id " + id
                        );
                    }
                }conn.commit();
                return true;
            } catch (SQLException | RuntimeException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al reservar los equipos", e);
        }

    }
}
