# Unit Converter (Kotlin)

## Description

This project is a console-based Unit Converter application written in Kotlin. It converts between various units of measurement, including:
- **Length** conversions (meter, kilometer, millimeter, centimeter, mile, yard, foot, inch)
- **Weight** conversions (gram, kilogram, milligram, pound, ounce)
- **Temperature** conversions (Celsius, Fahrenheit, Kelvin)

The application handles input validation, supports both singular and plural unit names, and provides accurate conversions based on standard conversion ratios.

## Motivation

This project was developed to practice console-based application development in Kotlin, including enum-based unit management, conversion algorithms, and input parsing with error handling.

## Stack & Requirements

- **Language:** Kotlin
- **Build System:** Gradle
- **JDK:** 17 or higher (recommended)

## Getting Started

### Prerequisites

Ensure you have the Java Development Kit (JDK) installed on your system.

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd "Unit Converter"
   ```
2. Open the project in an IDE that supports Kotlin (e.g., IntelliJ IDEA).

### Running the Application

You can run the `Main.kt` file directly from your IDE, which is located in `Unit Converter/task/src/converter/Main.kt`.

Alternatively, use the Gradle wrapper from the command line:

```bash
./gradlew run
```

*Note: On Windows, use `gradlew.bat run`.*

## Scripts & Commands

- `./gradlew build`: Compiles the project and runs tests.
- `./gradlew run`: Executes the main application.
- `./gradlew test`: Runs the automated test suite.
- `./gradlew clean`: Deletes the build directory.

## Tests

The project includes tests powered by the `hs-test` framework. To run tests:

```bash
./gradlew test
```

## Project Structure

```text
.
├── Unit Converter
│   ├── task
│   │   ├── src/converter/Main.kt       # Main entry point
│   │   └── test/                       # Project tests
│   └── ... (stage-specific task files)
├── build.gradle                   # Gradle build configuration
├── settings.gradle                # Gradle project settings
└── ...
```

## Environment Variables

No specific environment variables are required for this project.

## Code of Conduct

Please review our [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for expected behavior.

## Contributing

Please see our [CONTRIBUTING.md](CONTRIBUTING.md) for details on how to contribute to this project.

## Contributor

Mateusz Piotrowski

## License

MIT

## Badges

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)
