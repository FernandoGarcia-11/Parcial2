# 🎮 Mini-Juego Pokémon en Java

Un juego de batalla por turnos inspirado en Pokémon, desarrollado en Java aplicando conceptos de Programación Orientada a Objetos, manejo de excepciones, colecciones del JDK y Streams con expresiones lambda.

## 📋 Características del Proyecto

### ✨ Funcionalidades Implementadas
- **Batalla por turnos** entre jugador y CPU
- **4 Pokémon disponibles**: Charmander, Squirtle, Bulbasaur y Pikachu
- **Sistema de ataques** con precisión y daño variable
- **Manejo robusto de excepciones** para entrada inválida
- **Estadísticas detalladas** usando Streams y lambdas
- **Interfaz de consola** simple e intuitiva

### 🔧 Conceptos Técnicos Aplicados
- **POO**: Abstracción, herencia y polimorfismo
- **Excepciones**: Verificadas y no verificadas personalizadas
- **Colecciones**: ArrayList, Map, List con operaciones avanzadas
- **Streams & Lambdas**: Filtrado, ordenamiento y estadísticas
- **Interfaces funcionales**: DamageRule para cálculo de daño

## 🏗️ Arquitectura del Proyecto

```
📁 Pokemon-Game/
├── 📄 Game.java                    # Clase principal del juego
├── 📄 Pokemon.java                 # Clase abstracta base
├── 📄 Attack.java                  # Sistema de ataques
├── 📄 DamageRule.java             # Interfaz funcional para daño
├── 📄 BattleEvent.java            # Registro de eventos
├── 📄 Charmander.java             # Pokémon de fuego
├── 📄 Squirtle.java               # Pokémon de agua
├── 📄 Bulbasaur.java              # Pokémon de planta
├── 📄 Pikachu.java                # Pokémon eléctrico
├── 📄 InvalidChoiceException.java  # Excepción selección inválida
├── 📄 AttackMissedException.java   # Excepción ataque fallido
└── 📄 README.md                   # Este archivo
```

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java JDK 8 o superior
- Terminal/línea de comandos

### Pasos para ejecutar:

1. **Clonar o descargar** todos los archivos `.java` en un directorio

2. **Compilar** las clases en orden:
```bash
# Compilar dependencias base
javac DamageRule.java
javac Attack.java
javac Pokemon.java

# Compilar Pokémon específicos
javac Charmander.java
javac Squirtle.java
javac Bulbasaur.java
javac Pikachu.java

# Compilar excepciones y eventos
javac InvalidChoiceException.java
javac AttackMissedException.java
javac BattleEvent.java

# Compilar clase principal
javac Game.java
```

3. **Ejecutar** el juego:
```bash
java Game
```

### Compilación alternativa (todos los archivos):
```bash
javac *.java
java Game
```

## 🎯 Cómo Jugar

1. **Inicio**: El juego te pedirá tu nombre
2. **Selección**: Elige tu Pokémon favorito (1-4)
3. **Batalla**: En cada turno:
   - Selecciona un ataque de la lista ordenada por precisión
   - La CPU atacará automáticamente
   - Se mostrará el daño infligido y HP restante
4. **Victoria**: Gana quien reduzca a 0 los HP del oponente
5. **Resumen**: Revisa estadísticas detalladas de la batalla

### Pokémon Disponibles:
| Pokémon | Tipo | HP | Ataques Especiales |
|---------|------|----|--------------------|
| **Charmander** | 🔥 Fuego | 80 | Ascuas, Lanzallamas |
| **Squirtle** | 💧 Agua | 85 | Pistola Agua, Burbuja |
| **Bulbasaur** | 🌱 Planta | 90 | Hoja Afilada, Látigo Cepa |
| **Pikachu** | ⚡ Eléctrico | 75 | Impactrueno, Rayo |

## 🛡️ Sistema de Excepciones

### Excepciones Manejadas:
- **`InvalidChoiceException`**: Selección fuera de rango
- **`AttackMissedException`**: Ataque que falla por precisión
- **`NumberFormatException`**: Entrada no numérica

### Validaciones Implementadas:
- ✅ Selección de Pokémon (1-4)
- ✅ Selección de ataque válido
- ✅ Entrada numérica correcta
- ✅ División por cero en estadísticas

## 📊 Estadísticas y Streams

El juego utiliza **Java Streams** para generar estadísticas avanzadas:

```java
// Ejemplo: Top 3 ataques más usados
eventos.stream()
    .filter(evento -> evento.getAccion().contains("atacó con"))
    .collect(Collectors.groupingBy(ataque -> ataque, Collectors.counting()))
    .entrySet().stream()
    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
    .limit(3)
    .forEach(entry -> System.out.println("- " + entry.getKey()));
```

### Métricas Calculadas:
- 📈 **Precisión general** de ataques
- 🎯 **Top 3 ataques** más utilizados
- ⚔️ **Conteo de acciones** por jugador/CPU
- 📋 **Log de eventos** de los últimos 5 turnos
- 🏆 **Resultado final** con estadísticas completas

## 🧪 Casos de Prueba

### Pruebas Funcionales:
- ✅ Selección válida/inválida de Pokémon
- ✅ Ataques que aciertan y fallan
- ✅ Victorias del jugador, CPU y empates
- ✅ Cálculo correcto de estadísticas
- ✅ Manejo de entradas erróneas

### Escenarios de Prueba:
1. **Partida normal**: Selección correcta → Batalla → Victoria
2. **Entradas inválidas**: Números fuera de rango, texto
3. **Ataques fallidos**: Verificar manejo de AttackMissedException
4. **Estadísticas**: Verificar cálculos con diferentes duraciones de batalla

## 🏫 Objetivos Educativos Cumplidos

### Programación Orientada a Objetos:
- ✅ **Abstracción**: Clase abstracta `Pokemon`
- ✅ **Herencia**: Pokémon específicos extienden la clase base
- ✅ **Polimorfismo**: Ataques únicos por tipo de Pokémon
- ✅ **Encapsulación**: Atributos privados con getters/setters

### Manejo de Excepciones:
- ✅ **Verificadas**: InvalidChoiceException, AttackMissedException
- ✅ **No verificadas**: NumberFormatException
- ✅ **Try-catch**: Bloques de manejo robusto

### Colecciones del JDK:
- ✅ **ArrayList**: Listas de ataques y eventos
- ✅ **Map**: Agrupación de estadísticas
- ✅ **List**: Operaciones de filtrado y ordenamiento

### Streams y Expresiones Lambda:
- ✅ **Filtros**: `.filter()` para eventos específicos
- ✅ **Mapeos**: `.map()` para transformaciones
- ✅ **Agrupaciones**: `.collect(groupingBy())`
- ✅ **Ordenamientos**: `.sorted()` con Comparator
- ✅ **Interfaces funcionales**: DamageRule con lambdas

## 👤 Autor

Desarrollado como proyecto educativo para aprender conceptos avanzados de Java.

## 📝 Licencia

Este proyecto es de uso educativo libre.

---

¡Disfruta del juego y que gane el mejor entrenador Pokémon! 🏆
