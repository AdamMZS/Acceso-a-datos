package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static String url = "jdbc:mysql://localhost:3307/ad";
    private static String user = "user";
    private static String pwd = "pwd";

    static void main() {

        //INSERT
        String sqlQuery = "select * from equipo";
        String sqlInsert = "insert into equipo (nombre, categoria, disponible) values (?,?,?)";
        String sqlUpdate="UPDATE equipo  SET NOMBRE = ?, CATEGORIA = ?, DISPONIBLE = ? WHERE ID = ?";
        String sqlDelete="DELETE FROM equipo WHERE ID=?";
        try (Connection conn = DriverManager.getConnection(url,user,pwd);
             PreparedStatement stmt = conn.prepareStatement(
                     "insert into equipo (nombre, categoria, disponible) values (?,?,?)");)
        {
            stmt.setString(1,"GTA VI");
            stmt.setString(2,"VideoGame");
            stmt.setBoolean(3,true);
            int  filas = stmt.executeUpdate();

            System.out.println("filas insertadas: "+filas);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        /* try con update */
        try(Connection conn =DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps=conn.prepareStatement(sqlUpdate);
        ){
            ps.setString(1,"GTA IV");
            ps.setString(2,"Accion");
            ps.setBoolean(3,true);
            ps.setInt(4,30);
            int filas=ps.executeUpdate();
            if (filas>0){
                System.out.println("Actualizado");
            }else
                System.out.println( "error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /* try con delete */
        try(Connection con =DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps=con.prepareStatement(sqlDelete);){
            ps.setInt(1,1);
            int filas=ps.executeUpdate();
            if (filas>0){
                System.out.println("Eliminado");
            }else
                System.out.println( "error");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //SELECT
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement ps = conn.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery() )
        {

            while (rs.next()) {
                String name = rs.getString("nombre");
                System.out.println(name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}