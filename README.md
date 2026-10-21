# Informe Pokémon

Proyecto sencillo en Java para leer datos de Pokémon desde un archivo JSON y generar un informe con sus estadísticas.

## Funcionalidades

- Cuenta el total de Pokémon.
- Calcula el nivel medio y los PS (puntos de salud) medios.
- Muestra el Pokémon con más nivel y con más PS.
- Agrupa los Pokémon por su tipo (Fuego, Agua, Planta, etc.).

## Tecnologías

* **Java**
* **Gson** (para leer los datos en JSON)
* **Maven**

## Estructura del código

* `Pokemon.java`: Clase con los datos de cada Pokémon.
* `JsonService.java`: Se encarga de leer el archivo JSON.
* `CalculoDatos.java`: Realiza los cálculos y genera el informe.
* `Main.java`: Ejecuta la aplicación.

## Cómo ejecutarlo

1. Clona el repositorio:
   ```bash
   git clone [https://github.com/AdamMZS/Acceso-a-datos.git](https://github.com/AdamMZS/Acceso-a-datos.git)

   PD:Se borraron los commits al hacer el ultimo, ya que cambie el lector de JSON y tuve que quitar todo lo anterior para subir la ultima version
