# Maven

[Apache Maven in 5 Minute](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

## Table of Content

- [Install Maven](#install-maven)
- [Create Project](#create-project)
- [Dependency](#dependency)
  - [Lombok](#lombok)

---

## Install Maven

### macOS

```sh
brew install maven
```

### Arch

```sh
sudo pacman -Syu maven
```

Check installation with:

```sh
mvn --version
```

---

## Create Project

```sh
mvn archetype:generate \
    -DgroupId=com.unemotioned.englshtest \
    -DartifactId=english-test \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.5 \
    -DinteractiveMode=false
```

- Structure: `src/main/com/unemotioned/englishtest/EnglishTest.java`
- EnglishTest.java is the `Main` class

Create **Controller**, **Service**, **Model.vo** and etc under the `englishtest`
directory.

---

<!-- TODO: document further down -->

## Build and Run

```sh
mvn clean package
```

```sh
java -jar target/<pkg-name>.jar
```

---

## Dependency

### Lombok
