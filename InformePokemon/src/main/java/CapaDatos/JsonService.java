package CapaDatos;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonService {

    private String ruta = "pokemon.json";

    public JsonService() {
    }

    public JsonService(String ruta) {
        this.ruta = ruta;
    }

    public String AlmacenarDatos() {
        StringBuilder contenido = new StringBuilder();

        try (FileReader fr = new FileReader(ruta);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el archivo: " + ruta, e);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo", e);
        }

        return contenido.toString();
    }

    public void escribirDatos(String texto) {
        try (FileWriter fw = new FileWriter("informe_pokemon.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(texto);

        } catch (IOException e) {
            throw new RuntimeException("Error escribiendo informe_pokemon.txt", e);
        }
    }
    public List<Pokemon> cargarDesdeJson(String rutaArchivo) {
        ObjectMapper mapper = new ObjectMapper();
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            pokemons = mapper.readValue(new FileReader(rutaArchivo), new TypeReference<ArrayList<Pokemon>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON de Pokémon: " + e.getMessage(), e);
        }
        return pokemons;
    }



}