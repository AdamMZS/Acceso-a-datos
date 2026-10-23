package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/ad",
                    "user",
                    "pwd")){

            PreparedStatement ps = conn.prepareStatement("select nombre as name from equipo");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
