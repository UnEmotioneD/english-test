# Compilation

Build and run Java without build tools.

---

## Compile

- `find src -name "*.java"`: find all Java files
- `-d out`: compiled `.class` files into `out/` directory

```sh
javac -d out $(find src -name "*.java")
```

## Run

- `Start`: Java file with `psvm`
- `-cp`: classpath points to out which runs the compiled files

```sh
java -cp out kr.or.iei.start.Start
```

### One Liner

```sh
javac -d out $(find src -name "*.java") && java -cp out kr.or.iei.start.Start
```

---

## JAR

After [compile](#compile).

Creates `.jar` file at **bin** directory:

```sh
jar --create \
    --file bin/english-test.jar \
    --main-class kr.or.iei.start.Start \
    -C out .
```

Run created `.jar` file:

```sh
java -jar bin/english-test.jar
```
