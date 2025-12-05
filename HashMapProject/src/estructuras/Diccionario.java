package estructuras;

/**
 *
 * @author jcant
 */
public interface Diccionario<K, V> {

    /**
     * Asocia el valor especificado con la clave especificada en este mapa.
     * Si el mapa ya contenia una asignacion para la clave, el valor anterior
     * es reemplazado.
     * * @param key clave con la que se asociara el valor especificado
     * @param value valor que se asociara con la clave especificada
     */
    void put(K key, V value);

    /**
     * Devuelve el valor al cual esta asignada la clave especificada,
     * o null si este mapa no contiene ninguna asignacion para la clave.
     * * @param key la clave cuyo valor asociado se va a devolver
     * @return el valor al cual esta asignada la clave especificada, o null
     */
    V get(K key);

    /**
     * Elimina la asignacion para una clave de este mapa si esta presente.
     * * @param key clave cuya asignacion se va a eliminar del mapa
     * @return el valor previo asociado con key, o null si no habia asignacion
     */
    V remove(K key);

    /**
     * Devuelve true si este mapa contiene una asignacion para la clave especificada.
     * * @param key clave cuya presencia en este mapa se va a probar
     * @return true si este mapa contiene una asignacion para la clave especificada
     */
    boolean containsKey(K key);

    /**
     * Devuelve el numero de asignaciones clave-valor en este mapa.
     * * @return el numero de asignaciones clave-valor en este mapa
     */
    int size();
}
