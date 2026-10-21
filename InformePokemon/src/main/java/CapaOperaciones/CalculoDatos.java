package CapaOperaciones;

import CapaDatos.Pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalculoDatos {
    public CalculoDatos(){}
    private ArrayList<Pokemon> pokemon;

    public CalculoDatos(ArrayList<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public int totalPokemon() {
        return pokemon.size();
    }

    public double nivelMedio() {

        int suma = 0;

        for (Pokemon p : pokemon) {
            suma += p.nivel();
        }

        return (double) suma / pokemon.size();
    }

    public double psMedios() {

        int suma = 0;

        for (Pokemon p : pokemon) {
            suma += p.ps();
        }

        return (double) suma / pokemon.size();
    }

    public String pokemonMayorNivel() {

        Pokemon mayor = pokemon.get(0);

        for (Pokemon p : pokemon) {

            if (p.nivel() > mayor.nivel()) {
                mayor = p;
            }
        }
        return mayor.nombre() + " (Nivel " + mayor.nivel() + ")";
    }
    public String pokemonMayorPs() {
        Pokemon mayor = pokemon.get(0);

        for (Pokemon p : pokemon) {

            if (p.ps() > mayor.ps()) {
                mayor = p;
            }
        }
        return mayor.nombre() + " (PS " + mayor.ps() + ")";
    }

    public HashMap<String, ArrayList<String>> pokemonPorTipo() {

        HashMap<String, ArrayList<String>> tipos = new HashMap<>();

        for (Pokemon p : pokemon) {

            if (!tipos.containsKey(p.tipo())) {
                tipos.put(p.tipo(), new ArrayList<>());
            }
            tipos.get(p.tipo()).add(p.nombre());
        }
        return tipos;
    }
    public String generarInforme(){
        StringBuilder sb=new StringBuilder();
        sb.append("=========================\n");
        sb.append(" INFORME DE POKÉMON\n");
        sb.append("=========================\n\n");

        sb.append("Total de Pokémon: ").append(totalPokemon()).append("\n\n");
        sb.append(String.format("Nivel medio: %.2f\n\n", nivelMedio()));
        sb.append(String.format("PS medios: %.2f\n\n", psMedios()));

        sb.append("Pokémon con mayor nivel:\n");
        sb.append(pokemonMayorNivel()).append("\n\n");

        sb.append("Pokémon con más PS:\n");
        sb.append(pokemonMayorPs()).append("\n\n");

        sb.append("-------------------------\n");
        sb.append("POKÉMON POR TIPO\n");
        sb.append("-------------------------\n\n");

        HashMap<String, ArrayList<String>> porTipo = pokemonPorTipo();
        for (Map.Entry<String, ArrayList<String>> entry : porTipo.entrySet()) {
            String tipo = entry.getKey().toUpperCase();
            ArrayList<String> listaNombres = entry.getValue();

            sb.append(tipo).append(": ").append(listaNombres.size()).append("\n");
            for (String nombre : listaNombres) {
                sb.append(nombre).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }

}