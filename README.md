# Implementacion de Tabla Hash (Parte 1)

## Descripcion del Proyecto
Este proyecto consiste en la implementacion desde cero de una estructura de datos tipo "Tabla Hash" (Hash Table) en Java.

El objetivo principal es crear un contenedor de datos eficiente que asocie claves unicas con valores, implementando la interfaz Diccionario. La solucion aborda retos tecnicos fundamentales como el manejo de tipos genericos, la resolucion de colisiones mediante encadenamiento separado y el redimensionamiento dinamico del arreglo subyacente.

## Estructura del Proyecto

El codigo esta organizado en dos paquetes para separar la logica de la estructura de las pruebas unitarias:

### Paquete: estructuras
Contiene el nucleo de la estructura de datos.

* Diccionario.java (Interface):
  Define el contrato esencial para el funcionamiento del mapa. Incluye las operaciones basicas que toda estructura de tipo diccionario debe soportar:
  - put: Insertar o actualizar un valor.
  - get: Recuperar un valor dado una clave.
  - remove: Eliminar una clave y su valor.
  - containsKey: Verificar existencia.
  - size: Conocer la cantidad de elementos.

* TablaHash.java:
  Es la clase concreta que implementa la interfaz Diccionario.
  - Genericos <K, V>: Permite almacenar cualquier tipo de objeto (String, Integer, objetos personalizados, etc.).
  - Manejo de Colisiones: Utiliza un arreglo de listas enlazadas (LinkedList). Si dos claves generan el mismo indice hash, se almacenan en la misma lista (bucket).
  - Factor de Carga (0.75): Controla cuando debe crecer la tabla. Si el numero de elementos supera el 75% de la capacidad del arreglo, se dispara el metodo resize().
  - Hash Seguro: Implementa una mascara de bits (clave.hashCode() & 0x7fffffff) para asegurar que los indices sean siempre positivos.

### Paquete: test
* TestTablaHash.java:
  Clase ejecutable (main) diseñada para validar la robustez de la implementacion. Realiza pruebas secuenciales de todas las operaciones para asegurar que no existan errores de logica o excepciones inesperadas.

## Detalles de Implementacion

### Algoritmo de Hashing
Se utiliza el metodo hashCode() nativo de Java combinado con una operacion bitwise AND para garantizar indices positivos dentro de los limites del arreglo.

### Redimensionamiento (Resize)
La tabla inicia con una capacidad pequeña (11 buckets). Cuando la carga supera el umbral definido:
1. Se duplica la capacidad del arreglo interno.
2. Se reinicializan las listas.
3. Se recalcula el hash de todos los elementos existentes y se reinsertan en la nueva estructura (Rehashing).

## Como Ejecutar

Para verificar el funcionamiento correcto de la estructura de datos:

1. Compilar el proyecto asegurandose de incluir ambos paquetes.
2. Ejecutar la clase principal: test.TestTablaHash

## Salida Esperada

Al ejecutar la clase de prueba, deberia obtenerse una salida en consola similar a esta, confirmando que las operaciones CRUD y el redimensionamiento funcionan correctamente:

=== Iniciando Pruebas de TablaHash ===

--- Prueba de Insercion ---
Insertados gato, perro y casa. Tamano esperado 3. Actual: 3

--- Prueba de Actualizacion ---
Valor de 'gato' actualizado (esperado 100): 100

--- Prueba de Multiples Elementos (Forzar Resize) ---
Se insertaron 15 elementos extra.
Tamano total esperado (3 iniciales + 15 nuevos): 18

--- Prueba de Recuperacion ---
Valor de 'clave5' (esperado 5): 5
Valor de 'fantasma' (esperado null): null

--- Prueba de Eliminacion ---
Eliminando 'perro'. Valor retornado (esperado 2): 2
Buscar 'perro' despues de borrar (esperado null): null
Tamano despues de borrar: 17

=== Pruebas Finalizadas ===

## Autor
* Desarrollador: jcant (Jesus Elmer Cantua Dominguez)
* Materia: Estructura de Datos
* Version: 1.0 (Parte 1 - Estructura Base)
