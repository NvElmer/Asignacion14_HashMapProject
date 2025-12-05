# Implementación de HashMap (Tabla Hash)

Este proyecto es una implementación robusta de una estructura de datos tipo Tabla Hash desde cero, utilizando **Encadenamiento Separado** (Separate Chaining) para el manejo de colisiones. Fue desarrollado como parte de la materia de Estructura de Datos.

##  Descripción del Proyecto
La `TablaHash` permite almacenar pares clave-valor de forma genérica. Cuando ocurre una colisión (dos claves generan el mismo índice), se almacenan en una lista enlazada en esa posición. La tabla redimensiona su capacidad automáticamente (rehashing) cuando el factor de carga supera el 0.75.

##  Instrucciones de Compilación y Ejecución

Para compilar y ejecutar el proyecto desde la terminal, ubícate en la carpeta raíz del proyecto y ejecuta los siguientes comandos:

**1. Compilar el código:**
```bash
javac src/**/*.java
