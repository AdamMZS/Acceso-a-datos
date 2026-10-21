import CapaDatos.JsonService;
import CapaDatos.Pokemon;
import CapaOperaciones.CalculoDatos;

import java.util.ArrayList;
    public static void main(String[] args) {

        try {
            JsonService servicio = new JsonService("pokemon.json");

            ArrayList<Pokemon> listaPokemon = (ArrayList<Pokemon>) servicio.cargarDesdeJson("pokemon.json");

            CalculoDatos calculos = new CalculoDatos(listaPokemon);

            String informe = calculos.generarInforme();

            servicio.escribirDatos(informe);

            System.out.println("\nInforme guardado en informe_pokemon.txt");

        } catch (RuntimeException e) {
            System.err.println("Error al procesar los Pokémon: "
                    + e.getMessage());
        }

}