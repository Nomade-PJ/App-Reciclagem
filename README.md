# App Reciclagem

Aplicativo Android para gerenciamento de pontos de coleta de materiais recicláveis.

## Funcionalidades

- Cadastro de pontos de coleta
- Cadastro de materiais recicláveis
- Interface moderna e intuitiva
- Armazenamento local usando SQLite

## Tecnologias Utilizadas

- Java
- Android SDK
- SQLite
- AndroidX
- Material Design Components

## Como Executar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize o projeto com os arquivos Gradle
4. Execute o aplicativo em um emulador ou dispositivo Android

## Requisitos

- Android Studio
- JDK 11 ou superior
- Android SDK versão 21 ou superior
- Dispositivo/Emulador com Android 5.0 ou superior

## Estrutura do Projeto

```
app/
  ├── src/main/
  │   ├── java/com/example/reciclagem/
  │   │   ├── MainActivity.java
  │   │   ├── PontosColetaActivity.java
  │   │   ├── MateriaisActivity.java
  │   │   ├── DatabaseHelper.java
  │   │   ├── PontosColetaAdapter.java
  │   │   └── MateriaisAdapter.java
  │   ├── res/
  │   │   ├── layout/
  │   │   │   ├── activity_main.xml
  │   │   │   ├── activity_pontos_coleta.xml
  │   │   │   └── activity_materiais.xml
  │   │   └── values/
  │   │       ├── colors.xml
  │   │       └── strings.xml
  │   └── AndroidManifest.xml
  └── build.gradle
```

## Contribuição

Sinta-se à vontade para contribuir com o projeto através de Pull Requests. 