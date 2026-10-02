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


2. Patrón Builder
¿Cómo funciona?
El Builder soluciona el problema de tener constructores gigantescos con muchos parámetros. Permite construir objetos complejos paso a paso mediante métodos encadenados (fluent interface), decidiendo exactamente qué atributos (nombre, ataque, velocidad, vida) tendrá el personaje.

Código de implementación
Java

el patron entra en la parte del codigo donde dice 
package com.game.builder;

import com.game.model.Character;
import com.game.model.BaseWarrior;

public class CharacterBuilder {
    private String name = "Héroe por Defecto";
    private int attack = 10;
    private int speed = 5;
    private int health = 100;

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public CharacterBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public CharacterBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public Character build() {
        BaseWarrior character = new BaseWarrior();
        // Configura los valores en tu modelo base según corresponda
        return character;
    }
}
Commit asociado para Git
Bash
git add src/com/game/builder/
git commit -m "feat(builder): añadir CharacterBuilder para la creación paso a paso de per

el commit hecho por nicoll lopez 
