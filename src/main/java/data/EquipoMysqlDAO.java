package data;

import models.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoMysqlDAO implements DAO<Equipo>{

    private String url = "jdbc:mysql://localhost:3307/ad";
    private String user = "user";
    private String pwd = "pwd";

    private String sqlQuery = "select * from equipo";
    private String sqlDelete = "DELETE FROM equipo WHERE ID=?";
    private String sqlUpdate = "UPDATE equipo  SET NOMBRE = ?, CATEGORIA = ?, DISPONIBLE = ? WHERE ID = ?";
    private String sqlInsert="insert into equipo (nombre, categoria, disponible) values (?,?,?)";
    private String sqlFindById = "SELECT * FROM equipo WHERE id = ?";
    @Override
    public Equipo findById(Long id) {
        List<Equipo> salida =new ArrayList<>();
        Equipo equipo=null;
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
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

    @Override
    public List<Equipo> findAll() {
        List<Equipo> salida = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
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
        try(Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ){
            ps.setString(1,entity.getNombre());
            ps.setString(2,entity.getCategoria());
            ps.setBoolean(3,entity.getDisponible());

            ps.executeUpdate();

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection con =DriverManager.getConnection(url,user,pwd);
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
    try(Connection con=DriverManager.getConnection(url,user,pwd);
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