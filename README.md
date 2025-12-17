#Servidor de Chat Concurrente en Java
##Descripción

Este proyecto implementa un servidor de chat concurrente en Java utilizando Sockets, Threads e IO Streams.
El objetivo es comprender los problemas de los servidores bloqueantes y aplicar programación multihilo para permitir la comunicación simultánea con múltiples clientes.

Práctica realizada para el módulo PSP (Procesos y Servicios).

##Objetivos

Comprender el funcionamiento básico de los sockets en Java.

Analizar el problema del bloqueo en servidores secuenciales.

Implementar comunicación cliente-servidor en bucle.

Desarrollar un servidor multihilo mediante Runnable.

Gestionar correctamente errores y desconexiones inesperadas.

##Tecnologías

Java

Java Sockets

Threads

DataInputStream / DataOutputStream

IntelliJ IDEA

##Estructura del Proyecto
src/
├── Fase_1/
│ ├── Cliente1.java
│ ├── Cliente2.java
│ └── Servidor.java
├── Fase_2/
│ ├── Cliente.java
│ └── Servidor.java
├── Fase_3/
│ ├── Cliente.java
│ ├── Multihilo.java
│ └── ServidorPrincipal.java
└── Fase_4/
├── Cliente.java
├── Multihilos.java
└── ServidorPrincipal.java

🚀 Funcionalidades

Comunicación continua cliente-servidor

Atención a múltiples clientes simultáneamente

Gestión de conexiones mediante hilos independientes

Identificación de clientes por IP

Manejo de desconexiones inesperadas

Cierre correcto de recursos

🧪 Fases de Desarrollo
Fase 1 – Análisis del Bloqueo

Se demuestra cómo un servidor sin hilos queda bloqueado al atender a un cliente, impidiendo nuevas conexiones.

Fase 2 – Conversación Fluida

Se implementa un bucle de comunicación que finaliza cuando el cliente envía el mensaje FIN.

Fase 3 – Servidor Multihilo

Cada cliente es gestionado por un hilo independiente mediante la clase GestorCliente.

Fase 4 – Mejoras Profesionales

Se añaden logs de conexión, gestión de errores y control de desconexiones abruptas.
