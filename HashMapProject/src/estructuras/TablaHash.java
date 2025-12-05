/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;
import java.util.LinkedList;

/**
 * Implementación de tabla hash con encadenamiento separado.
 * Soporta tipos genéricos y redimensionamiento dinámico.
 * El factor de carga máximo es 0.75.
 *
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 * @author jcant
 * @version 1.0
 */

public class TablaHash<K, V> implements Diccionario<K, V> {

    // Clase interna para manejar los pares clave-valor
    private class Nodo<K, V> {
        K key;
        V value;

        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Atributos de la tabla
    private LinkedList<Nodo<K, V>>[] tabla;
    private int size;       // Cantidad actual de elementos (N)
    private int capacidad;  // Tamano del arreglo (M)
    private static final double FACTOR_CARGA_MAX = 0.75;

    /**
     * Constructor por defecto.
     * Inicializa la tabla con una capacidad inicial de 11 (numero primo)
     * e inicializa todas las listas internas.
     */
    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.capacidad = 11;
        this.tabla = new LinkedList[capacidad];
        this.size = 0;

        // IMPORTANTE: Inicializar cada bucket del arreglo
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    /**
     * Calcula el indice valido en el arreglo para una clave dada.
     * Utiliza una mascara de bits para manejar hashCodes negativos.
     * * Complejidad: O(1)
     * * @param key La clave a procesar
     * @return Indice valido en el rango [0, capacidad-1]
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacidad;
    }

    /**
     * Inserta un par clave-valor en la tabla.
     * Si la clave ya existe, actualiza su valor.
     * Verifica automaticamente el factor de carga y redimensiona si es necesario.
     * * Complejidad: O(1) promedio, O(n) en el peor caso (si requiere resize).
     * * @param key La clave unica del elemento (no puede ser null)
     * @param value El valor asociado a la clave
     * @throws NullPointerException si key es null
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("La clave no puede ser nula");
        }

        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];

        // 1. Buscar si la clave ya existe para actualizar
        for (Nodo<K, V> nodo : lista) {
            if (nodo.key.equals(key)) {
                nodo.value = value; // Actualizar valor
                return;
            }
        }

        // 2. Si no existe, agregar nuevo nodo
        lista.add(new Nodo<>(key, value));
        size++;

        // 3. Verificar factor de carga
        if ((double) size / capacidad >= FACTOR_CARGA_MAX) {
            resize();
        }
    }

    /**
     * Recupera el valor asociado a una clave.
     * * Complejidad: O(1) promedio.
     * * @param key La clave a buscar
     * @return El valor asociado, o null si la clave no existe
     */
    @Override
    public V get(K key) {
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];

        for (Nodo<K, V> nodo : lista) {
            if (nodo.key.equals(key)) {
                return nodo.value;
            }
        }
        return null;
    }

    /**
     * Elimina un par clave-valor de la tabla.
     * * Complejidad: O(1) promedio.
     * * @param key La clave del elemento a eliminar
     * @return El valor eliminado, o null si la clave no existe
     */
    @Override
    public V remove(K key) {
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];

        // Usamos indice i para poder remover eficientemente de la lista
        for (int i = 0; i < lista.size(); i++) {
            Nodo<K, V> nodo = lista.get(i);
            if (nodo.key.equals(key)) {
                lista.remove(i);
                size--;
                return nodo.value;
            }
        }
        return null;
    }

    /**
     * Verifica si una clave existe en la tabla.
     * * Complejidad: O(1) promedio.
     * * @param key La clave a verificar
     * @return true si existe, false en caso contrario
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Retorna la cantidad de elementos almacenados.
     * * @return El tamano actual de la tabla (N)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Redimensiona la tabla hash cuando el factor de carga supera el umbral.
     * Duplica la capacidad y reubica todos los elementos (rehashing).
     * * Complejidad: O(n) donde n es el numero de elementos en la tabla.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Nodo<K, V>>[] tablaVieja = tabla;
        
        // 1. Duplicar capacidad
        capacidad = capacidad * 2;
        tabla = new LinkedList[capacidad];
        
        // 2. Inicializar nueva tabla
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }

        // 3. Reiniciar size porque put() lo incrementara nuevamente
        size = 0;

        // 4. Reinsertar todos los elementos (Rehashing)
        // Es CRITICO recalcular el hash porque la capacidad cambio
        for (LinkedList<Nodo<K, V>> bucket : tablaVieja) {
            for (Nodo<K, V> nodo : bucket) {
                put(nodo.key, nodo.value);
            }
        }
    }
}