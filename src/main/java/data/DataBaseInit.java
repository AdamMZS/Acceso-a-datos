package data;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInit {

    public static void initialize(DataSource ds) {
        ejecutarScript(ds, "init.sql");
    }

    public static void initData(DataSource ds) {
        ejecutarScript(ds, "data.sql");
    }

    private static void ejecutarScript(DataSource ds, String nombreRecurso) {
        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             InputStream is = DataBaseInit.class.getClassLoader().getResourceAsStream(nombreRecurso)) {

            if (is == null) {
                throw new IllegalStateException("No se encontró el script en resources: " + nombreRecurso);
            }

            String sql = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            for (String sentencia : sql.split(";")) {
                if (!sentencia.trim().isEmpty()) {
                    stmt.execute(sentencia);
                }
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
