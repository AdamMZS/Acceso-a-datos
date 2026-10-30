package models;
import data.EquipoMysqlDAO;
import models.Equipo;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Instanciamos nuestro DAO
        EquipoMysqlDAO dao = new EquipoMysqlDAO();
        System.out.println("=== 1. PROBANDO INSERT (save) ===");
        Equipo nuevoEquipo = new Equipo();
        nuevoEquipo.setNombre("PlayStation 6");
        nuevoEquipo.setCategoria("Consola");
        nuevoEquipo.setDisponible(true);

        dao.save(nuevoEquipo);
        System.out.println("¡Equipo insertado con éxito con id:"+nuevoEquipo.getId());


        for (Equipo equipo : dao.findbyNombre("MacBook Air M2")) {
            System.out.println(equipo);
        }
     

    }
}