package data;

import models.Equipo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoMysqlDAO implements DAO<Equipo>{

    private final DataSource dataSource = DataSourceFactory.getDataSource();

    private String sqlQuery = "select * from equipo";
    private String sqlDelete = "DELETE FROM equipo WHERE ID=?";
    private String sqlUpdate = "UPDATE equipo  SET NOMBRE = ?, CATEGORIA = ?, DISPONIBLE = ? WHERE ID = ?";
    private String sqlInsert="insert into equipo (nombre, categoria, disponible) values (?,?,?)";
    private String sqlFindById = "SELECT * FROM equipo WHERE id = ?";
    private String sqlFindByName="SELECT * FROM equipo where nombre = ?";
    @Override
    public Equipo findById(Long id) {
        List<Equipo> salida =new ArrayList<>();
        Equipo equipo=null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlFindById);){
                ps.setLong(1,id);
            try( ResultSet rs = ps.executeQuery() ) {
                while (rs.next()) {
                    equipo = new Equipo();
                    equipo.setId(rs.getLong("id"));
                    equipo.setNombre(rs.getString("nombre"));
                    equipo.setCategoria(rs.getString("categoria"));
                    equipo.setDisponible(rs.getBoolean("disponible"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipo;

    }
    public List<Equipo>findbyNombre(String nombre){
        List<Equipo>lista=new ArrayList<>();
        try (Connection con=dataSource.getConnection();
         PreparedStatement ps = con.prepareStatement(sqlFindByName);)
        {
            ps.setString(1,nombre);
            try (ResultSet rs = ps.executeQuery()){
             while (rs.next()){
                     Equipo equipo = new Equipo();
                     equipo.setId(rs.getLong("id"));
                     equipo.setNombre(rs.getString("nombre"));
                     equipo.setCategoria(rs.getString("categoria"));
                     equipo.setDisponible(rs.getBoolean("disponible"));
                     lista.add(equipo);
             }
         }
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
        return lista;

    }

    @Override
    public List<Equipo> findAll() {
        List<Equipo> salida = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery);
             // Ejecuta la consulta SELECT para obtener todos los registros
             ResultSet rs = ps.executeQuery() )
        {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getLong("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setCategoria(rs.getString("categoria"));
                equipo.setDisponible(rs.getBoolean("disponible"));
                salida.add(equipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public Equipo save(Equipo entity) {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1,entity.getNombre());
            ps.setString(2,entity.getCategoria());
            ps.setBoolean(3,entity.getDisponible());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    entity.setId(rs.getLong(1));
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection con =dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDelete);
        ){
          ps.setLong(1,id);
          ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Equipo entity) {
    try(Connection con=dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sqlUpdate);
    ){
        ps.setString(1,entity.getNombre());
        ps.setString(2,entity.getCategoria());
        ps.setBoolean(3,entity.getDisponible());
        ps.setLong(4,entity.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

}