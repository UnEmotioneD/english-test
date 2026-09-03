# Compilation

Archived after switching to maven.

---

## Compile

- `find src -name "*.java"`: find all Java files
- `-d out`: compiled `.class` files into `out/` directory

```bash
javac -d out $(find src -name "*.java")
```

## Run

- `Start`: Java file with `psvm`
- `-cp`: classpath points to out which runs the compiled files

```bash
java -cp out kr.or.iei.start.Start
```

### One Liner

```bash
javac -d out $(find src -name "*.java") && java -cp out kr.or.iei.start.Start
```

---

## JAR

After [compile](#compile).

Creates `.jar` file at **bin** directory:

```bash
jar --create \
    --file bin/english-test.jar \
    --main-class kr.or.iei.start.Start \
    -C out .
```

Run created `.jar` file:

```bash
java -jar bin/english-test.jar
```
