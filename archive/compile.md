# Compile

> [!NOTE]
> Deprecated after switch to **_Maven_**.

---

## Compile and Run

- `find src -name "*.java"`: find all Java files
- `-d out`: compiled `.class` files into `out/` directory

```bash
javac -d out $(find src -name "*.java")
```

- `Start`: Java file with `psvm`
- `-cp`: classpath points to out which runs the compiled files

```bash
java -cp out kr.or.iei.start.Start
```

This will run the project

In one line:

```bash
javac -d out $(find src -name "*.java") && java -cp out kr.or.iei.start.Start
```

---

## Create JAR

> [!NOTE]
> Deprecated after switched to **Maven**.

After compiling

Create `.jar` file at bin/:

```bash
jar --create \
    --file bin/english-test.jar \
    --main-class kr.or.iei.start.Start \
    -C out .
```

Or for older javac:

```sh
jar -cfe bin/english-test.jar kr.or.iei.start.Start -C out .
```

Run created `.jar` file

```bash
java -jar bin/english-test.jar
```
