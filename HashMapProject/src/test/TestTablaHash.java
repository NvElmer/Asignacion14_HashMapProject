/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import estructuras.TablaHash;
/**
 *
 * @author jcant
 */
public class TestTablaHash {
    
    public static void main(String[] args) {
        System.out.println("=== Iniciando Pruebas de TablaHash ===");
        
        TablaHash<String, Integer> mapa = new TablaHash<>();
        
        // 1. Prueba de insercion (put)
        System.out.println("\n--- Prueba de Insercion ---");
        mapa.put("gato", 1);
        mapa.put("perro", 2);
        mapa.put("casa", 3);
        System.out.println("Insertados gato, perro y casa. Tamano esperado 3. Actual: " + mapa.size());
        
        // 2. Prueba de actualizacion
        System.out.println("\n--- Prueba de Actualizacion ---");
        mapa.put("gato", 100);
        System.out.println("Valor de 'gato' actualizado (esperado 100): " + mapa.get("gato"));
        
        // 3. Prueba de colisiones (depende del hash, pero probamos funcionalidad)
        System.out.println("\n--- Prueba de Multiples Elementos (Forzar Resize) ---");
        // Insertamos suficientes para superar el factor de carga 0.75 de 11 (aprox 8 elementos)
        for(int i = 0; i < 15; i++) {
            mapa.put("clave" + i, i);
        }
        System.out.println("Se insertaron 15 elementos extra.");
        System.out.println("Tamano total esperado (3 iniciales + 15 nuevos): " + mapa.size());
        
        // 4. Prueba de recuperacion (get)
        System.out.println("\n--- Prueba de Recuperacion ---");
        System.out.println("Valor de 'clave5' (esperado 5): " + mapa.get("clave5"));
        System.out.println("Valor de 'fantasma' (esperado null): " + mapa.get("fantasma"));
        
        // 5. Prueba de eliminacion (remove)
        System.out.println("\n--- Prueba de Eliminacion ---");
        Integer borrado = mapa.remove("perro");
        System.out.println("Eliminando 'perro'. Valor retornado (esperado 2): " + borrado);
        System.out.println("Buscar 'perro' despues de borrar (esperado null): " + mapa.get("perro"));
        System.out.println("Tamano despues de borrar: " + mapa.size());
        
        System.out.println("\n=== Pruebas Finalizadas ===");
    }
}
