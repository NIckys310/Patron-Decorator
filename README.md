# Video Game Buffs & Debuffs — Decorator Pattern in Java

Este proyecto implementa el patrón de diseño estructural **Decorator** en Java para gestionar un sistema de efectos dinámicos (*buffs* y *debuffs*) sobre personajes en un videojuego.

El repositorio está estructurado para demostrar el trabajo colaborativo en equipo mediante ramas en Git y separación clara de responsabilidades sin acoplamiento.

---

## 📋 Tabla de Contenidos
- [Contexto del Problema](#-contexto-del-problema)
- [Solución con el Patrón Decorator](#-solución-con-el-patrón-decorator)
- [Arquitectura del Proyecto](#-arquitectura-del-proyecto)
- [Distribución del Trabajo por Integrantes](#-distribución-del-trabajo-por-integrantes)
- [Requisitos de Ejecución](#-requisitos-de-ejecución)
- [Cómo Compilar y Ejecutar](#-cómo-compilar-y-ejecutar)
- [Ejecución de Pruebas Unitarias](#-ejecución-de-pruebas-unitarias)

---

## 🎮 Contexto del Problema

En un videojuego de rol o acción (RPG), un personaje posee estadísticas base como **Puntos de Ataque**, **Velocidad de Movimiento** y **Puntos de Vida**. Durante la partida, el jugador puede recibir alteraciones temporales:
* **Buffs (Mejoras):** Tomar una poción de velocidad, activar un escudo protector.
* **Debuffs (Penalizaciones):** Recibir un ataque envenenado que reduce la fuerza.

### El problema de la herencia tradicional
Intentar resolver esto creando subclases para cada combinación (`WarriorWithSpeed`, `WarriorWithShieldAndPoison`, etc.) genera una **explosión de clases** inmanejable. Usar múltiples variables booleanas en la clase base viola el principio de responsabilidad única (*SRP*) y dificulta añadir nuevos efectos en el futuro.

---

## 💡 Solución con el Patrón Decorator

El patrón **Decorator** permite envolver la instancia de un personaje dentro de objetos "decoradores" que modifican sus comportamientos o estadísticas de manera dinámica en tiempo de ejecución.

* **Composición sobre Herencia:** Los efectos actúan como capas alrededor del personaje original.
* **Encadenamiento:** Se pueden apilar múltiples *buffs* y *debuffs* en cualquier orden.
* **Principio Abierto/Cerrado (OCP):** Se pueden añadir nuevos efectos creando clases decoradoras sin modificar el código base del personaje.

---

## 📐 Arquitectura del Proyecto

```text
src/
└── com/
    └── game/
        ├── model/
        │   ├── Character.java         # Interfaz Componente Base
        │   └── BaseWarrior.java       # Componente Concreto
        ├── decorators/
        │   ├── EffectDecorator.java   # Decorador Base Abstracto
        │   ├── SpeedPotionDecorator.java  # Buff: Aumenta velocidad (+5)
        │   ├── ShieldDecorator.java       # Buff: Absorbe daño (-50%)
        │   └── PoisonDecorator.java       # Debuff: Reduce ataque (-4)
        └── GameDemo.java              # Cliente / Simulación principal

test/
└── com/
    └── game/
        └── CharacterTest.java         # Pruebas Unitarias (JUnit 5)

3. Patrón Prototype
¿Cómo funciona?
El patrón Prototype permite clonar objetos existentes (duplicar un personaje ya configurado o un set de buffs activo) en lugar de instanciarlos desde cero. Es muy útil en juegos para generar oleadas de enemigos idénticos o duplicar estados rápidamente.

Código de implementación
Java
El patron entre desde

package com.game.prototype;

// Interfaz Prototype genérica
public interface Prototype<T> {
    T clone();
}

// Aplicación en los efectos o personajes del juego
public abstract class EffectDecorator implements com.game.model.Character, Prototype<EffectDecorator> {
    protected com.game.model.Character wrappedCharacter;

    public EffectDecorator(com.game.model.Character character) {
        this.wrappedCharacter = character;
    }

    @Override
    public abstract EffectDecorator clone();
}
Commit asociado para Git

