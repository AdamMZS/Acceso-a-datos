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
        nuevoEquipo.setNombre("PlayStation 5");
        nuevoEquipo.setCategoria("Consola");
        nuevoEquipo.setDisponible(true);

        dao.save(nuevoEquipo);
        System.out.println("¡Equipo insertado con éxito!");


        //FIND ALL
        System.out.println("\n=== 2. PROBANDO FIND ALL (findAll) ===");
        List<Equipo> equipos = dao.findAll();
        for (Equipo e : equipos) {
            System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre() + " | Categoría: " + e.getCategoria() + " | Disponible: " + e.getDisponible());
        }

        // Tomamos el ID del primer equipo de la lista para hacer pruebas
        if (!equipos.isEmpty()) {
            Long idPrueba = equipos.get(0).getId();


            //FIND BY ID
            System.out.println("\n=== 3. PROBANDO FIND BY ID (findById) ===");
            Equipo encontrado = dao.findById(idPrueba);
            if (encontrado != null) {
                System.out.println("Encontrado -> ID: " + encontrado.getId() + " | Nombre: " + encontrado.getNombre());
            } else {
                System.out.println("No se encontró ningún equipo con el ID: " + idPrueba);
            }
            //UPDATE
            System.out.println("\n=== 4. PROBANDO UPDATE (update) ===");
            encontrado.setNombre("PS5 Pro (Actualizado)");
            encontrado.setCategoria("Consola Next-Gen");
            dao.update(encontrado);
            System.out.println("¡Equipo actualizado!");

            // Verificamos el cambio volviendo a buscarlo
            Equipo actualizado = dao.findById(idPrueba);
            System.out.println("Verificación -> Nuevo Nombre: " + actualizado.getNombre() + " | Categoría: " + actualizado.getCategoria());

            System.out.println("\n=== 5. PROBANDO DELETE (deleteById) ===");
            dao.deleteById(idPrueba);
            System.out.println("¡Equipo con ID " + idPrueba + " eliminado!");

            // Comprobamos que ya no existe
            Equipo borrado = dao.findById(idPrueba);
            if (borrado == null) {
                System.out.println("Confirmado: El equipo ya no existe en la base de datos.");
            }
        } else {
            System.out.println("No hay equipos en la base de datos para realizar pruebas de FindById, Update o Delete.");
        }
    }
}